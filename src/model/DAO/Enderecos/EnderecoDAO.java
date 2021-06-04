package model.DAO.Enderecos;

import model.DAO.Principal.ConectionFactory;
import model.DAO.Principal.InterfaceDAO;
import java.util.List;
import model.bo.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EnderecoDAO implements InterfaceDAO<Endereco>{

    @Override
    public void Create(Endereco objeto) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "INSERT INTO endereco(logradouro,numero,complemento,bairroId,cidadeId,cep,status) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstm = null;
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1,objeto.getLogradouro());
            pstm.setString(2,objeto.getNumero());
            pstm.setString(3,objeto.getComplemento());
            pstm.setInt(4,objeto.getBairro().getId());
            pstm.setInt(5,objeto.getCidade().getId());

            pstm.setString(6,objeto.getCep());
            pstm.setBoolean(7,objeto.getStatus());
            pstm.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);       
    }

    @Override
    public List<Endereco> Retrieve() {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "SELECT id,logradouro,numero,complemento,bairroId, cidadeId,cep,status FROM endereco";  
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Endereco> enderecos = new ArrayList();
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();
            while(rs.next()){
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                //Alterar na implementação da camada service...
                BairroDAO bairroDAO = new BairroDAO();
                endereco.setBairro(bairroDAO.Retrieve(rs.getInt("bairroId")));
                
                CidadeDAO cidadeDAO = new CidadeDAO();
                endereco.setCidade(cidadeDAO.Retrieve(rs.getInt("cidadeId")));
                
                endereco.setCep(rs.getString("cep"));
                endereco.setStatus(rs.getBoolean("status"));
                enderecos.add(endereco);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return enderecos;
        }catch(Exception ex){
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public Endereco Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "SELECT id,logradouro,numero,complemento,bairroId, cidadeId,cep,status FROM endereco WHERE endereco.id = ?";  
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            Endereco endereco = new Endereco();
            while(rs.next()){
                endereco.setId(rs.getInt("id"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                //Alterar na implementação da camada service...
                BairroDAO bairroDAO = new BairroDAO();
                endereco.setBairro(bairroDAO.Retrieve(rs.getInt("bairroId")));
                
                CidadeDAO cidadeDAO = new CidadeDAO();
                endereco.setCidade(cidadeDAO.Retrieve(rs.getInt("cidadeId")));
                endereco.setCep(rs.getString("cep"));
                endereco.setStatus(rs.getBoolean("status"));
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return endereco;
        }catch(Exception ex){
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public void Update(Endereco objeto) {
        Connection conexao = ConectionFactory.getConection();        
        String sqlExecutar = "UPDATE endereco SET logradouro=?,numero=?,complemento=?,bairroId=?,cidadeId=?,cep=?,status=? WHERE id=?";        
        PreparedStatement pstm = null;
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, objeto.getLogradouro());
            pstm.setString(2, objeto.getNumero());
            pstm.setString(3, objeto.getComplemento());
            pstm.setInt(4, objeto.getBairro().getId());
            pstm.setInt(5, objeto.getCidade().getId());
            pstm.setString(6, objeto.getCep());
            pstm.setBoolean(7, objeto.getStatus());
            pstm.setInt(8, objeto.getId());
            pstm.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }        
        ConectionFactory.closeConnection(conexao, pstm);        
    }

    @Override
    public void Delete(Endereco objeto)  {
        Connection conexao = ConectionFactory.getConection();        
        String sqlExecutar = "DELETE FROM endereco WHERE id =?";        
        PreparedStatement pstm = null;
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();            
        }catch(Exception ex){
            ex.printStackTrace();
        }        
        ConectionFactory.closeConnection(conexao, pstm);
    }
}

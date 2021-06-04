
package model.DAO.Vender;

import model.DAO.Enderecos.EnderecoDAO;
import model.DAO.Principal.ConectionFactory;
import model.DAO.Principal.InterfaceDAO;
import java.util.List;
import model.bo.Personal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PersonalDAO implements InterfaceDAO<Personal>{

    @Override
    public void Create(Personal objeto) {
        
        Connection conexao = ConectionFactory.getConection();
        
        String sqlExecutar = "INSERT INTO pessoaFisica(nome, rg, cpf, dataDeNascimento, complemento, telefone1, telefone2, email, observacao,  enderecoId) VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement pstm = null;
        
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getRg());
            pstm.setString(3, objeto.getCpf());
            pstm.setString(4, objeto.getDataDeNascimento());
            pstm.setString(5, objeto.getComplemento());
            pstm.setString(6, objeto.getTelefone1());
            pstm.setString(7, objeto.getTelefone2());
            pstm.setString(8, objeto.getEmail());
            pstm.setString(9, objeto.getObservacao());
            pstm.setInt(10, objeto.getEndereco().getId());

            pstm.executeUpdate();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public List<Personal> Retrieve() {
        Connection conexao = ConectionFactory.getConection();
        
        String sqlExecutar = "SELECT id,nome, rg, cpf, dataDeNascimento, complemento, telefone1, telefone2, email, observacao, enderecoId FROM pessoaFisica";
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
 
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();
            
            List<Personal> personais = new ArrayList();
            
            while(rs.next()){
                Personal pessoaFisica = new Personal();
                
                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setRg(rs.getString("rg"));
                pessoaFisica.setCpf(rs.getString("cpf")); 
                pessoaFisica.setDataDeNascimento(rs.getString("dataDeNascimento"));
                pessoaFisica.setComplemento(rs.getString("complemento"));
                pessoaFisica.setTelefone1(rs.getString("telefone1"));
                pessoaFisica.setTelefone2(rs.getString("telefone2"));
                pessoaFisica.setEmail(rs.getString("email"));
                pessoaFisica.setObservacao(rs.getString("observacao"));
                
                EnderecoDAO enderecoDAO = new EnderecoDAO();
                pessoaFisica.setEndereco(enderecoDAO.Retrieve(rs.getInt("enderecoId")));
                
                personais.add(pessoaFisica);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return personais;
        }catch(Exception ex){
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
        
    }

    @Override
    public Personal Retrieve(int id) {

        Connection conexao = ConectionFactory.getConection();       
        String sqlExecutar = "SELECT id, nome, rg, cpf, dataDeNascimento, complemento, telefone1, telefone2, email, observacao, enderecoId FROM pessoaFisica WHERE  pessoaFisica.id = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
 
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, id);           
            rs = pstm.executeQuery();
            Personal pessoaFisica = new Personal();

            while(rs.next()){
                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setNome(rs.getString("nome"));
                pessoaFisica.setRg(rs.getString("rg"));
                pessoaFisica.setCpf(rs.getString("cpf"));
                pessoaFisica.setDataDeNascimento(rs.getString("dataDeNascimento"));
                pessoaFisica.setComplemento(rs.getString("complemento"));
                pessoaFisica.setTelefone1(rs.getString("telefone1"));
                pessoaFisica.setTelefone2(rs.getString("telefone2"));
                pessoaFisica.setEmail(rs.getString("email"));
                pessoaFisica.setObservacao(rs.getString("observacao"));
                
                EnderecoDAO enderecoDAO = new EnderecoDAO();
                pessoaFisica.setEndereco(enderecoDAO.Retrieve(rs.getInt("enderecoId")));
            }
            
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return pessoaFisica;
            
        }catch(Exception ex){
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
        
    }

    @Override
    public void Update(Personal objeto) {
        Connection conexao = ConectionFactory.getConection();        
        String sqlExecutar = "UPDATE pessoaFisica SET nome =?, rg =?, cpf = ?, dataDeNascimento = ? ,complemento = ?, telefone1 = ?, telefone2 = ?, email = ?, observacao = ?, enderecoId = ?, WHERE id = ?"; 
        
        PreparedStatement pstm = null;
        
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, objeto.getId());
            pstm.setString(2, objeto.getRg());
            pstm.setString(3, objeto.getNome());
            pstm.setString(4, objeto.getCpf());
            pstm.setString(5, objeto.getDataDeNascimento());
            pstm.setString(6, objeto.getComplemento());
            pstm.setString(7, objeto.getTelefone1());
            pstm.setString(8, objeto.getTelefone2());
            pstm.setString(9, objeto.getEmail());
            pstm.setString(10, objeto.getObservacao());
            pstm.setInt(11, objeto.getEndereco().getId());
            
            pstm.executeUpdate();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }        
        ConectionFactory.closeConnection(conexao, pstm);  
    }

    @Override
    public void Delete(Personal objeto) {
        Connection conexao = ConectionFactory.getConection();        
        String sqlExecutar = "DELETE FROM pessoaFisica WHERE id =?";        
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

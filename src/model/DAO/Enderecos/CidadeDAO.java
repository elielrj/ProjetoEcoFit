
package model.DAO.Enderecos;

import model.DAO.Principal.ConectionFactory;
import model.DAO.Principal.InterfaceDAO;
import java.util.List;
import model.bo.Cidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class CidadeDAO implements InterfaceDAO<Cidade>{
    

    @Override
    public void Create(Cidade objeto) {
        Connection conexao = ConectionFactory.getConection();        
        String sqlExecutar = "INSERT INTO cidade(nome,status) VALUES(?,?)"; 
        PreparedStatement pstm = null;
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString  (1, objeto.getNome());
            pstm.setBoolean(2, objeto.getStatus());
            pstm.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }        
        ConectionFactory.closeConnection(conexao, pstm);
    }
    

    @Override
    public List<Cidade> Retrieve() {
        
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;
        ResultSet rs = null;       
        
        List<Cidade> cidades = new ArrayList();
       
        try{
            
            String sqlExecutar = "SELECT id,nome,status FROM cidade"; 
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();
        
            while(rs.next()){
                Cidade cidade = new Cidade();
                cidade.setId(rs.getInt("id"));
                cidade.setNome(rs.getString("nome"));
               
                cidade.setStatus(rs.getBoolean("status"));
                
                cidades.add(cidade);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return cidades;
        }catch(Exception ex){
            
            //throw new RuntimeException("Erro na conexão com a lista de cidades: "+ex);            
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public Cidade Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();       
        String sqlExecutar = "SELECT id,nome,status FROM cidade WHERE cidade.id = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            Cidade cidade = new Cidade();
            while(rs.next()){
                cidade.setId    (rs.getInt("id"));
                cidade.setNome  (rs.getString("nome"));
                cidade.setStatus(rs.getBoolean("status"));
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return cidade;
        }catch(Exception ex){
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public void Update(Cidade objeto)
    {
        Connection conexao = ConectionFactory.getConection();        
        String sqlExecutar = "UPDATE cidade SET nome=?,status=? WHERE id = ?";        
        PreparedStatement pstm = null;   
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString  (1, objeto.getNome());
            pstm.setBoolean (2, objeto.getStatus());
            pstm.setInt     (3, objeto.getId());
            pstm.executeUpdate();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }        
        ConectionFactory.closeConnection(conexao, pstm);        
    }

    @Override
    public void Delete(Cidade objeto){
        Connection conexao = ConectionFactory.getConection();        
        String sqlExecutar = "DELETE FROM cidade WHERE id=?";        
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

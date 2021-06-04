
package model.DAO.Vender;

import model.DAO.Principal.ConectionFactory;
import model.DAO.Principal.InterfaceDAO;
import java.util.List;
import model.bo.Receber;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReceberDAO implements InterfaceDAO<Receber> {

    @Override
    public void Create(Receber objeto) {
        Connection conexao = ConectionFactory.getConection();     
        
        String sqlExecutar = "INSERT INTO receber(data,hora,valorDeDescontoNegociado, valorDeAcrescimo,valorRecebido, observacao,vendaId) VALUES(?,?,?,?,?,?,?)";
                
        
        PreparedStatement pstm = null;
        
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());
            pstm.setDouble(3, objeto.getValorDeDescontoNegociado());
            pstm.setDouble(4, objeto.getValorDeAcrescimo());
            pstm.setDouble(5, objeto.getValorRecebido());
            pstm.setString(6, objeto.getObservacao());
            pstm.setInt(7, objeto.getVenda().getId());
            pstm.executeUpdate();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public List<Receber> Retrieve() {
        Connection conexao = ConectionFactory.getConection();
        
        String sqlExecutar = "SELECT id,data,hora,valorDeDescontoNegociado, valorDeAcrescimo,valorRecebido, observacao,vendaId FROM receber";
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
 
        List<Receber> recebimentos = new ArrayList();
        
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();
            
            
            
            while(rs.next()){
                Receber recebimento = new Receber();
                recebimento.setId(rs.getInt("id"));
                recebimento.setData(rs.getString("data"));
                recebimento.setHora(rs.getString("hora")); 
                recebimento.setValorDeDescontoNegociado(rs.getFloat("valorDeDescontoNegociado"));
                recebimento.setValorDeAcrescimo(rs.getFloat("valorDeAcrescimo"));
                recebimento.setValorRecebido(rs.getFloat("valorRecebido"));
                recebimento.setObservacao(rs.getString("observacao"));
                recebimento.getVenda().setId(rs.getInt("vendaId"));
                
                recebimentos.add(recebimento);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return recebimentos;
        }catch(Exception ex){
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public Receber Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();
        
        String sqlExecutar = "SELECT id,data,hora,valorDeDescontoNegociado, valorDeAcrescimo,valorRecebido, observacao,vendaId FROM receber WHERE receber.id =?";
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
 
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1,id);
            rs = pstm.executeQuery();
            
            Receber recebimento = new Receber();
            
            while(rs.next()){
                recebimento.setId(rs.getInt("id"));
                recebimento.setData(rs.getString("data"));
                recebimento.setHora(rs.getString("hora")); 
                recebimento.setValorDeDescontoNegociado(rs.getFloat("valorDeDescontoNegociado"));
                recebimento.setValorDeAcrescimo(rs.getFloat("valorDeAcrescimo"));
                recebimento.setValorRecebido(rs.getFloat("valorRecebido"));
                recebimento.setObservacao(rs.getString("observacao"));
                recebimento.getVenda().setId(rs.getInt("vendaId"));
 
                
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return recebimento;
        }catch(Exception ex){
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public void Update(Receber objeto) {
        Connection conexao = ConectionFactory.getConection();        
        String sqlExecutar = "UPDATE receber SET data =?,hora =?,valorDeDescontoNegociado =?,valorDeAcrescimo =?,valorRecebido =?,observacao = ?,vendaId =? WHERE id=?"; 
        
        PreparedStatement pstm = null;
        
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());
            pstm.setDouble(3, objeto.getValorDeDescontoNegociado());
            pstm.setDouble(4, objeto.getValorDeAcrescimo());
            pstm.setDouble(5, objeto.getValorRecebido());
            pstm.setString(6, objeto.getObservacao());
            pstm.setInt(7, objeto.getVenda().getId());
            pstm.setInt(8, objeto.getId());
            
            pstm.executeUpdate();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }        
        ConectionFactory.closeConnection(conexao, pstm); 
    }

    @Override
    public void Delete(Receber objeto) {
        
        
        Connection conexao = ConectionFactory.getConection();        
        String sqlExecutar = "DELETE FROM receber WHERE id =?";        
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

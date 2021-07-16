package model.DAO;

import java.util.List;
import model.bo.Receber;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DAO.SQL.SQL;

public class ReceberDAO implements InterfaceDAO<Receber> {
    
    @Override
    public void Create(Receber objeto) {
        Connection conexao = ConectionFactory.getConection();      
        PreparedStatement pstm = null;        
        try {
            pstm = conexao.prepareStatement(SQL.RECEBER_CREATE);
            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());
            pstm.setDouble(3, objeto.getValorDeDescontoNegociado());
            pstm.setDouble(4, objeto.getValorDeAcrescimo());
            pstm.setDouble(5, objeto.getValorRecebido());
            pstm.setString(6, objeto.getObservacao());
            pstm.setInt(7, objeto.getVenda().getId());
            pstm.executeUpdate();            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        ConectionFactory.closeConnection(conexao, pstm);
    }
    
    @Override
    public List<Receber> Retrieve() {
        Connection conexao = ConectionFactory.getConection();                
        PreparedStatement pstm = null;
        ResultSet rs = null;        
        List<Receber> recebimentos = new ArrayList();        
        try {
            pstm = conexao.prepareStatement(SQL.RECEBER_RETRIVE_ALL);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                Receber recebimento = new Receber.ReceberBuilder()
                        .setId(rs.getInt("id"))
                        .setData(rs.getString("data"))
                        .setHora(rs.getString("hora"))
                        .setValorDeDescontoNegociado(rs.getFloat("valorDeDescontoNegociado"))
                        .setValorDeAcrescimo(rs.getFloat("valorDeAcrescimo"))
                        .setValorRecebido(rs.getFloat("valorRecebido"))
                        .setObservacao(rs.getString("observacao"))
                        .setVenda(
                                service.ServiceVenda.Buscar(rs.getInt("vendaId"))
                        )
                        .createReceber();
                recebimentos.add(recebimento);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return recebimentos;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }
    
    @Override
    public Receber Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();      
        PreparedStatement pstm = null;
        ResultSet rs = null;        
        try {
            pstm = conexao.prepareStatement(SQL.RECEBER_RETRIVE_ONE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            
            Receber recebimento = new Receber.ReceberBuilder().createReceber();
            
            while (rs.next()) {
                recebimento.setId(rs.getInt("id"));
                recebimento.setData(rs.getString("data"));
                recebimento.setHora(rs.getString("hora"));
                recebimento.setValorDeDescontoNegociado(rs.getFloat("valorDeDescontoNegociado"));
                recebimento.setValorDeAcrescimo(rs.getFloat("valorDeAcrescimo"));
                recebimento.setValorRecebido(rs.getFloat("valorRecebido"));
                recebimento.setObservacao(rs.getString("observacao"));
                recebimento.setVenda(
                        service.ServiceVenda.Buscar(rs.getInt("vendaId"))
                );
                
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return recebimento;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }
    
    @Override
    public void Update(Receber objeto) {
        Connection conexao = ConectionFactory.getConection();
        
        PreparedStatement pstm = null;
        
        try {
            pstm = conexao.prepareStatement(SQL.RECEBER_UPDATE);
            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());
            pstm.setDouble(3, objeto.getValorDeDescontoNegociado());
            pstm.setDouble(4, objeto.getValorDeAcrescimo());
            pstm.setDouble(5, objeto.getValorRecebido());
            pstm.setString(6, objeto.getObservacao());
            pstm.setInt(7, objeto.getVenda().getId());
            pstm.setInt(8, objeto.getId());
            
            pstm.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }
    
    @Override
    public void Delete(Receber objeto) {
        
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;
        
        try {
            pstm = conexao.prepareStatement(SQL.RECEBER_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }
    
}


package model.DAO.Comprar;

import model.DAO.Principal.ConectionFactory;
import model.DAO.Principal.InterfaceDAO;
import java.util.List;
import model.bo.Pagar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class PagarDAO implements InterfaceDAO<Pagar> {

    @Override
    public void Create(Pagar objeto) {
        Connection conexao = ConectionFactory.getConection();     
        
        String sqlExecutar = "INSERT INTO pagar(data,hora,valorDeDescontoNegociado, valorDeAcrescimo,valorPago, observacao,compraId) VALUES(?,?,?,?,?,?,?)";
                
        
        PreparedStatement pstm = null;
        
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());
            pstm.setDouble(3, objeto.getValorDeDescontoNegociado());
            pstm.setDouble(4, objeto.getValorDeAcrescimo());
            pstm.setDouble(5, objeto.getValorPago());
            pstm.setString(6, objeto.getObservacao());
            pstm.setInt(7, objeto.getCompra().getId());
            pstm.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public List<Pagar> Retrieve() {
        Connection conexao = ConectionFactory.getConection();
        
        String sqlExecutar = "SELECT id,data,hora,valorDeDescontoNegociado, valorDeAcrescimo,valorPago, observacao,compraId FROM pagar";
        PreparedStatement pstm = null;
        ResultSet rs = null;
 
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();
            
            List<Pagar> pagamentos = new ArrayList();
            
            while(rs.next()){
                Pagar pagamento = new Pagar();
              
                pagamento.setId(rs.getInt("id"));
                pagamento.setData(rs.getString("data"));
                pagamento.setHora(rs.getString("hora")); 
                pagamento.setValorDeDescontoNegociado(rs.getFloat("valorDeDescontoNegociado"));
                pagamento.setValorDeAcrescimo(rs.getFloat("valorDeAcrescimo"));
                pagamento.setValorPago(rs.getFloat("valorPago"));
                pagamento.setObservacao(rs.getString("observacao"));
                
                CompraDAO compraDAO = new CompraDAO();
                pagamento.setCompra(compraDAO.Retrieve(rs.getInt("compraId")));
                
                pagamentos.add(pagamento);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return pagamentos;
        }catch(Exception ex){
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public Pagar Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();
        
        String sqlExecutar = "SELECT id,data,hora,valorDeDescontoNegociado, valorDeAcrescimo,valorPago, observacao,compraId FROM pagar WHERE id = ?";
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
 
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1,id);
            rs = pstm.executeQuery();
            
            Pagar pagamento = new Pagar();
            
            while(rs.next()){
                pagamento.setId(rs.getInt("id"));
                pagamento.setData(rs.getString("data"));
                pagamento.setHora(rs.getString("hora")); 
                pagamento.setValorDeDescontoNegociado(rs.getFloat("valorDeDescontoNegociado"));
                pagamento.setValorDeAcrescimo(rs.getFloat("valorDeAcrescimo"));
                pagamento.setValorPago(rs.getFloat("valorPago"));
                pagamento.setObservacao(rs.getString("observacao"));
                
                CompraDAO compraDAO = new CompraDAO();
                pagamento.setCompra(compraDAO.Retrieve(rs.getInt("compraId")));
                
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return pagamento;
        }catch(Exception ex){
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public void Update(Pagar objeto) {
        Connection conexao = ConectionFactory.getConection();        
        String sqlExecutar = "UPDATE pagar SET data = ?, hora = ?, valorDeDescontoNegociado = ?, valorDeAcrescimo =?, valorPago = ?, observacao = ?, compraId = ? WHERE id=?"; 
        
        PreparedStatement pstm = null;
        
        try{
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());
            pstm.setDouble(3, objeto.getValorDeDescontoNegociado());
            pstm.setDouble(4, objeto.getValorDeAcrescimo());
            pstm.setDouble(5, objeto.getValorPago());
            pstm.setString(6, objeto.getObservacao());
            pstm.setInt(7, objeto.getCompra().getId());
            pstm.setInt(8, objeto.getId());

            pstm.executeUpdate();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }        
        ConectionFactory.closeConnection(conexao, pstm); 
    }

    @Override
    public void Delete(Pagar objeto) {
        
        
        Connection conexao = ConectionFactory.getConection();        
        String sqlExecutar = "DELETE FROM pagar WHERE id = ?";        
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

package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import model.bo.ItemDeVenda;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DAO.ProdutoDAO;

public class ItemDeVendaDAO implements InterfaceDAO<ItemDeVenda> {

    @Override
    public void Create(ItemDeVenda objeto) {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "INSERT INTO itemDeVenda( quantidade, valor, status, vendaId,produtoId ) VALUES(?,?,?,?,?)";

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, objeto.getQuantidade());
            pstm.setDouble(2, objeto.getValor());
            pstm.setBoolean(3, objeto.getStatus());
            pstm.setInt(4, objeto.getVendaId());
            pstm.setInt(5, objeto.getProduto().getId());

            pstm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public List<ItemDeVenda> Retrieve() {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id, quantidade, valor, status, vendaId,produtoId FROM itemDeVenda WHERE status=true order bay id ";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();

            List<ItemDeVenda> itensDeVenda = new ArrayList();

            while (rs.next()) {
                ItemDeVenda itemDeVenda = new ItemDeVenda();

                itemDeVenda.setId(rs.getInt("id"));
                itemDeVenda.setQuantidade(rs.getInt("quantidade"));
                itemDeVenda.setValor(rs.getFloat("valor"));
                itemDeVenda.setStatus(rs.getBoolean("status"));
                itemDeVenda.setVendaId(rs.getInt("vendaId"));
                
                ProdutoDAO produtoDAO = new ProdutoDAO();
                itemDeVenda.setProduto(produtoDAO.Retrieve(rs.getInt("produtoId")));

                

                itensDeVenda.add(itemDeVenda);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return itensDeVenda;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public ItemDeVenda Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id,quantidade, valor, status, vendaId,produtoId FROM itemDeVenda WHERE itemDeVenda.id=?";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            ItemDeVenda itemDeVenda = new ItemDeVenda();

            while (rs.next()) {
                itemDeVenda.setId(rs.getInt("id"));
                itemDeVenda.setQuantidade(rs.getInt("quantidade"));
                itemDeVenda.setValor(rs.getFloat("valor"));
                itemDeVenda.setStatus(rs.getBoolean("status"));
                itemDeVenda.setVendaId(rs.getInt("vendaId"));
                
                ProdutoDAO produtoDAO = new ProdutoDAO();
                itemDeVenda.setProduto(produtoDAO.Retrieve(rs.getInt("produtoId")));

                

            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return itemDeVenda;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public void Update(ItemDeVenda objeto) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "UPDATE itemDeVenda SET quantidade = ?, valor =?, status = ?, vendaId=?, produtoId = ? WHERE itemDeVenda.id=?";

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, objeto.getQuantidade());
            pstm.setFloat(2, objeto.getValor());
            pstm.setBoolean(3, objeto.getStatus());
            pstm.setInt(4, objeto.getVendaId());
            pstm.setInt(5, objeto.getProduto().getId());
            pstm.setInt(6, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public void Delete(ItemDeVenda objeto) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "DELETE FROM itemDeVenda WHERE id = ?";
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }
    
    public List<ItemDeVenda> RetrieveTodosOsItensDeUmaVenda(int idDaVenda) {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id, quantidade, valor, status, vendaId,produtoId FROM itemDeVenda WHERE itemDeVenda.vendaId=?";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, idDaVenda);
            rs = pstm.executeQuery();

            List<ItemDeVenda> itensDeVenda = new ArrayList<>();

            while (rs.next()) {
                ItemDeVenda itemDeVenda = new ItemDeVenda();
                itemDeVenda.setId(rs.getInt("id"));
                itemDeVenda.setQuantidade(rs.getInt("quantidade"));
                itemDeVenda.setValor(rs.getFloat("valor"));
                itemDeVenda.setStatus(rs.getBoolean("status"));
                itemDeVenda.setVendaId(rs.getInt("vendaId"));
                
                ProdutoDAO produtoDAO = new ProdutoDAO();
                itemDeVenda.setProduto(produtoDAO.Retrieve(rs.getInt("produtoId")));

                
                itensDeVenda.add(itemDeVenda);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return itensDeVenda;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

}

package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import model.bo.ItemDeCompra;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DAO.ProdutoDAO;

public class ItemDeCompraDAO implements InterfaceDAO<ItemDeCompra> {

    @Override
    public void Create(ItemDeCompra objeto) {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "INSERT INTO itemDeCompra(quantidade, valor, produtoId, compraId) VALUES (?,?,?,?,?)";

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, objeto.getQuantidade());
            pstm.setDouble(2, objeto.getValor());
            pstm.setInt(3, objeto.getProduto().getId());
            pstm.setInt(4, objeto.getCompra().getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public List<ItemDeCompra> Retrieve() {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id, quantidade, valor, produtoId, compraId FROM itemDeCompra";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();

            List<ItemDeCompra> itensDeCompra = new ArrayList();

            while (rs.next()) {
                ItemDeCompra itemDeCompra = new ItemDeCompra();

                itemDeCompra.setId(rs.getInt("id"));
                itemDeCompra.setQuantidade(rs.getInt("quantidade"));
                itemDeCompra.setValor(rs.getFloat("valor"));

                ProdutoDAO produtoDAO = new ProdutoDAO();
                itemDeCompra.setProduto(produtoDAO.Retrieve(rs.getInt("produtoId")));

                CompraDAO compraDAO = new CompraDAO();
                itemDeCompra.setCompra(compraDAO.Retrieve(rs.getInt("compraId")));

                itensDeCompra.add(itemDeCompra);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return itensDeCompra;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public ItemDeCompra Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id, quantidade, valor, produtoId, compraId FROM itemDeCompra WHERE itemDeCompra.id=?";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            ItemDeCompra itemDeCompra = new ItemDeCompra();

            while (rs.next()) {
                itemDeCompra.setId(rs.getInt("id"));
                itemDeCompra.setQuantidade(rs.getInt("quantidade"));
                itemDeCompra.setValor(rs.getFloat("valor"));

                ProdutoDAO produtoDAO = new ProdutoDAO();
                itemDeCompra.setProduto(produtoDAO.Retrieve(rs.getInt("produtoId")));

                CompraDAO compraDAO = new CompraDAO();
                itemDeCompra.setCompra(compraDAO.Retrieve(rs.getInt("compraId")));
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return itemDeCompra;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public void Update(ItemDeCompra objeto) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "UPDATE itemDeCompra SET quantidade =?, valor = ?, produtoId = ?, compraId = ? WHERE id=?";

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, objeto.getQuantidade());
            pstm.setFloat(2, objeto.getValor());
            pstm.setInt(3, objeto.getProduto().getId());
            pstm.setInt(4, objeto.getCompra().getId());
            pstm.setInt(5, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public void Delete(ItemDeCompra objeto) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "DELETE FROM itemDeCompra WHERE id = ?";
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

}

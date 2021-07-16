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
            pstm.setBoolean(1, objeto.getStatus());//2
            pstm.setInt(2, objeto.getQuantidade());//3
            pstm.setInt(3, objeto.getProduto().getId());//4
            pstm.setDouble(4, objeto.getSubTotal());//5
            pstm.setInt(5, objeto.getVendaId());//6
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
                ItemDeVenda itemDeVenda = new ItemDeVenda.ItemDeVendaBuilder()
                        .setId(rs.getInt("id"))
                        .setStatus(rs.getBoolean("status"))
                        .setQuantidade(rs.getInt("quantidade"))
                        .setProduto(
                                service.ServiceProduto.Buscar(rs.getInt("produtoId"))
                        )
                        .setSubTotal(rs.getFloat("valor"))
                        .setVendaId(rs.getInt("vendaId"))
                        .createItemDeVenda();
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

            ItemDeVenda itemDeVenda = new ItemDeVenda.ItemDeVendaBuilder().createItemDeVenda();

            while (rs.next()) {
                itemDeVenda.setId(rs.getInt("id"));
                itemDeVenda.setStatus(rs.getBoolean("status"));
                itemDeVenda.setQuantidade(rs.getInt("quantidade"));
                itemDeVenda.setProduto(
                        service.ServiceProduto.Buscar(rs.getInt("produtoId"))
                );
                itemDeVenda.setSubTotal(rs.getFloat("valor"));
                itemDeVenda.setVendaId(rs.getInt("vendaId"));
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

            pstm.setBoolean(1, objeto.getStatus());
            pstm.setInt(2, objeto.getQuantidade());
            pstm.setInt(3, objeto.getProduto().getId());
            pstm.setFloat(4, objeto.getSubTotal());
            pstm.setInt(5, objeto.getVendaId());
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

    public void Delete(int idVenda) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "DELETE FROM itemDeVenda WHERE itemdevenda.vendaid=?";
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, idVenda);
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
                ItemDeVenda itemDeVenda = new ItemDeVenda.ItemDeVendaBuilder()
                        .setId(rs.getInt("id"))
                        .setStatus(rs.getBoolean("status"))
                        .setQuantidade(rs.getInt("quantidade"))
                        .setProduto(
                                service.ServiceProduto.Buscar(rs.getInt("produtoId"))
                        )
                        .setSubTotal(rs.getFloat("valor"))
                        .setVendaId(rs.getInt("vendaId"))
                        .createItemDeVenda();
                itensDeVenda.add(itemDeVenda);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return itensDeVenda;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Create->bairroDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public List<ItemDeVenda> RetrieveListaDeUmaVenda(int idDaVenda) {

        try {

            String sqlExecutar = "SELECT id, quantidade, valor, status, vendaid,produtoid FROM itemdevenda WHERE vendaid=?";

            Connection conexao = ConectionFactory.getConection();

            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, idDaVenda);

            List<ItemDeVenda> itensDeVenda = new ArrayList<>();

            while (rs.next()) {
                ItemDeVenda itemDeVenda = new ItemDeVenda.ItemDeVendaBuilder()
                        .setId(rs.getInt("id"))//1
                        .setStatus(rs.getBoolean("status"))//2
                        .setQuantidade(rs.getInt("quantidade"))//3
                        .setProduto(service.ServiceProduto.Buscar(rs.getInt("produtoId")))//4
                        .setSubTotal(rs.getFloat("valor"))//5
                        .setVendaId(rs.getInt("vendaId"))//6                        
                        .createItemDeVenda();
                itensDeVenda.add(itemDeVenda);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return itensDeVenda;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Create->bairroDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }

    }
}

package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import model.bo.ItemDeVenda;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DAO.SQL.SQL;

public class ItemDeVendaDAO implements InterfaceDAO<ItemDeVenda> {

    @Override
    public void Create(ItemDeVenda objeto) {
        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.ITEM_DE_VENDA_CREATE);
            pstm.setInt(1, objeto.getQuantidade());//3
            pstm.setInt(2, objeto.getVendaId());//4
            pstm.setInt(3, objeto.getProduto().getId());//5
            pstm.setFloat(4, objeto.getSubTotal());//6
            pstm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public List<ItemDeVenda> Retrieve() {
        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(SQL.ITEM_DE_VENDA_RETRIVE_ALL);
            rs = pstm.executeQuery();

            List<ItemDeVenda> itensDeVenda = new ArrayList();

            while (rs.next()) {
                ItemDeVenda itemDeVenda = new ItemDeVenda.ItemDeVendaBuilder()
                        .setId(rs.getInt("id"))
                        .setQuantidade(rs.getInt("quantidade"))
                        .setVendaId(rs.getInt("vendaid"))
                        .setProduto(
                                service.ServiceProduto.Buscar(rs.getInt("produtoid"))
                        )
                        .setSubTotal(rs.getFloat("subtotal"))
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

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(SQL.ITEM_DE_VENDA_RETRIVE_ONE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            ItemDeVenda itemDeVenda = new ItemDeVenda.ItemDeVendaBuilder().createItemDeVenda();

            while (rs.next()) {
                itemDeVenda.setId(rs.getInt("id"));
                itemDeVenda.setQuantidade(rs.getInt("quantidade"));
                itemDeVenda.setProduto(
                        service.ServiceProduto.Buscar(rs.getInt("produtoid"))
                );
                itemDeVenda.setSubTotal(rs.getFloat("subtotal"));
                itemDeVenda.setVendaId(rs.getInt("vendaid"));
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

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.ITEM_DE_VENDA_UPDATE);

            pstm.setInt(1, objeto.getQuantidade());
            pstm.setInt(2, objeto.getVendaId());
            pstm.setInt(3, objeto.getProduto().getId());
            pstm.setFloat(4, objeto.getSubTotal());
            pstm.setInt(5, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public void Delete(ItemDeVenda objeto) {
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.ITEM_DE_VENDA_DELETE);
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
//??????????????????????????????????????????????????????????

    public List<ItemDeVenda> RetrieveTodosOsItensDeUmaVenda(int idDaVenda) {
        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(SQL.ITEM_DE_VENDA_DELETE_TODOS_ID_VENDA);
            pstm.setInt(1, idDaVenda);
            rs = pstm.executeQuery();

            List<ItemDeVenda> itensDeVenda = new ArrayList<>();

            while (rs.next()) {
                ItemDeVenda itemDeVenda = new ItemDeVenda.ItemDeVendaBuilder()
                        .setId(rs.getInt("id"))
                        .setQuantidade(rs.getInt("quantidade"))
                        .setVendaId(rs.getInt("vendaid"))
                        .setProduto(
                                service.ServiceProduto.Buscar(rs.getInt("produtoid"))
                        )
                        .setSubTotal(rs.getFloat("subtotal"))
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

            Connection conexao = ConectionFactory.getConection();

            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.ITEM_DE_VENDA_RETRIVE_ALL_POR_VENDAID);
            pstm.setInt(1, idDaVenda);

            List<ItemDeVenda> itensDeVenda = new ArrayList<>();

            while (rs.next()) {
                ItemDeVenda itemDeVenda = new ItemDeVenda.ItemDeVendaBuilder()
                        .setId(rs.getInt("id"))//1
                        .setQuantidade(rs.getInt("quantidade"))//3
                        .setVendaId(rs.getInt("vendaid")
                        )//6   
                        .setProduto(
                                service.ServiceProduto.Buscar(rs.getInt("produtoid"))
                        )//4
                        .setSubTotal(rs.getFloat("subtotal"))//5
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

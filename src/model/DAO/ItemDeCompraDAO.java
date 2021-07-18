package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import model.bo.ItemDeCompra;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemDeCompraDAO implements InterfaceDAO<ItemDeCompra> {

    @Override
    public void Create(ItemDeCompra objeto) {
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(SQL.ITEM_DE_COMPRA_CREATE);
            pstm.setInt(1, objeto.getQuantidade());
            pstm.setInt(2, objeto.getProduto().getId());
            pstm.setFloat(3, objeto.getSubTotal());
            pstm.setInt(4, objeto.getCompraId());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ItemDeCompraDAO->Create->\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );            
        }

        
    }

    @Override
    public List<ItemDeCompra> Retrieve() {
        Connection conexao = ConectionFactory.getConection();

        

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(SQL.ITEM_DE_COMPRA_RETRIVE_ALL);
            rs = pstm.executeQuery();

            List<ItemDeCompra> itensDeCompra = new ArrayList();

            while (rs.next()) {
                ItemDeCompra itemDeCompra = new ItemDeCompra.ItemDeCompraBuilder()
                        .setId(rs.getInt("id"))//1
                        .setQuantidade(rs.getInt("quantidade"))//2
                        .setProduto(
                                service.ServiceProduto.Buscar(rs.getInt("produtoid"))
                        )//4                        
                        .setSubTotal(rs.getFloat("valor"))//3
                        .setCompraId(rs.getInt("compraid"))//5
                        .createItemDeCompra();
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

        

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(SQL.ITEM_DE_COMPRA_RETRIVE_ONE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            ItemDeCompra itemDeCompra = new ItemDeCompra.ItemDeCompraBuilder().createItemDeCompra();

            while (rs.next()) {
                itemDeCompra.setId(rs.getInt("id"));
                itemDeCompra.setQuantidade(rs.getInt("quantidade"));
                itemDeCompra.setProduto(
                        service.ServiceProduto.Buscar(rs.getInt("produtoid"))
                );
                itemDeCompra.setSubTotal(rs.getFloat("valor"));
                itemDeCompra.setCompraId(rs.getInt("compraid"));
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

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.ITEM_DE_COMPRA_UPDATE);
            pstm.setInt(1, objeto.getQuantidade());
            pstm.setFloat(2, objeto.getProduto().getId());
            pstm.setFloat(3, objeto.getSubTotal());
            pstm.setInt(4, objeto.getCompraId());
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
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.ITEM_DE_COMPRA_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }
    
    public List<ItemDeCompra> RetrieveListaDeUmaCompra(int idDaVenda) {

        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.ITEM_DE_VENDA_RETRIVE_ALL_POR_VENDAID);
            pstm.setInt(1, idDaVenda);
            rs = pstm.executeQuery();

            List<ItemDeCompra> itensDeVenda = new ArrayList<>();

            while (rs.next()) {
                ItemDeCompra itemDeVenda = new ItemDeCompra.ItemDeCompraBuilder()
                        .setId(rs.getInt("id"))//1
                        .setQuantidade(rs.getInt("quantidade"))//3
                        .setCompraId(rs.getInt("compraid")
                        )//6   
                        .setProduto(
                                service.ServiceProduto.Buscar(rs.getInt("produtoid"))
                        )//4
                        .setSubTotal(rs.getFloat("subtotal"))//5
                        .createItemDeCompra();
                itensDeVenda.add(itemDeVenda);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return itensDeVenda;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ItemDeVendaDAO->RetrieveListaDeUmaVenda\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }

    }

}

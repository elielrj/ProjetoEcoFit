package model.DAO;

import java.util.List;
import model.bo.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.bo.ContaAPagar;

public class CompraDAO implements InterfaceDAO<Compra> {

    @Override
    public void Create(Compra objeto) {

        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.COMPRA_CREATE);
            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());
            pstm.setString(3, objeto.getDataDeVencimento());
            pstm.setString(4, objeto.getObservacao());
            pstm.setFloat(5, objeto.getValorDeDesconto());
            pstm.setFloat(6, objeto.getValorTotal());
            pstm.setBoolean(7, objeto.getStatus());
            pstm.setInt(8, objeto.getFornecedor().getId());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: CompraDAO->Create\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }

    }

    @Override
    public List<Compra> Retrieve() {

        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList();

        try {
            pstm = conexao.prepareStatement(SQL.COMPRA_RETRIVE_ALL);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Compra compra = new Compra.CompraBuilder()
                        .setId(rs.getInt("id"))
                        .setData(rs.getString("datacompra"))
                        .setHora(rs.getString("hora"))
                        .setDataDeVencimento(rs.getString("datavencimento"))
                        .setObservacao(rs.getString("observacao"))
                        .setValorDeDesconto(rs.getFloat("valordesconto"))
                        .setValorTotal(rs.getFloat("valortotal"))
                        .setStatus(rs.getBoolean("status"))
                        .setFornecedor(
                                service.ServiceFornecedor.Buscar(rs.getInt("fornecedorid"))
                        )
                        .createCompra();

                compras.add(compra);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return compras;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: CompraDAO->RetriveALL\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public Compra Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(SQL.COMPRA_RETRIVE_ONE_ID);
            pstm.setInt(1, id);

            rs = pstm.executeQuery();
            Compra compra = new Compra.CompraBuilder().createCompra();

            while (rs.next()) {
                compra.setId(rs.getInt("id"));
                compra.setData(rs.getString("datacompra"));
                compra.setHora(rs.getString("hora"));

                compra.setDataDeVencimento(rs.getString("datavencimento"));
                compra.setObservacao(rs.getString("observacao"));
                compra.setValorDeDesconto(rs.getFloat("valordesconto"));

                compra.setValorTotal(rs.getFloat("valortotal"));

                compra.setStatus(rs.getBoolean("status"));

                compra.setFornecedor(
                        service.ServiceFornecedor.Buscar(rs.getInt("fornecedorid"))
                );
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return compra;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: CompraDAO->Retrive\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public void Update(Compra objeto) {
        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.COMPRA_UPDATE);
            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());
            pstm.setString(3, objeto.getDataDeVencimento());
            pstm.setString(4, objeto.getObservacao());
            pstm.setFloat(5, objeto.getValorDeDesconto());
            pstm.setFloat(6, objeto.getValorTotal());
            pstm.setBoolean(7, objeto.getStatus());
            pstm.setInt(8, objeto.getFornecedor().getId());
            pstm.setInt(9, objeto.getId());

            pstm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public void Delete(Compra objeto) {
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.COMPRA_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();

            ContaAPagar contaAPagar = service.ServiceContaAPagar.BuscarIdDaContaAPagarPeloIdDaCompra(objeto.getId());
            service.ServiceContaAPagar.Deletar(contaAPagar);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    public int Retrieve(Compra compra) {
        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(SQL.COMPRA_RETRIVE_COMPRA_OBJ);
            pstm.setFloat(1, compra.getValorTotal());
            pstm.setInt(2, compra.getFornecedor().getId());
            pstm.setString(3, compra.getData());
            rs = pstm.executeQuery();

            while (rs.next()) {
                compra.setId(rs.getInt("id"));//1
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return compra.getId();
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: VendaDAO->Retrive\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public void Delete(int idDaCompra) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.COMPRA_DELETE);
            pstm.setInt(1, idDaCompra);
            pstm.executeUpdate();
            ContaAPagar contaAPagar = service.ServiceContaAPagar.BuscarIdDaContaAPagarPeloIdDaCompra(idDaCompra);
            service.ServiceContaAPagar.Deletar(contaAPagar);
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: CompraDAO->Delete\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
}

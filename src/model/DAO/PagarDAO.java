package model.DAO;

import java.util.List;
import model.bo.Pagar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DAO.SQL.SQL;

public class PagarDAO implements InterfaceDAO<Pagar> {

    @Override
    public void Create(Pagar objeto) {
        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.PAGAR_CREATE);
            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());
            pstm.setDouble(3, objeto.getValorDeDescontoNegociado());
            pstm.setDouble(4, objeto.getValorDeAcrescimo());
            pstm.setDouble(5, objeto.getValorPago());
            pstm.setString(6, objeto.getObservacao());
            pstm.setInt(7, objeto.getCompra().getId());
            pstm.setBoolean(8, objeto.getStatus());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public List<Pagar> Retrieve() {
        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(SQL.PAGAR_RETRIVE_ALL);
            rs = pstm.executeQuery();

            List<Pagar> pagamentos = new ArrayList();

            while (rs.next()) {
                Pagar pagamento = new Pagar.PagarBuilder()
                        .setId(rs.getInt("id"))
                        .setData(rs.getString("datapagamento"))
                        .setHora(rs.getString("hora"))
                        .setValorDeDescontoNegociado(rs.getFloat("valordedescontoNegociado"))
                        .setValorDeAcrescimo(rs.getFloat("valoracrescimo"))
                        .setValorPago(rs.getFloat("valorpago"))
                        .setObservacao(rs.getString("observacao"))
                        .setCompra(
                                service.ServiceCompra.Buscar(rs.getInt("compraid"))
                        )
                        .setStatus(rs.getBoolean("status"))
                        .createPagar();

                pagamentos.add(pagamento);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return pagamentos;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public Pagar Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(SQL.PAGAR_RETRIVE_ONE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            Pagar pagamento = new Pagar.PagarBuilder().createPagar();

            while (rs.next()) {
                pagamento.setId(rs.getInt("id"));
                pagamento.setData(rs.getString("datapagamento"));
                pagamento.setHora(rs.getString("hora"));
                pagamento.setValorDeDescontoNegociado(rs.getFloat("valordesconto"));
                pagamento.setValorDeAcrescimo(rs.getFloat("valoracrescimo"));
                pagamento.setValorPago(rs.getFloat("valorpago"));
                pagamento.setObservacao(rs.getString("observacao"));
                pagamento.setCompra(
                        service.ServiceCompra.Buscar(rs.getInt("compraid"))
                );
                pagamento.setStatus(rs.getBoolean("status"));

            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return pagamento;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public void Update(Pagar objeto) {
        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.PAGAR_UPDATE);
            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());
            pstm.setDouble(3, objeto.getValorDeDescontoNegociado());
            pstm.setDouble(4, objeto.getValorDeAcrescimo());
            pstm.setDouble(5, objeto.getValorPago());
            pstm.setString(6, objeto.getObservacao());
            pstm.setInt(7, objeto.getCompra().getId());
            pstm.setBoolean(8, objeto.getStatus());
            pstm.setInt(9, objeto.getId());

            pstm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public void Delete(Pagar objeto) {

        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.PAGAR_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

}

package model.DAO;

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

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.PAGAR_CREATE);
            pstm.setString(1, objeto.getDataPagamento());
            pstm.setString(2, objeto.getHora());
            pstm.setFloat(3, objeto.getValorAcrescimo());
            pstm.setFloat(4, objeto.getValorPago());
            pstm.setString(5, objeto.getObservacao());
            pstm.setInt(6, objeto.getContaAPagar().getId());
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
                        .setDataPagamento(rs.getString("datapagamento"))
                        .setHora(rs.getString("hora"))
                        .setValorAcrescimo(rs.getFloat("valoracrescimo"))
                        .setValorPago(rs.getFloat("valorpago"))
                        .setObservacao(rs.getString("observacao"))
                        .setContaAPagar(
                                service.ServiceContaAPagar.Buscar(rs.getInt("contaapagarid"))
                        )
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
                pagamento.setDataPagamento(rs.getString("datapagamento"));
                pagamento.setHora(rs.getString("hora"));
                pagamento.setValorAcrescimo(rs.getFloat("valoracrescimo"));
                pagamento.setValorPago(rs.getFloat("valorpago"));
                pagamento.setObservacao(rs.getString("observacao"));
                pagamento.setContaAPagar(
                        service.ServiceContaAPagar.Buscar(rs.getInt("contaapagarid"))
                );
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
            pstm.setString(1, objeto.getDataPagamento());
            pstm.setString(2, objeto.getHora());
            pstm.setFloat(3, objeto.getValorAcrescimo());
            pstm.setFloat(4, objeto.getValorPago());
            pstm.setString(5, objeto.getObservacao());
            pstm.setInt(6, objeto.getContaAPagar().getId());
            pstm.setInt(7, objeto.getId());

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
    public void Delete(int idPagar){
        Delete(Retrieve(idPagar));
    }

}

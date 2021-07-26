package model.DAO;

import java.util.List;
import model.bo.Receber;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReceberDAO implements InterfaceDAO<Receber> {

    @Override
    public void Create(Receber objeto) {
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(SQL.RECEBER_CREATE);
            pstm.setString(1, objeto.getDataRecebimento());
            pstm.setString(2, objeto.getHora());
            pstm.setFloat(3, objeto.getValorAcrescimo());
            pstm.setFloat(4, objeto.getValorRecebido());
            pstm.setString(5, objeto.getObservacao());
            pstm.setInt(6, objeto.getContaAReceber().getId());
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
                        .setDataRecebimento(rs.getString("data"))
                        .setHora(rs.getString("hora"))
                        .setValorAcrescimo(rs.getFloat("valorDeAcrescimo"))
                        .setValorRecebido(rs.getFloat("valorRecebido"))
                        .setObservacao(rs.getString("observacao"))
                        .setContaAReceber(
                                service.ServiceContaAReceber.Buscar(rs.getInt("contaareceberid"))
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
                recebimento.setDataRecebimento(rs.getString("datarecebimento"));
                recebimento.setHora(rs.getString("hora"));
                recebimento.setValorAcrescimo(rs.getFloat("valoracrescimo"));
                recebimento.setValorRecebido(rs.getFloat("valorrecebido"));
                recebimento.setObservacao(rs.getString("observacao"));
                recebimento.setContaAReceber(
                        service.ServiceContaAReceber.Buscar(rs.getInt("contaareceberid"))
                );
            }
            return recebimento;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: VendaDAO->Delete IdVenda\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        } finally {
            ConectionFactory.closeConnection(conexao, pstm, rs);
        }
    }

    @Override
    public void Update(Receber objeto) {
        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.RECEBER_UPDATE);
            pstm.setString(1, objeto.getDataRecebimento());
            pstm.setString(2, objeto.getHora());
            pstm.setDouble(3, objeto.getValorAcrescimo());
            pstm.setDouble(4, objeto.getValorRecebido());
            pstm.setString(5, objeto.getObservacao());
            pstm.setInt(6, objeto.getContaAReceber().getId());
            pstm.setInt(7, objeto.getId());

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

    public Receber RetrievePorUmaIdVendaRecebido(int idVenda) {
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conexao.prepareStatement(SQL.RECEBER_RETRIVE_ONE_ID_DA_CONTA_A_RECEBER);
            pstm.setInt(1, idVenda);
            rs = pstm.executeQuery();
            Receber recebimento = new Receber.ReceberBuilder().createReceber();
            while (rs.next()) {
                recebimento.setId(rs.getInt("id"));
                recebimento.setDataRecebimento(rs.getString("datarecebimento"));
                recebimento.setHora(rs.getString("hora"));
                recebimento.setValorAcrescimo(rs.getFloat("valoracrescimo"));
                recebimento.setValorRecebido(rs.getFloat("valorrecebido"));
                recebimento.setObservacao(rs.getString("observacao"));
                recebimento.setContaAReceber(
                        service.ServiceContaAReceber.Buscar(rs.getInt("contaareceberid"))
                );
            }
            return recebimento;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: VendaDAO->Delete IdVenda\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        } finally {
            ConectionFactory.closeConnection(conexao, pstm, rs);
        }
    }
}

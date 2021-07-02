package model.DAO;

import model.DAO.Principal.ConectionFactory;
import model.DAO.Principal.InterfaceDAO;
import java.util.List;
import model.bo.Faturamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FaturamentoDAO implements InterfaceDAO<Faturamento> {

    @Override
    public void Create(Faturamento objeto) {

        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "INSERT INTO faturamento(data,hora,dataDeVencimento,observacao,valorDoDesconto,valorTotal,status, pessoaFisicaId,userCaixa) VALUES(?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());
            pstm.setString(3, objeto.getDataDeVencimento());
            pstm.setString(4, objeto.getObservacao());
            pstm.setFloat(5, objeto.getValorDoDesconto());
            pstm.setFloat(6, objeto.getValorTotal());
            pstm.setBoolean(7, objeto.getStatus());
            pstm.setInt(8, objeto.getPessoaFisica().getId());
            pstm.setString(9, objeto.getUserCaixa());
            pstm.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException("Erro criar Faturamento DAO: " + ex);
        }

    }

    @Override
    public List<Faturamento> Retrieve() {

        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id,data,hora,dataDeVencimento,observacao,valorDoDesconto,valorTotal,status,pessoaFisicaId,userCaixa FROM faturamento";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<Faturamento> faturamentos = new ArrayList();

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Faturamento faturamento = new Faturamento();
                faturamento.setId(rs.getInt("id"));
                faturamento.setData(rs.getString("data"));
                faturamento.setHora(rs.getString("hora"));
                faturamento.setDataDeVencimento(rs.getString("dataDeVencimento"));
                faturamento.setObservacao(rs.getString("observacao"));
                faturamento.setValorDoDesconto(rs.getFloat("valorDoDesconto"));
                faturamento.setValorTotal(rs.getFloat("valorTotal"));

                PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
                faturamento.setPessoaFisica(pessoaFisicaDAO.Retrieve(rs.getInt("pessoaFisicaId")));
                faturamento.setUserCaixa((rs.getString("userCaixa")));

                faturamentos.add(faturamento);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return faturamentos;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao retornar Faturamento por lista DAO: " + ex);

        }
    }

    @Override
    public Faturamento Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id,data,hora,dataDeVencimento,observacao,valorDoDesconto,valorTotal,status,pessoaFisicaId,userCaixa FROM faturamento WHERE faturamento.id=?";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            Faturamento faturamento = new Faturamento();

            while (rs.next()) {
                faturamento.setId(rs.getInt("id"));
                faturamento.setData(rs.getString("data"));
                faturamento.setHora(rs.getString("hora"));
                faturamento.setDataDeVencimento(rs.getString("dataDeVencimento"));
                faturamento.setObservacao(rs.getString("observacao"));
                faturamento.setValorDoDesconto(rs.getFloat("valorDoDesconto"));
                faturamento.setValorTotal(rs.getFloat("valorTotal"));
                faturamento.setStatus(rs.getBoolean("status"));

                PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
                faturamento.setPessoaFisica(pessoaFisicaDAO.Retrieve(rs.getInt("pessoaFisicaId")));

                faturamento.setUserCaixa(rs.getString("userCaixa"));

            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return faturamento;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar Faturamento por id DAO: " + ex);
        }
    }

    @Override
    public void Update(Faturamento objeto) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "UPDATE faturamento SET data=?,hora=?,dataDeVencimento= ?,observacao=?,valorDoDesconto=?,valorTotal =?, status=?,pessoaFisicaId=?,userCaixa=? WHERE faturamento.id=?";

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);

            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());
            pstm.setString(3, objeto.getDataDeVencimento());
            pstm.setString(4, objeto.getObservacao());
            pstm.setDouble(5, objeto.getValorDoDesconto());
            pstm.setDouble(6, objeto.getValorTotal());
            pstm.setBoolean(7, objeto.getStatus());
            pstm.setInt(8, objeto.getPessoaFisica().getId());
            pstm.setString(9, objeto.getUserCaixa());
            pstm.setInt(10, objeto.getId());
            pstm.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao ATUALIZAR Faturamento: " + ex);
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public void Delete(Faturamento objeto) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "DELETE FROM faturamento WHERE id = ?";
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar Faturamento: " + ex);
        }
        
    }

    public int buscarId(float valorTotal, int idCliente, String dataFaturamento) {
        try {
            Connection conexao = ConectionFactory.getConection();
            String sqlExecutar = "SELECT id FROM faturamento WHERE valorTotal=? and pessoaFisicaId=? and data=?";
            PreparedStatement pstm = null;
            ResultSet rs = null;

            pstm = conexao.prepareStatement(sqlExecutar);
            
            pstm.setFloat(1, valorTotal);
            pstm.setInt(2, idCliente);
            pstm.setString(3, dataFaturamento);

            int idFaturamento = 0;
            rs = pstm.executeQuery();

            while (rs.next()) {
                idFaturamento = rs.getInt("id");
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);

            return idFaturamento;
        } catch (Exception ex) {
            throw new RuntimeException("Erro na busca do Id_Venda dao: " + ex);
        }
    }

}

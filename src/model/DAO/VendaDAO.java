package model.DAO;

import java.util.List;
import model.bo.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VendaDAO implements InterfaceDAO<Venda> {

    @Override
    public void Create(Venda objeto) {

        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "INSERT INTO venda(data,hora,dataDeVencimento,observacao,valorDoDesconto,valorTotal,status, pessoaFisicaId) VALUES(?,?,?,?,?,?,?,?)";

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

            pstm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public List<Venda> Retrieve() {

        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id,data,hora,dataDeVencimento,observacao,valorDoDesconto,valorTotal,status,pessoaFisicaId FROM venda";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<Venda> vendas = new ArrayList();

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id"));
                venda.setData(rs.getString("data"));
                venda.setHora(rs.getString("hora"));
                venda.setDataDeVencimento(rs.getString("dataDeVencimento"));
                venda.setObservacao(rs.getString("observacao"));
                venda.setValorDoDesconto(rs.getFloat("valorDoDesconto"));
                venda.setValorTotal(rs.getFloat("valorTotal"));

                PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
                venda.setPessoaFisica(pessoaFisicaDAO.Retrieve(rs.getInt("pessoaFisicaId")));

                vendas.add(venda);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return vendas;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public Venda Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id,data,hora,dataDeVencimento, observacao, valorDoDesconto, valorTotal,status,pessoaFisicaId FROM venda WHERE venda.id=?";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            Venda venda = new Venda();

            while (rs.next()) {
                venda.setId(rs.getInt("id"));
                venda.setData(rs.getString("data"));
                venda.setHora(rs.getString("hora"));
                venda.setDataDeVencimento(rs.getString("dataDeVencimento"));
                venda.setObservacao(rs.getString("observacao"));
                venda.setValorDoDesconto(rs.getFloat("valorDoDesconto"));
                venda.setValorTotal(rs.getFloat("valorTotal"));
                venda.setStatus(rs.getBoolean("status"));

                PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
                venda.setPessoaFisica(pessoaFisicaDAO.Retrieve(rs.getInt("pessoaFisicaId")));

            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return venda;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public void Update(Venda objeto) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "UPDATE venda SET data = ?, hora = ?, dataDeVencimento= ?,observacao = ?, valorDoDesconto = ?, valorTotal =?, status = ?, pessoaFisicaId = ? WHERE id=?";

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
            pstm.setInt(9, objeto.getId());
            pstm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public void Delete(Venda objeto) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "DELETE FROM venda WHERE id = ?";
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Retrive->bairroDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public int Retrieve(Venda venda) {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id FROM venda WHERE venda.total=? venda.pessoafisicaid=? and venda.data=?";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setFloat(1, venda.getValorTotal());
            pstm.setInt(2, venda.getPessoaFisica().getId());
            pstm.setString(3, venda.getData());
            rs = pstm.executeQuery();

            while (rs.next()) {
                venda.setId(rs.getInt("id"));//1
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return venda.getId();
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
        }
    }

}

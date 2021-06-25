package model.DAO;

import model.DAO.FornecedorDAO;
import model.DAO.Principal.ConectionFactory;
import model.DAO.Principal.InterfaceDAO;
import java.util.List;
import model.bo.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CompraDAO implements InterfaceDAO<Compra> {

    @Override
    public void Create(Compra objeto) {

        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "INSERT INTO compra(data,hora,dataDeVencimento,observacao,valorDoDesconto,valorTotal,status,fornecedorId) VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);

            pstm.setString(1, objeto.getData());
            pstm.setString(2, objeto.getHora());
            pstm.setString(3, objeto.getDataDeVencimento());
            pstm.setString(4, objeto.getObservacao());
            pstm.setDouble(5, objeto.getValorDeDesconto());
            pstm.setDouble(6, objeto.getValorTotal());
            pstm.setBoolean(7, objeto.getStatus());
            pstm.setInt(8, objeto.getFornecedor().getId());
            pstm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public List<Compra> Retrieve() {

        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "SELECT id,data,hora,dataDeVencimento,observacao,valorDoDesconto,valorTotal,status,fornecedorId FROM compra";
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList();

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setData(rs.getString("data"));
                compra.setHora(rs.getString("hora"));

                compra.setDataDeVencimento(rs.getString("dataDeVencimento"));
                compra.setObservacao(rs.getString("observacao"));
                compra.setValorDeDesconto(rs.getFloat("valorDoDesconto"));

                compra.setValorTotal(rs.getFloat("valorTotal"));
                compra.setStatus(rs.getBoolean("status"));
                FornecedorDAO fornecedorDAO = new FornecedorDAO();
                compra.setFornecedor(fornecedorDAO.Retrieve(rs.getInt("fornecedorId")));

                compras.add(compra);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return compras;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public Compra Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id,data,hora,dataDeVencimento,observacao,valorDoDesconto,valorTotal,status, fornecedorId FROM compra WHERE compra.id = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, id);

            rs = pstm.executeQuery();
            Compra compra = new Compra();

            while (rs.next()) {
                compra.setId(rs.getInt("id"));
                compra.setData(rs.getString("data"));
                compra.setHora(rs.getString("hora"));

                compra.setDataDeVencimento(rs.getString("dataDeVencimento"));
                compra.setObservacao(rs.getString("observacao"));
                compra.setValorDeDesconto(rs.getFloat("valorDoDesconto"));

                compra.setValorTotal(rs.getFloat("valorTotal"));

                compra.setStatus(rs.getBoolean("status"));

                FornecedorDAO fornecedorDAO = new FornecedorDAO();
                compra.setFornecedor(fornecedorDAO.Retrieve(rs.getInt("fornecedorId")));
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return compra;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public void Update(Compra objeto) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "UPDATE compra SET data=?, hora=?, dataDeVencimento=  ?, observacao=?, valorDoDesconto=?, valorTotal=?,status=?, fornecedorId=? WHERE id=?";

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
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
        String sqlExecutar = "DELETE FROM compra WHERE id =?";
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

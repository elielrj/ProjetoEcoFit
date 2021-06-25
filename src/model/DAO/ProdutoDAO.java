package model.DAO;

import model.DAO.Principal.ConectionFactory;
import model.DAO.Principal.InterfaceDAO;
import java.util.List;
import model.bo.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutoDAO implements InterfaceDAO<Produto> {

    @Override
    public void Create(Produto objeto) {

        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "INSERT INTO produto(descricao,unidadeDeCompra,unidadeDeVenda, correlacaoUnidade, valor,quantidadeDeEstoque, codigoDeBarras, status, observacao) VALUES(?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, objeto.getUnidadeDeCompra());
            pstm.setString(3, objeto.getUnidadeDeVenda());
            pstm.setString(4, objeto.getCorrelacaoUnidade());
            pstm.setDouble(5, objeto.getValor());
            pstm.setInt(6, objeto.getQuantidadeDeEstoque());
            pstm.setString(7, objeto.getCodigoDeBarras());
            pstm.setBoolean(8, objeto.getStatus());
            pstm.setString(9, objeto.getObservacao());

            pstm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public List<Produto> Retrieve() {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id, descricao,unidadeDeCompra,unidadeDeVenda, correlacaoUnidade, valor,quantidadeDeEstoque, codigoDeBarras,status,observacao  FROM produto";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();

            List<Produto> produtos = new ArrayList();

            while (rs.next()) {
                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setUnidadeDeCompra(rs.getString("unidadeDeCompra"));
                produto.setUnidadeDeVenda(rs.getString("unidadeDeVenda"));
                produto.setCorrelacaoUnidade(rs.getString("correlacaoUnidade"));
                produto.setValor(rs.getFloat("valor"));
                produto.setQuantidadeDeEstoque(rs.getInt("quantidadeDeEstoque"));
                produto.setCodigoDeBarras(rs.getString("codigoDeBarras"));
                produto.setStatus(rs.getBoolean("status"));
                produto.setObservacao(rs.getString("observacao"));

                produtos.add(produto);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return produtos;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public Produto Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id, descricao,unidadeDeCompra,unidadeDeVenda, correlacaoUnidade, valor,quantidadeDeEstoque, codigoDeBarras, status,observacao FROM produto WHERE id=?";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            Produto produto = new Produto();

            while (rs.next()) {

                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setUnidadeDeCompra(rs.getString("unidadeDeCompra"));
                produto.setUnidadeDeVenda(rs.getString("unidadeDeVenda"));
                produto.setCorrelacaoUnidade(rs.getString("correlacaoUnidade"));
                produto.setValor(rs.getFloat("valor"));
                produto.setQuantidadeDeEstoque(rs.getInt("quantidadeDeEstoque"));
                produto.setCodigoDeBarras(rs.getString("codigoDeBarras"));
                produto.setStatus(rs.getBoolean("status"));
                produto.setObservacao(rs.getString("observacao"));

            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return produto;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public void Update(Produto objeto) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "UPDATE produto SET descricao = ?, unidadeDeCompra =?, unidadeDeVenda =?,correlacaoUnidade =?,valor =?,quantidadeDeEstoque =?,codigoDeBarras =?, status=?, observacao=? WHERE id=?";

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, objeto.getUnidadeDeCompra());
            pstm.setString(3, objeto.getUnidadeDeVenda());
            pstm.setString(4, objeto.getCorrelacaoUnidade());
            pstm.setDouble(5, objeto.getValor());
            pstm.setInt(6, objeto.getQuantidadeDeEstoque());
            pstm.setString(7, objeto.getCodigoDeBarras());
            pstm.setBoolean(8, objeto.getStatus());
            pstm.setString(9, objeto.getObservacao());
            pstm.setInt(10, objeto.getId());

            pstm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public void Delete(Produto objeto) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "DELETE FROM produto WHERE id = ?";
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

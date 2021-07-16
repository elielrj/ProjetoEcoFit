package model.DAO;

import java.util.List;
import model.bo.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DAO.SQL.SQL;
import model.bo.Estoque;

public class ProdutoDAO implements InterfaceDAO<Produto> {

    @Override
    public void Create(Produto objeto) {

        Connection conexao = ConectionFactory.getConection();

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(SQL.PRODUTO_CREATE);
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, objeto.getUnidadeDeCompra());
            pstm.setString(3, objeto.getUnidadeDeVenda());
            pstm.setString(4, objeto.getCorrelacaoUnidade());
            pstm.setDouble(5, objeto.getValor());
            pstm.setString(6, objeto.getCodigoDeBarras());
            pstm.setBoolean(7, objeto.getStatus());
            pstm.setString(8, objeto.getObservacao());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Create\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
		);
}
    }

    @Override
    public List<Produto> Retrieve() {
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(SQL.PRODUTO_RETRIVE_ALL);
            rs = pstm.executeQuery();

            List<Produto> produtos = new ArrayList();

            while (rs.next()) {
                Produto produto = new Produto.ProdutoBuilder()
                        .setId(rs.getInt("id"))
                        .setDescricao(rs.getString("descricao"))
                        .setUnidadeDeCompra(rs.getString("unidadeDeCompra"))
                        .setUnidadeDeVenda(rs.getString("unidadeDeVenda"))
                        .setCorrelacaoUnidade(rs.getString("correlacaoUnidade"))
                        .setValor(rs.getFloat("valor"))
                        .setCodigoDeBarras(rs.getString("codigoDeBarras"))
                        .setStatus(rs.getBoolean("status"))
                        .setObservacao(rs.getString("observacao"))
                        .createProduto();
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
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conexao.prepareStatement(SQL.PRODUTO_RETRIVE_ONE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            Produto produto = new Produto.ProdutoBuilder().createProduto();

            while (rs.next()) {

                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setUnidadeDeCompra(rs.getString("unidadeDeCompra"));
                produto.setUnidadeDeVenda(rs.getString("unidadeDeVenda"));
                produto.setCorrelacaoUnidade(rs.getString("correlacaoUnidade"));
                produto.setValor(rs.getFloat("valor"));
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

    public Produto Retrieve(String codigodeBarrasDoProduto) {
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conexao.prepareStatement(SQL.PRODUTO_RETRIVE_ONE_COD_BARRAS);
            pstm.setString(1, codigodeBarrasDoProduto);
            rs = pstm.executeQuery();
            Produto produto = new Produto.ProdutoBuilder().createProduto();
            while (rs.next()) {
                produto.setId(rs.getInt("id"));//1
                produto.setDescricao(rs.getString("descricao"));//2
                produto.setUnidadeDeCompra(rs.getString("unidadeDeCompra"));//3
                produto.setUnidadeDeVenda(rs.getString("unidadeDeVenda"));//4
                produto.setCorrelacaoUnidade(rs.getString("correlacaoUnidade"));//5
                produto.setValor(rs.getFloat("valor"));//6
                produto.setCodigoDeBarras(rs.getString("codigoDeBarras"));//7
                produto.setStatus(rs.getBoolean("status"));//8
                produto.setObservacao(rs.getString("observacao"));//9
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
        PreparedStatement pstm = null;
        try {
            pstm = conexao.prepareStatement(SQL.PRODUTO_UPDATE);
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, objeto.getUnidadeDeCompra());
            pstm.setString(3, objeto.getUnidadeDeVenda());
            pstm.setString(4, objeto.getCorrelacaoUnidade());
            pstm.setDouble(5, objeto.getValor());
            pstm.setString(6, objeto.getCodigoDeBarras());
            pstm.setBoolean(7, objeto.getStatus());
            pstm.setString(8, objeto.getObservacao());
            pstm.setInt(9, objeto.getId());
            pstm.executeUpdate();
            
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: EstoqueDAO->Update->estoqueDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
        
    }

    @Override
    public void Delete(Produto objeto) {
        Connection conexao = ConectionFactory.getConection();
        
        PreparedStatement pstm = null;

        try {
            Estoque estoque = service.ServiceEstoque.BuscarEstoquePorIdDoProduto(objeto.getId());
            service.ServiceEstoque.Deletar(estoque);
            
            pstm = conexao.prepareStatement(SQL.PRODUTO_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }
    
     public void Delete(int idProduto) {
        Connection conexao = ConectionFactory.getConection();
        
        PreparedStatement pstm = null;

        try {
            Estoque objeto = service.ServiceEstoque.Buscar(idProduto);
            service.ServiceEstoque.Deletar(objeto);
            
            pstm = conexao.prepareStatement(SQL.PRODUTO_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }
}

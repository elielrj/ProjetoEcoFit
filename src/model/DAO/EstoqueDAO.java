package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.bo.Estoque;
import java.util.ArrayList;
import model.DAO.SQL.SQL;

public class EstoqueDAO implements InterfaceDAO<Estoque> {

    @Override
    public void Create(Estoque objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.ESTOQUE_CREATE);
            pstm.setInt(1, objeto.getProdutoId());
            pstm.setInt(2, objeto.getQuantidade());        
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: EstoqueDAO->Create->estoqueDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public List<Estoque> Retrieve() {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.ESTOQUE_RETRIVE_ALL);
            rs = pstm.executeQuery();
            List<Estoque> estoques = new ArrayList();
            while (rs.next()) {
                Estoque estoque = new Estoque.EstoqueBuilder()
                        .setId(rs.getInt("id"))
                        .setProdutoId(rs.getInt("produtoid"))
                        .setQuantidade(rs.getInt("quantidade"))
                        .createEstoque();
                estoques.add(estoque);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return estoques;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: EstoqueDAO->Retrive->estoqueDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public Estoque Retrieve(int id) {
        try {
            Connection conexao = ConectionFactory.getConection();

            //String sqlExecutar = "SELECT id,nome,status FROM estoque WHERE estoque.id=?";
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.ESTOQUE_RETRIVE_ONE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            Estoque estoque = new Estoque.EstoqueBuilder().createEstoque();
            while (rs.next()) {
                estoque.setId(rs.getInt("id"));
                estoque.setProdutoId(rs.getInt("produtoid"));
                estoque.setQuantidade(rs.getInt("quantidade"));
            }

            ConectionFactory.closeConnection(conexao, pstm, rs);
            return estoque;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: EstoqueDAO->Retrive(int id)->estoqueDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
    
    public int RetrieveDeIdEstoquePeloIdProduto(int id) {
        try {
            Connection conexao = ConectionFactory.getConection();

            //String sqlExecutar = "SELECT id,nome,status FROM estoque WHERE estoque.id=?";
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.ESTOQUE_RETRIVE_ONE_ID_DO_ESTOQUE);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            Estoque estoque = new Estoque.EstoqueBuilder().createEstoque();
            while (rs.next()) {
                estoque.setId(rs.getInt("id"));
                
            }

            ConectionFactory.closeConnection(conexao, pstm, rs);
            return estoque.getId();
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: EstoqueDAO->Retrive(int id)->estoqueDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public void Update(Estoque objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.ESTOQUE_UPDATE);
            pstm.setInt(1, objeto.getProdutoId());
            pstm.setInt(2, objeto.getQuantidade());
            pstm.setInt(3, objeto.getId());
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
    public void Delete(Estoque objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.ESTOQUE_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: EstoqueDAO->Delete->estoqueDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }


public Estoque RetrievePorIdDoProduto(int idProdutoDoEstoque) {
        try {
            Connection conexao = ConectionFactory.getConection();

            //String sqlExecutar = "SELECT id,nome,status FROM estoque WHERE estoque.id=?";
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.ESTOQUE_RETRIVE_ONE_ID_PRODUTO_DO_ESTOQUE);
            pstm.setInt(1, idProdutoDoEstoque);
            rs = pstm.executeQuery();
            Estoque estoque = new Estoque.EstoqueBuilder().createEstoque();
            while (rs.next()) {
                estoque.setId(rs.getInt("id"));
                estoque.setProdutoId(rs.getInt("produtoid"));
                estoque.setQuantidade(rs.getInt("quantidade"));
            }

            ConectionFactory.closeConnection(conexao, pstm, rs);
            return estoque;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: EstoqueDAO->Retrive(int id)->estoqueDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
    
}

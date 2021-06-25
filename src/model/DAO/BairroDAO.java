package model.DAO;

import model.DAO.Principal.InterfaceDAO;
import model.DAO.SQL.SqlBairro;
import model.DAO.Principal.PS;
import java.util.List;
import model.bo.Bairro;
import java.util.ArrayList;

public class BairroDAO implements InterfaceDAO<Bairro> {

    private PS ps;

    public BairroDAO() {
        this.ps = new PS(new SqlBairro());
    }

    @Override
    public void Create(Bairro objeto) {
        try {
            ps.criar();
            ps.preparedStatement.setString(1, objeto.getNome());
            ps.preparedStatement.setBoolean(2, objeto.getStatus());
            ps.preparedStatement.executeUpdate();
            ps.fecharConexao();
        } catch (Exception ex) {
            throw new RuntimeException("Erro na cricação de bairroDAO: " + ex);
        }
    }

    @Override
    public List<Bairro> Retrieve() {
        List<Bairro> bairros = new ArrayList();
        try {
            ps.listarTodos();
            while (ps.resultSet.next()) {
                Bairro bairro = new Bairro();
                bairro.setId(ps.resultSet.getInt("id"));
                bairro.setNome(ps.resultSet.getString("nome"));
                bairro.setStatus(ps.resultSet.getBoolean("status"));
                bairros.add(bairro);
            }
            ps.fecharConexao();
            return bairros;
        } catch (Exception ex) {
            throw new RuntimeException("Erro na listagem geral de bairroDAO: " + ex);
        }
    }

    @Override
    public Bairro Retrieve(int id) {
        try {
            ps.listarId(id);
            Bairro bairro = new Bairro();
            while (ps.resultSet.next()) {
                bairro.setId(ps.resultSet.getInt("id"));
                bairro.setNome(ps.resultSet.getString("nome"));
                bairro.setStatus(ps.resultSet.getBoolean("status"));
            }
            ps.fecharConexao();
            return bairro;
        } catch (Exception ex) {
            throw new RuntimeException("Erro na listagem por Id de bairroDAO: " + ex);
        }
    }

    @Override
    public void Update(Bairro objeto) {
        try {
            ps.atualizar();
            ps.preparedStatement.setString(1, objeto.getNome());
            ps.preparedStatement.setBoolean(2, objeto.getStatus());
            ps.preparedStatement.setInt(3, objeto.getId());
            ps.preparedStatement.executeUpdate();
            ps.fecharConexao();
        } catch (Exception ex) {
            throw new RuntimeException("Erro na atualização de bairroDAO: " + ex);
        }
    }

    @Override
    public void Delete(Bairro objeto) {
        try {
            ps.deletar(objeto.getId());
            ps.preparedStatement.executeUpdate();
            ps.fecharConexao();
        } catch (Exception ex) {
            throw new RuntimeException("Erro em deletar bairroDAO: " + ex);
        }
    }
}

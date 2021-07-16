package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.bo.Cidade;
import java.util.ArrayList;
import model.DAO.SQL.SQL;

public class CidadeDAO implements InterfaceDAO<Cidade> {

    @Override
    public void Create(Cidade objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.CIDADE_CREATE);
            pstm.setString(1, objeto.getNome());
            pstm.setBoolean(2, objeto.getStatus());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: CidadeDAO->Create\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public List<Cidade> Retrieve() {

        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.CIDADE_RETRIVE_ALL);
            rs = pstm.executeQuery();
            List<Cidade> cidades = new ArrayList();
            while (rs.next()) {
                Cidade cidade = new Cidade.CidadeBuilder()
                        .setId(rs.getInt("id"))
                        .setNome(rs.getString("nome"))
                        .setStatus(rs.getBoolean("status"))
                        .createCidade();
                cidades.add(cidade);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return cidades;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: CidadeDAO->Retrive\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public Cidade Retrieve(int id) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.CIDADE_RETRIVE_ONE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            Cidade cidade = new Cidade.CidadeBuilder().createCidade();
            while (rs.next()) {
                cidade.setId(rs.getInt("id"));
                cidade.setNome(rs.getString("nome"));
                cidade.setStatus(rs.getBoolean("status"));
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return cidade;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: CidadeDAO->Retrive(id)\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public void Update(Cidade objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.CIDADE_UPDATE);
            pstm.setString(1, objeto.getNome());
            pstm.setBoolean(2, objeto.getStatus());
            pstm.setInt(3, objeto.getId());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: CidadeDAO->Update\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public void Delete(Cidade objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.CIDADE_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: CidadeDAO->Delete\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public void Delete(int idCidade) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.CIDADE_DELETE);
            pstm.setInt(1, idCidade);
            pstm.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: CidadeDAO->Delete\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
}

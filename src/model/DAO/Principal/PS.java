package model.DAO.Principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.DAO.SQL.Sql;

public class PS {

    private Sql sqlExecutar;
    private Connection conexao;
    public static PreparedStatement preparedStatement;
    public static ResultSet resultSet;

    public PS(Sql sqlExecutar) {
        this.sqlExecutar = sqlExecutar;
        this.conexao = ConectionFactory.getConection();
        this.preparedStatement = null;
        this.resultSet = null;
    }

    public void criar() {
        try {
            this.preparedStatement = conexao.prepareStatement(sqlExecutar.criar());
        } catch (Exception ex) {
            throw new RuntimeException("Erro em PS->Criar: " + ex);
        }
    }

    public void listarTodos() {
        try {
            this.preparedStatement = conexao.prepareStatement(sqlExecutar.listarTodos());
            this.resultSet = this.preparedStatement.executeQuery();
        } catch (Exception ex) {
            throw new RuntimeException("Erro em PS->ListarTodos: " + ex);
        }
    }

    public void listarId(int id) {
        try {
            this.preparedStatement = conexao.prepareStatement(sqlExecutar.listarId());
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();
        } catch (Exception ex) {
            throw new RuntimeException("Erro em PS->ListarId: " + ex);
        }
    }

    public void atualizar() {
        try {
            this.preparedStatement = conexao.prepareStatement(sqlExecutar.atualizar());
        } catch (Exception ex) {
            throw new RuntimeException("Erro em PS->Atualizar: " + ex);
        }
    }

    public void deletar(int id) {
        try {
            this.preparedStatement = conexao.prepareStatement(sqlExecutar.deletar());
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();
        } catch (Exception ex) {
            throw new RuntimeException("Erro em PS->Deletar: " + ex);
        }
    }

    public void fecharConexao() {
        if (resultSet == null) {
            ConectionFactory.closeConnection(conexao, preparedStatement);
        } else if (preparedStatement != null) {
            ConectionFactory.closeConnection(conexao, preparedStatement, resultSet);
        } else {
            ConectionFactory.closeConnection(conexao);
        }
    }

}

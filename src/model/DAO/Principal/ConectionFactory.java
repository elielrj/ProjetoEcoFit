package model.DAO.Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public final class ConectionFactory {

    private static final String VAR_DRIVER = "com.mysql.jdbc.Driver";
    private static final String VAR_DATABASE = "jdbc:mysql://localhost:3306/espacoecofit";
    private static final String VAR_USER = "root";
    private static final String VAR_KEY = "root";

    public static Connection getConection() {
        try {
            return DriverManager.getConnection(
                    VAR_DATABASE
                    + "?verifyServerCertificate=false"
                    + "&useSSL=false"
                    + "&requireSSL=false"
                    + "&USER=" + VAR_USER
                    + "&password=" + VAR_KEY
                    + "&serverTimezone=UTC"
            );
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao conectar: " + ex);
        }
    }

    public static void closeConnection(Connection conexao) {
        try {
            if (!conexao.equals(null)) {
                conexao.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar conexão: " + ex);
        }
    }

    public static void closeConnection(Connection conexao, PreparedStatement pstm) {
        try {
            closeConnection(conexao);
            if (!pstm.equals(null)) {
                pstm.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar conexão e PreparedStatement : " + ex);
        }
    }

    public static void closeConnection(Connection conexao, PreparedStatement pstm, ResultSet rst) {
        try {
            closeConnection(conexao, pstm);
            if (!rst.equals(null)) {
                rst.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar conexão, PreparedStatement e ResultSet: " + ex);
        }
    }
}

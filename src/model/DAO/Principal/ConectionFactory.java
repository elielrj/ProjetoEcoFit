
package model.DAO.Principal;

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ConectionFactory {

        private static final String driver = "com.mysql.jdbc.Driver";
        
        private static final String banco = "jdbc:mysql://localhost:3306/espacoecofit";//jdbc:mysql://localhost:3306/espacoecofit?useTimezone=true&serverTimezone=UTC
        private static final String usuario = "root";
        private static final String senha = "root";

        
        
        public static Connection getConection() {
            try{
                
                return DriverManager.getConnection(banco + "?verifyServerCertificate=false"
                    + "&useSSL=false"
                    + "&requireSSL=false"
                    + "&USER=" + usuario + "&password=" + senha + "&serverTimezone=UTC" );
            }
            catch(SQLException ex){
                throw new RuntimeException("Erro ao conectar: " + ex);
            }
        }

        public static void closeConnection(Connection conexao){            
            try{ 
                if(conexao != null){
                    conexao.close();
                }
            }
            catch (SQLException ex){
                throw new RuntimeException("Erro ao fechar conexão: " + ex);
            }
        }
        
        public static void closeConnection(Connection conexao, PreparedStatement pstm){
            closeConnection(conexao);
            try{
                if(pstm != null){
                    pstm.close();
                }
            }catch (Exception ex){
                throw new RuntimeException("Erro ao fechar conexão e PreparedStatement : " + ex);
            }
        }
        
        public static void closeConnection(Connection conexao, PreparedStatement pstm, ResultSet rst){
            closeConnection(conexao, pstm);
            try{
                if(rst != null){
                    rst.close();
                }
            }
            catch (SQLException ex){
                throw new RuntimeException("Erro ao fechar conexão, PreparedStatement e ResultSet: " + ex);
            }
        }

       
}
 

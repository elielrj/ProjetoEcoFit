package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.bo.Bairro;
import java.util.ArrayList;
import model.DAO.SQL.SQL;

public class BairroDAO implements InterfaceDAO<Bairro> {
    
    @Override
    public void Create(Bairro objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            //String sqlExecutar = "INSERT INTO bairro(nome,status) VALUES(?,?)";
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.BAIRRO_CREATE);
            pstm.setString(1, objeto.getNome());
            pstm.setBoolean(2, objeto.getStatus());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao,pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Create->bairroDAO\nMENSAGEM:" 
                    + ex.getMessage() + "\nLOCALIZADO:" 
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public List<Bairro> Retrieve() {
        
        try {
            Connection conexao = ConectionFactory.getConection();
            //String sqlExecutar = "SELECT id,nome,status FROM bairro";
            PreparedStatement pstm = null;
            ResultSet rs = null;
            List<Bairro> bairros = new ArrayList();
            pstm = conexao.prepareStatement(SQL.BAIRRO_RETRIVE_ALL);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Bairro bairro = new Bairro(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getBoolean("status")
                );
                bairros.add(bairro);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return bairros;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Retrive->bairroDAO\nMENSAGEM:" 
                    + ex.getMessage() + "\nLOCALIZADO:" 
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public Bairro Retrieve(int id) {
        try {
            Connection conexao = ConectionFactory.getConection();

            //String sqlExecutar = "SELECT id,nome,status FROM bairro WHERE bairro.id=?";
           
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.BAIRRO_RETRIVE_ONE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            Bairro bairro = new Bairro();
            while (rs.next()){
                bairro.setId(rs.getInt("id"));
                bairro.setNome(rs.getString("nome"));
                bairro.setStatus(rs.getBoolean("status"));
            }

            
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return bairro;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Retrive(int id)->bairroDAO\nMENSAGEM:" 
                    + ex.getMessage() + "\nLOCALIZADO:" 
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public void Update(Bairro objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.BAIRRO_UPDATE);
            pstm.setString(1, objeto.getNome());
            pstm.setBoolean(2, objeto.getStatus());
            pstm.setInt(3, objeto.getId());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Update->bairroDAO\nMENSAGEM:" 
                    + ex.getMessage() + "\nLOCALIZADO:" 
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public void Delete(Bairro objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            //String sqlExecutar = "DELETE FROM bairro WHERE id = ?";
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.BAIRRO_DELETE);
            pstm.setInt(1,objeto.getId());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Delete->bairroDAO\nMENSAGEM:" 
                    + ex.getMessage() + "\nLOCALIZADO:" 
                    + ex.getLocalizedMessage()
            );
        }
    }
    
    @Override
    public void Delete(int idBairro) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.BAIRRO_DELETE);
            pstm.setInt(1,idBairro);
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Delete->bairroDAO\nMENSAGEM:" 
                    + ex.getMessage() + "\nLOCALIZADO:" 
                    + ex.getLocalizedMessage()
            );
        }
    }
}

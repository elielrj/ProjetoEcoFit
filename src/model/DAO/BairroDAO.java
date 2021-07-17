package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.bo.Bairro;
import java.util.ArrayList;

public class BairroDAO implements InterfaceDAO<Bairro> {

    @Override
    public void Create(Bairro objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.BAIRRO_CREATE);
            pstm.setString(1, objeto.getNome());
            pstm.setBoolean(2, objeto.getStatus());
            pstm.setInt(3, objeto.getCidade().getId());            
            pstm.executeUpdate();      
            ConectionFactory.closeConnection(conexao, pstm);
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
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.BAIRRO_RETRIVE_ALL);
            rs = pstm.executeQuery();
            List<Bairro> bairros = new ArrayList();
            while (rs.next()) {
                Bairro bairro = new Bairro.BairroBuilder()
                        .setId(rs.getInt("id"))
                        .setNome(rs.getString("nome"))
                        .setStatus(rs.getBoolean("status"))
                        .setCidade(
                                service.ServiceCidade.Buscar(rs.getInt("cidadeId"))
                        )
                        .createBairro();
                
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
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.BAIRRO_RETRIVE_ONE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            Bairro bairro = new Bairro.BairroBuilder().createBairro();
            while (rs.next()) {
                bairro.setId(rs.getInt("id"));
                bairro.setNome(rs.getString("nome"));
                bairro.setStatus(rs.getBoolean("status"));
                bairro.setCidade(
                    service.ServiceCidade.Buscar(rs.getInt("cidadeid"))
                );
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
            pstm.setInt(3, objeto.getCidade().getId());
            pstm.setInt(4, objeto.getId());
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
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.BAIRRO_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Delete->bairroDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public void Delete(int idBairro) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.BAIRRO_DELETE);
            pstm.setInt(1, idBairro);
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Delete->bairroDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public List<Bairro> RetrieveForCity(int idCidade) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.BAIRRO_RETRIVE_ID_CITY);
            pstm.setInt(1, idCidade);
            rs = pstm.executeQuery();
            List<Bairro> bairros = new ArrayList<>();
            while (rs.next()) {
                Bairro bairro = new Bairro.BairroBuilder().createBairro();
                bairro.setId(rs.getInt("id"));
                bairro.setNome(rs.getString("nome"));
                bairro.setStatus(rs.getBoolean("status"));
                bairro.setCidade(
                        service.ServiceCidade.Buscar(rs.getInt("cidadeId"))
                );
                bairros.add(bairro);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return bairros;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->Retrive(int id)->bairroDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public int RetrieveIdTheCity(int idBairro) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.BAIRRO_RETRIVE_ONE_ID);
            pstm.setInt(1, idBairro);
            rs = pstm.executeQuery();
            Bairro bairro = new Bairro.BairroBuilder().createBairro();
            while (rs.next()) {
                bairro.setCidade(
                        service.ServiceCidade.Buscar(rs.getInt("cidadeId"))
                );
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return bairro.getCidade().getId();
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: BairroDAO->RetrieveIdTheCity(int idBairro)->bairroDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
}

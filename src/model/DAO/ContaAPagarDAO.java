package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.bo.Compra;
import model.bo.ContaAPagar;

public class ContaAPagarDAO implements InterfaceDAO<ContaAPagar> {

    @Override
    public void Create(ContaAPagar objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.CONTA_A_PAGAR_CREATE);
            pstm.setInt(1, objeto.getCompraId());
            pstm.setFloat(2, objeto.getValor());
            pstm.setBoolean(3, objeto.getStatus());            
            pstm.executeUpdate();      
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAPagarDAO->Create->contaAPagarDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );            
        }
    }

    @Override
    public List<ContaAPagar> Retrieve() {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.CONTA_A_PAGAR_RETRIVE_ALL);
            rs = pstm.executeQuery();
            List<ContaAPagar> contaAPagars = new ArrayList();
            while (rs.next()) {
                ContaAPagar contaAPagar = new ContaAPagar.ContaAPagarBuilder()
                        .setId(rs.getInt("id"))
                        .setCompraId(rs.getInt("compraid"))
                        .setValor(rs.getFloat("valor"))
                        .setStatus(rs.getBoolean("status"))
                        .createContaAPagar();
                
                contaAPagars.add(contaAPagar);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return contaAPagars;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAPagarDAO->Retrive->contaAPagarDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public ContaAPagar Retrieve(int id) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.CONTA_A_PAGAR_RETRIVE_ONE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            ContaAPagar contaAPagar = new ContaAPagar.ContaAPagarBuilder().createContaAPagar();
            while (rs.next()) {
                contaAPagar.setId(rs.getInt("id"));
                contaAPagar.setCompraId(rs.getInt("compraid"));
                contaAPagar.setValor(rs.getFloat("valor"));
                contaAPagar.setStatus(rs.getBoolean("status"));
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return contaAPagar;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAPagarDAO->Retrive(int id)->contaAPagarDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public void Update(ContaAPagar objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.CONTA_A_PAGAR_UPDATE);
            pstm.setInt(1, objeto.getCompraId());
            pstm.setFloat(2, objeto.getValor());
            pstm.setBoolean(3, objeto.getStatus());
            pstm.setInt(4, objeto.getId());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAPagarDAO->Update->contaAPagarDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public void Delete(ContaAPagar objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.CONTA_A_PAGAR_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAPagarDAO->Delete->contaAPagarDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
/*
    public void Delete(int idContaAPagar) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.BAIRRO_DELETE);
            pstm.setInt(1, idContaAPagar);
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAPagarDAO->Delete->contaAPagarDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public List<ContaAPagar> RetrieveForCity(int idCidade) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.CONTA_A_PAGAR_RETRIVE_ID_CITY);
            pstm.setInt(1, idCidade);
            rs = pstm.executeQuery();
            List<ContaAPagar> contaAPagars = new ArrayList<>();
            while (rs.next()) {
                ContaAPagar contaAPagar = new ContaAPagar.ContaAPagarBuilder().createContaAPagar();
                contaAPagar.setId(rs.getInt("id"));
                contaAPagar.setNome(rs.getString("nome"));
                contaAPagar.setStatus(rs.getBoolean("status"));
                contaAPagar.setCidade(
                        service.ServiceCidade.Buscar(rs.getInt("cidadeId"))
                );
                contaAPagars.add(contaAPagar);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return contaAPagars;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAPagarDAO->Retrive(int id)->contaAPagarDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
*/
    public ContaAPagar RetrieveIdDaContaAReceberPeloIdDaCompra(int idDaCompra) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.CONTA_A_PAGAR_RETRIVE_ONE_ID_DA_COMPRA);
            pstm.setInt(1, idDaCompra);
            rs = pstm.executeQuery();
            ContaAPagar contaAPagar = new ContaAPagar.ContaAPagarBuilder().createContaAPagar();
            while (rs.next()) {
                contaAPagar.setId(rs.getInt("id"));
                contaAPagar.setCompraId(rs.getInt("compraid"));
                contaAPagar.setValor(rs.getFloat("valor"));
                contaAPagar.setStatus(rs.getBoolean("status"));
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return contaAPagar;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAPagarDAO->RetrieveIdTheCity(int idContaAPagar)->contaAPagarDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
}

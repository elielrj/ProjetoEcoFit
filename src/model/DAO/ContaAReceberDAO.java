package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.bo.ContaAReceber;

public class ContaAReceberDAO implements InterfaceDAO<ContaAReceber> {

    @Override
    public void Create(ContaAReceber objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.CONTA_A_RECEBER_CREATE);
            pstm.setInt(1, objeto.getVendaId());
            pstm.setFloat(2, objeto.getValor());
            pstm.setBoolean(3, objeto.getStatus());            
            pstm.executeUpdate();      
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAReceberDAO->Create\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );            
        }
    }

    @Override
    public List<ContaAReceber> Retrieve() {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.CONTA_A_RECEBER_RETRIVE_ALL);
            rs = pstm.executeQuery();
            List<ContaAReceber> contaARecebers = new ArrayList();
            while (rs.next()) {
                ContaAReceber contaAReceber = new ContaAReceber.ContaAReceberBuilder()
                        .setId(rs.getInt("id"))
                        .setVendaId(rs.getInt("vendaid"))
                        .setValor(rs.getFloat("valor"))
                        .setStatus(rs.getBoolean("status"))
                        .createContaAReceber();
                
                contaARecebers.add(contaAReceber);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return contaARecebers;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAReceberDAO->Retrive\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public ContaAReceber Retrieve(int id) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.CONTA_A_RECEBER_RETRIVE_ONE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            ContaAReceber contaAReceber = new ContaAReceber.ContaAReceberBuilder().createContaAReceber();
            while (rs.next()) {
                contaAReceber.setId(rs.getInt("id"));
                contaAReceber.setVendaId(rs.getInt("nome"));
                contaAReceber.setValor(rs.getFloat("valor"));
                contaAReceber.setStatus(rs.getBoolean("status"));
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return contaAReceber;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAReceberDAO->Retrive(int id)\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public void Update(ContaAReceber objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.CONTA_A_RECEBER_UPDATE);
            pstm.setInt(1, objeto.getVendaId());
            pstm.setFloat(2, objeto.getValor());
            pstm.setBoolean(3, objeto.getStatus());
            pstm.setInt(4, objeto.getId());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAReceberDAO->Update\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public void Delete(ContaAReceber objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.CONTA_A_RECEBER_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAReceberDAO->Delete\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
/*
    public void Delete(int idContaAReceber) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.BAIRRO_DELETE);
            pstm.setInt(1, idContaAReceber);
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAReceberDAO->Delete->contaAReceberDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public List<ContaAReceber> RetrieveForCity(int idCidade) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.CONTA_A_PAGAR_RETRIVE_ID_CITY);
            pstm.setInt(1, idCidade);
            rs = pstm.executeQuery();
            List<ContaAReceber> contaARecebers = new ArrayList<>();
            while (rs.next()) {
                ContaAReceber contaAReceber = new ContaAReceber.ContaAReceberBuilder().createContaAReceber();
                contaAReceber.setId(rs.getInt("id"));
                contaAReceber.setNome(rs.getString("nome"));
                contaAReceber.setStatus(rs.getBoolean("status"));
                contaAReceber.setCidade(
                        service.ServiceCidade.Buscar(rs.getInt("cidadeId"))
                );
                contaARecebers.add(contaAReceber);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return contaARecebers;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAReceberDAO->Retrive(int id)->contaAReceberDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
*/
    public ContaAReceber RetrieveIdDaContaAReceberPeloIdDaVenda(int idDaVenda) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.CONTA_A_RECEBER_RETRIVE_ONE_ID_DA_VENDA);
            pstm.setInt(1, idDaVenda);
            rs = pstm.executeQuery();
            ContaAReceber contaAReceber = new ContaAReceber.ContaAReceberBuilder().createContaAReceber();
            while (rs.next()) {
                contaAReceber.setId(rs.getInt("id"));
                contaAReceber.setVendaId(rs.getInt("vendaid"));
                contaAReceber.setValor(rs.getFloat("valor"));
                contaAReceber.setStatus(rs.getBoolean("status"));
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return contaAReceber;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ContaAReceberDAO->RetrieveIdTheCity(int idContaAReceber)->contaAReceberDAO\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
}

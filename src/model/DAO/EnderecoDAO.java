package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.bo.Endereco;
import java.util.ArrayList;

public class EnderecoDAO implements InterfaceDAO<Endereco> {

    @Override
    public void Create(Endereco objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.ENDERECO_CREATE);
            pstm.setString(1, objeto.getLogradouro());
            pstm.setString(2, objeto.getNumero());
            pstm.setInt(3, objeto.getBairro().getId());
            pstm.setString(4, objeto.getCep());
            pstm.setBoolean(5, objeto.getStatus());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: EnderecoDAO->Create\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public List<Endereco> Retrieve() {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.ENDERECO_RETRIVE_ALL);
            rs = pstm.executeQuery();
            List<Endereco> enderecos = new ArrayList();
            while (rs.next()) {
                Endereco endereco = new Endereco.EnderecoBuilder()
                        .setId(rs.getInt("id"))
                        .setLogradouro(rs.getString("logradouro"))
                        .setNumero(rs.getString("numero"))
                        .setBairro(
                                service.ServiceBairro.Buscar(rs.getInt("bairroid"))
                        )
                        .setCep(rs.getString("cep"))
                        .setStatus(rs.getBoolean("status"))
                        .createEndereco();
                enderecos.add(endereco);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return enderecos;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: EnderecoDAO->Retrive\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public Endereco Retrieve(int id) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.ENDERECO_RETRIVE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            Endereco endereco = new Endereco.EnderecoBuilder().createEndereco();
            while (rs.next()) {
                endereco.setId(rs.getInt("id"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setBairro(
                    service.ServiceBairro.Buscar(rs.getInt("bairroid"))
                );
                endereco.setCep(rs.getString("cep"));
                endereco.setStatus(rs.getBoolean("status"));
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return endereco;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: EnderecoDAO->Retrive(id)\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public void Update(Endereco objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.ENDERECO_UPDATE);
            pstm.setString(1, objeto.getLogradouro());
            pstm.setString(2, objeto.getNumero());
            pstm.setInt(3, objeto.getBairro().getId());
            pstm.setString(4, objeto.getCep());
            pstm.setBoolean(5, objeto.getStatus());
            pstm.setInt(6, objeto.getId());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: EnderecoDAO->Update\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public void Delete(Endereco objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.ENDERECO_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: EnderecoDAO->Delete\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public void Delete(int idEndereco) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.ENDERECO_DELETE);
            pstm.setInt(1, idEndereco);
            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: EnderecoDAO->Delete\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public int RetrievePorId(Endereco endereco) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.ENDERECO_RETRIVE_NUMERO_AND_LOGRADOURO_AND_BAIRRO);
            pstm.setString(1, endereco.getNumero());
            pstm.setString(2, endereco.getLogradouro());
            pstm.setInt(3, endereco.getBairro().getId());
            rs = pstm.executeQuery();
            Endereco enderecoRetorno = new Endereco.EnderecoBuilder().createEndereco();
            while (rs.next()) {
                enderecoRetorno.setId(rs.getInt("id"));
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return enderecoRetorno.getId();
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: EnderecoDAO->Retrive(id)\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
}

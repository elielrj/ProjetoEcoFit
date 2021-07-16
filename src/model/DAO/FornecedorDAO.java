package model.DAO;

import java.util.List;
import model.bo.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DAO.EnderecoDAO;

public class FornecedorDAO implements InterfaceDAO<Fornecedor> {

    @Override
    public void Create(Fornecedor objeto) {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "INSERT INTO fornecedor(razaoSocial, cnpj, inscricaoEstadual, telefone1, telefone2, email, observacao, status, enderecoId,complemtno) VALUES (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, objeto.getRazaoSocial());
            pstm.setString(2, objeto.getInscricaoEstadual());
            pstm.setString(3, objeto.getCnpj());
            pstm.setInt(4, objeto.getEndereco().getId());
            pstm.setString(5, objeto.getTelefone1());
            pstm.setString(6, objeto.getTelefone2());
            pstm.setString(7, objeto.getEmail());
            pstm.setString(8, objeto.getObservacao());
            pstm.setBoolean(9, objeto.getStatus());
            pstm.setString(10, objeto.getComplemento());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public List<Fornecedor> Retrieve() {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id, razaoSocial, cnpj, inscricaoEstadual, telefone1, telefone2, email, observacao, status, enderecoId FROM fornecedor";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();

            List<Fornecedor> fornecedores = new ArrayList();

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor.FornecedorBuilder()
                        .setId(rs.getInt("id"))//1
                        .setRazaoSocial(rs.getString("razaoSocial"))//2
                        .setInscricaoEstadual(rs.getString("inscricaoEstadual"))//3
                        .setCnpj(rs.getString("cnpj"))//4
                        .setEndereco(
                                service.ServiceEndereco.Buscar(rs.getInt("enderecoId"))
                        )//5
                        .setTelefone1(rs.getString("telefone1"))//6
                        .setTelefone2(rs.getString("telefone2"))//7
                        .setEmail(rs.getString("email"))//8
                        .setObservacao(rs.getString("observacao"))//9
                        .setStatus(rs.getBoolean("status"))//10
                        .setComplemento(rs.getString("complemento"))//11
                        .createFornecedor();
                fornecedores.add(fornecedor);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return fornecedores;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public Fornecedor Retrieve(int id) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "SELECT id, razaoSocial, cnpj, inscricaoEstadual, telefone1, telefone2, email, observacao, status,enderecoId  FROM fornecedor WHERE  fornecedor.id = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            Fornecedor fornecedor = new Fornecedor.FornecedorBuilder().createFornecedor();

            while (rs.next()) {
                fornecedor.setId(rs.getInt("id"));//1
                fornecedor.setRazaoSocial(rs.getString("razaoSocial"));//2
                fornecedor.setInscricaoEstadual(rs.getString("inscricaoEstadual"));//3
                fornecedor.setCnpj(rs.getString("cnpj"));//4
                fornecedor.setEndereco(
                        service.ServiceEndereco.Buscar(rs.getInt("enderecoId"))
                );//5
                fornecedor.setTelefone1(rs.getString("telefone1"));//6
                fornecedor.setTelefone2(rs.getString("telefone2"));//7
                fornecedor.setEmail(rs.getString("email"));//8
                fornecedor.setObservacao(rs.getString("observacao"));//9
                fornecedor.setStatus(rs.getBoolean("status"));//10
                fornecedor.setComplemento(rs.getString("complemento"));//11
            }

            ConectionFactory.closeConnection(conexao, pstm, rs);
            return fornecedor;

        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    @Override
    public void Update(Fornecedor objeto) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "UPDATE fornecedor SET razaoSocial =?, cnpj = ?, inscricaoEstadual = ?, telefone1 = ?, telefone2 = ?, email = ?, observacao = ?, status = ?, enderecoId = ?, complemnto=? WHERE id =?";

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, objeto.getRazaoSocial());
            pstm.setString(2, objeto.getCnpj());
            pstm.setString(3, objeto.getInscricaoEstadual());
            pstm.setString(4, objeto.getTelefone1());
            pstm.setString(5, objeto.getTelefone2());
            pstm.setString(6, objeto.getEmail());
            pstm.setString(7, objeto.getObservacao());
            pstm.setBoolean(8, objeto.getStatus());
            pstm.setInt(9, objeto.getEndereco().getId());
            pstm.setInt(10, objeto.getId());
            pstm.setString(11, objeto.getComplemento());

            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public void Delete(Fornecedor objeto) {
        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "DELETE FROM fornecedor WHERE id = ?";
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

}

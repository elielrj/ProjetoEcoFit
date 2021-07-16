package model.DAO;

import java.util.List;
import model.bo.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DAO.SQL.SQL;

public class FornecedorDAO implements InterfaceDAO<Fornecedor> {
    
    @Override
    public void Create(Fornecedor objeto) {
        Connection conexao = ConectionFactory.getConection();
        
        PreparedStatement pstm = null;
        
        try {
            pstm = conexao.prepareStatement(SQL.FORNECEFOR_CREATE);
            pstm.setString(1, objeto.getRazaoSocial());
            pstm.setString(2, objeto.getInscricaoEstadual());
            pstm.setString(3, objeto.getCnpj());
            
            pstm.setString(4, objeto.getTelefone1());
            pstm.setString(5, objeto.getTelefone2());
            pstm.setString(6, objeto.getEmail());
            pstm.setString(7, objeto.getObservacao());
            pstm.setBoolean(8, objeto.getStatus());
            service.ServiceEndereco.Incluir(objeto.getEndereco());            
            pstm.setInt(9,
                    service.ServiceEndereco.BuscarPorId(objeto.getEndereco())
            );
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
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            pstm = conexao.prepareStatement(SQL.FORNECEFOR_RETRIVE_ALL);
            rs = pstm.executeQuery();
            
            List<Fornecedor> fornecedores = new ArrayList();
            
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor.FornecedorBuilder()
                        .setId(rs.getInt("id"))//1
                        .setRazaoSocial(rs.getString("razaosocial"))//2
                        .setCnpj(rs.getString("cnpj"))//4
                        .setInscricaoEstadual(rs.getString("inscricaoestadual"))//3
                        .setTelefone1(rs.getString("telefone1"))//6
                        .setTelefone2(rs.getString("telefone2"))//7
                        .setEmail(rs.getString("email"))//8
                        .setObservacao(rs.getString("observacao"))//9
                        .setStatus(rs.getBoolean("status"))//10
                        .setEndereco(
                                service.ServiceEndereco.Buscar(rs.getInt("enderecoid"))
                        )//5
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
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            pstm = conexao.prepareStatement(SQL.FORNECEFOR_RETRIVE_ONE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            Fornecedor fornecedor = new Fornecedor.FornecedorBuilder().createFornecedor();
            
            while (rs.next()) {
                fornecedor.setId(rs.getInt("id"));//1
                fornecedor.setRazaoSocial(rs.getString("razaosocial"));//2
                fornecedor.setInscricaoEstadual(rs.getString("inscricaoestadual"));//3
                fornecedor.setCnpj(rs.getString("cnpj"));//4
                fornecedor.setEndereco(
                        service.ServiceEndereco.Buscar(rs.getInt("enderecoid"))
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
        
        PreparedStatement pstm = null;
        
        try {
            pstm = conexao.prepareStatement(SQL.FORNECEFOR_UPDATE);
            pstm.setString(1, objeto.getRazaoSocial());
            pstm.setString(2, objeto.getCnpj());
            pstm.setString(3, objeto.getInscricaoEstadual());
            pstm.setString(4, objeto.getTelefone1());
            pstm.setString(5, objeto.getTelefone2());
            pstm.setString(6, objeto.getEmail());
            pstm.setString(7, objeto.getObservacao());
            pstm.setBoolean(8, objeto.getStatus());
            pstm.setInt(9, objeto.getEndereco().getId());
            service.ServiceEndereco.Atualizar(objeto.getEndereco());
            pstm.setString(10, objeto.getComplemento());
            pstm.setInt(11, objeto.getId());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }
    
    @Override
    public void Delete(Fornecedor objeto) {
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;
        
        try {
            
            pstm = conexao.prepareStatement(SQL.FORNECEFOR_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
            service.ServiceEndereco.Deletar(objeto.getEndereco());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }
    
    public void Delete(int idFornecedor) {
        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;
        
        try {
            Fornecedor objeto = service.ServiceFornecedor.Buscar(idFornecedor);
            
            pstm = conexao.prepareStatement(SQL.FORNECEFOR_DELETE);
            pstm.setInt(1, idFornecedor);
            pstm.executeUpdate();
            service.ServiceEndereco.Deletar(objeto.getEndereco());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }
    
}

package model.DAO;

import model.DAO.Principal.ConectionFactory;
import model.DAO.Principal.InterfaceDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.bo.PessoaFisica;

public class PessoaFisicaDAO implements InterfaceDAO<PessoaFisica> {

    @Override
    public void Create(PessoaFisica objeto) {

        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "INSERT INTO pessoaFisica(nome,rg,cpf,dataDeNascimento,telefone1,telefone2,email,observacao,status,enderecoId,tipo) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getRg());
            pstm.setString(3, objeto.getCpf());
            pstm.setString(4, objeto.getDataDeNascimento());

            pstm.setString(5, objeto.getTelefone1());
            pstm.setString(6, objeto.getTelefone2());
            pstm.setString(7, objeto.getEmail());
            pstm.setString(8, objeto.getObservacao());
            pstm.setBoolean(9, objeto.getStatus());
            pstm.setInt(10, objeto.getEndereco().getId());
            pstm.setString(11, objeto.getTipo());

            pstm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ConectionFactory.closeConnection(conexao, pstm);

    }

    @Override
    public List<PessoaFisica> Retrieve() {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id,nome,rg,cpf,dataDeNascimento,telefone1,telefone2,email,observacao,status,enderecoId,tipo FROM pessoaFisica";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();

            List<PessoaFisica> pessoaFisicas = new ArrayList();

            while (rs.next()) {
                PessoaFisica pessoaFisica = new PessoaFisica();

                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setNome(rs.getString("nome"));
                pessoaFisica.setRg(rs.getString("rg"));

                pessoaFisica.setCpf(rs.getString("cpf"));
                pessoaFisica.setDataDeNascimento(rs.getString("dataDeNascimento"));
                pessoaFisica.setTelefone1(rs.getString("telefone1"));

                pessoaFisica.setTelefone2(rs.getString("telefone2"));
                pessoaFisica.setEmail(rs.getString("email"));
                pessoaFisica.setObservacao(rs.getString("observacao"));

                pessoaFisica.setStatus(rs.getBoolean("status"));

                EnderecoDAO enderecoDAO = new EnderecoDAO();
                pessoaFisica.setEndereco(enderecoDAO.Retrieve(rs.getInt("enderecoId")));

                pessoaFisica.setTipo(rs.getString("tipo"));

                pessoaFisicas.add(pessoaFisica);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return pessoaFisicas;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }

    }

    @Override
    public PessoaFisica Retrieve(int id) {

        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "SELECT id,nome,rg,cpf,dataDeNascimento,telefone1,telefone2,email,observacao,status,enderecoId,tipo FROM pessoaFisica WHERE  pessoaFisica.id = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            PessoaFisica pessoaFisica = new PessoaFisica();

            while (rs.next()) {
                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setNome(rs.getString("nome"));
                pessoaFisica.setRg(rs.getString("rg"));
                pessoaFisica.setCpf(rs.getString("cpf"));
                pessoaFisica.setDataDeNascimento(rs.getString("dataDeNascimento"));

                pessoaFisica.setTelefone1(rs.getString("telefone1"));
                pessoaFisica.setTelefone2(rs.getString("telefone2"));
                pessoaFisica.setEmail(rs.getString("email"));
                pessoaFisica.setObservacao(rs.getString("observacao"));
                pessoaFisica.setStatus(rs.getBoolean("status"));

                EnderecoDAO enderecoDAO = new EnderecoDAO();
                pessoaFisica.setEndereco(enderecoDAO.Retrieve(rs.getInt("enderecoId")));

                pessoaFisica.setTipo(rs.getString("tipo"));

            }

            ConectionFactory.closeConnection(conexao, pstm, rs);
            return pessoaFisica;

        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }

    }

    @Override
    public void Update(PessoaFisica objeto) {

        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "UPDATE pessoaFisica SET nome=?,rg=?,cpf=?,dataDeNascimento=?,telefone1=?,telefone2=?,email=?,observacao=?,status=?,enderecoId=?,tipo=? WHERE id = ?";

        PreparedStatement pstm = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getRg());
            pstm.setString(3, objeto.getCpf());
            pstm.setString(4, objeto.getDataDeNascimento());

            pstm.setString(5, objeto.getTelefone1());
            pstm.setString(6, objeto.getTelefone2());
            pstm.setString(7, objeto.getEmail());
            pstm.setString(8, objeto.getObservacao());
            pstm.setBoolean(9, objeto.getStatus());
            pstm.setInt(10, objeto.getEndereco().getId());
            pstm.setString(11, objeto.getTipo());
            pstm.setInt(12, objeto.getId());

            pstm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    @Override
    public void Delete(PessoaFisica objeto) {

        Connection conexao = ConectionFactory.getConection();
        String sqlExecutar = "DELETE FROM pessoaFisica WHERE id = ?";
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

    public List<PessoaFisica> RetrieveAluno() {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id,nome,rg,cpf,dataDeNascimento,telefone1,telefone2,email,observacao,status,enderecoId,tipo FROM pessoaFisica where pessoaFisica.tipo = 'aluno'";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();

            List<PessoaFisica> pessoaFisicas = new ArrayList();

            while (rs.next()) {
                PessoaFisica pessoaFisica = new PessoaFisica();

                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setNome(rs.getString("nome"));
                pessoaFisica.setRg(rs.getString("rg"));

                pessoaFisica.setCpf(rs.getString("cpf"));
                pessoaFisica.setDataDeNascimento(rs.getString("dataDeNascimento"));
                pessoaFisica.setTelefone1(rs.getString("telefone1"));

                pessoaFisica.setTelefone2(rs.getString("telefone2"));
                pessoaFisica.setEmail(rs.getString("email"));
                pessoaFisica.setObservacao(rs.getString("observacao"));

                pessoaFisica.setStatus(rs.getBoolean("status"));

                EnderecoDAO enderecoDAO = new EnderecoDAO();
                pessoaFisica.setEndereco(enderecoDAO.Retrieve(rs.getInt("enderecoId")));

                pessoaFisica.setTipo(rs.getString("tipo"));

                pessoaFisicas.add(pessoaFisica);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return pessoaFisicas;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

    public List<PessoaFisica> RetrievePersonal() {
        Connection conexao = ConectionFactory.getConection();

        String sqlExecutar = "SELECT id,nome,rg,cpf,dataDeNascimento,telefone1,telefone2,email,observacao,status,enderecoId,tipo FROM pessoaFisica where pessoaFisica.tipo = 'personal'";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            rs = pstm.executeQuery();

            List<PessoaFisica> pessoaFisicas = new ArrayList();

            while (rs.next()) {
                PessoaFisica pessoaFisica = new PessoaFisica();

                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setNome(rs.getString("nome"));
                pessoaFisica.setRg(rs.getString("rg"));

                pessoaFisica.setCpf(rs.getString("cpf"));
                pessoaFisica.setDataDeNascimento(rs.getString("dataDeNascimento"));
                pessoaFisica.setTelefone1(rs.getString("telefone1"));

                pessoaFisica.setTelefone2(rs.getString("telefone2"));
                pessoaFisica.setEmail(rs.getString("email"));
                pessoaFisica.setObservacao(rs.getString("observacao"));

                pessoaFisica.setStatus(rs.getBoolean("status"));

                EnderecoDAO enderecoDAO = new EnderecoDAO();
                pessoaFisica.setEndereco(enderecoDAO.Retrieve(rs.getInt("enderecoId")));

                pessoaFisica.setTipo(rs.getString("tipo"));

                pessoaFisicas.add(pessoaFisica);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return pessoaFisicas;
        } catch (Exception ex) {
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return null;
        }
    }

}

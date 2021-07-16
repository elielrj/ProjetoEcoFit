package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DAO.SQL.SQL;
import model.bo.PessoaFisica;

public class PessoaFisicaDAO implements InterfaceDAO<PessoaFisica> {

    @Override
    public void Create(PessoaFisica objeto) {
        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.PESSOA_FISICA_CREATE);
            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getRg());
            pstm.setString(3, objeto.getCpf());
            pstm.setString(4, objeto.getDataDeNascimento());
            pstm.setString(5, objeto.getTelefone1());
            pstm.setString(6, objeto.getTelefone2());
            pstm.setString(7, objeto.getEmail());
            pstm.setString(8, objeto.getObservacao());
            pstm.setBoolean(9, objeto.getStatus());
            //inclui e resgata Id End
            service.ServiceEndereco.Incluir(objeto.getEndereco());
            pstm.setInt(10,
                    service.ServiceEndereco.BuscarPorId(objeto.getEndereco())
            );
            pstm.setString(11, objeto.getTipo());
            pstm.setString(12, objeto.getComplemento());

            pstm.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: PessoaFisicaDAO->Create\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public List<PessoaFisica> Retrieve() {

        try {
            Connection conexao = ConectionFactory.getConection();

            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.PESSOA_FISICA_RETRIVE_ALL);
            rs = pstm.executeQuery();

            List<PessoaFisica> pessoaFisicas = new ArrayList();

            while (rs.next()) {
                PessoaFisica pessoaFisica = new PessoaFisica.PessoaFisicaBuilder()
                        .setId(rs.getInt("id"))
                        .setNome(rs.getString("nome"))
                        .setRg(rs.getString("rg"))
                        .setCpf(rs.getString("cpf"))
                        .setDataDeNascimento(rs.getString("datanascimento"))
                        .setTelefone1(rs.getString("telefone1"))
                        .setTelefone2(rs.getString("telefone2"))
                        .setEmail(rs.getString("email"))
                        .setObservacao(rs.getString("observacao"))
                        .setStatus(rs.getBoolean("status"))
                        .setEndereco(
                                service.ServiceEndereco.Buscar(rs.getInt("enderecoid"))
                        )
                        .setTipo(rs.getString("tipo"))
                        .setComplemento(rs.getString("complemento"))
                        .createPessoaFisica();
                pessoaFisicas.add(pessoaFisica);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return pessoaFisicas;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: PessoaFisicaDAO->RetriveALL\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }

    }

    @Override
    public PessoaFisica Retrieve(int id) {

        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.PESSOA_FISICA_RETRIVE_ONE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            PessoaFisica pessoaFisica = new PessoaFisica.PessoaFisicaBuilder().createPessoaFisica();

            while (rs.next()) {
                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setNome(rs.getString("nome"));
                pessoaFisica.setRg(rs.getString("rg"));
                pessoaFisica.setCpf(rs.getString("cpf"));
                pessoaFisica.setDataDeNascimento(rs.getString("datanascimento"));
                pessoaFisica.setTelefone1(rs.getString("telefone1"));
                pessoaFisica.setTelefone2(rs.getString("telefone2"));
                pessoaFisica.setEmail(rs.getString("email"));
                pessoaFisica.setObservacao(rs.getString("observacao"));
                pessoaFisica.setStatus(rs.getBoolean("status"));
                pessoaFisica.setEndereco(
                        service.ServiceEndereco.Buscar(rs.getInt("enderecoid"))
                );
                pessoaFisica.setTipo(rs.getString("tipo"));
                pessoaFisica.setComplemento(rs.getString("complemento"));

            }

            ConectionFactory.closeConnection(conexao, pstm, rs);
            return pessoaFisica;

        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: PessoaFisicaDAO->RetriveID\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }

    }

    @Override
    public void Update(PessoaFisica objeto) {

        try {
            Connection conexao = ConectionFactory.getConection();
            PreparedStatement pstm = null;
            pstm = conexao.prepareStatement(SQL.PESSOA_FISICA_UPDATE);

            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getRg());
            pstm.setString(3, objeto.getCpf());
            pstm.setString(4, objeto.getDataDeNascimento());
            pstm.setString(5, objeto.getTelefone1());
            pstm.setString(6, objeto.getTelefone2());
            pstm.setString(7, objeto.getEmail());
            pstm.setString(8, objeto.getObservacao());
            pstm.setBoolean(9, objeto.getStatus());
            //atualiza
            service.ServiceEndereco.Atualizar(objeto.getEndereco());
            pstm.setInt(10, (objeto.getEndereco().getId()));
            pstm.setString(11, objeto.getTipo());
            pstm.setString(12, objeto.getComplemento());
            pstm.setInt(13, objeto.getId());

            pstm.executeUpdate();
            ConectionFactory.closeConnection(conexao, pstm);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: PessoaFisicaDAO->Update\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    @Override
    public void Delete(PessoaFisica objeto) {

        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;

        try {
            
            pstm = conexao.prepareStatement(SQL.PESSOA_FISICA_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
            service.ServiceEndereco.Deletar(objeto.getEndereco());
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: PessoaFisicaDAO->Delete\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }
    
    public void Delete(int idPessoaFisica) {

        Connection conexao = ConectionFactory.getConection();
        PreparedStatement pstm = null;

        try {
            PessoaFisica objeto = service.ServicePessoaFisica.Buscar(idPessoaFisica);
            pstm = conexao.prepareStatement(SQL.PESSOA_FISICA_DELETE);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
            service.ServiceEndereco.Deletar(objeto.getEndereco());
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: PessoaFisicaDAO->Delete\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
        ConectionFactory.closeConnection(conexao, pstm);
    }

    public List<PessoaFisica> RetrieveAluno() {

        try {
            Connection conexao = ConectionFactory.getConection();

            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.PESSOA_FISICA_RETRIVE_ALL_ALUNO);
            rs = pstm.executeQuery();

            List<PessoaFisica> pessoaFisicas = new ArrayList();
            while (rs.next()) {
                PessoaFisica pessoaFisica = new PessoaFisica.PessoaFisicaBuilder()
                        .setId(rs.getInt("id"))
                        .setNome(rs.getString("nome"))
                        .setRg(rs.getString("rg"))
                        .setCpf(rs.getString("cpf"))
                        .setDataDeNascimento(rs.getString("datanascimento"))
                        .setEndereco(
                                service.ServiceEndereco.Buscar(rs.getInt("enderecoid"))
                        )
                        .setTipo(rs.getString("tipo"))
                        .setTelefone1(rs.getString("telefone1"))
                        .setTelefone2(rs.getString("telefone2"))
                        .setEmail(rs.getString("email"))
                        .setObservacao(rs.getString("observacao"))
                        .setStatus(rs.getBoolean("status"))
                        .setComplemento(rs.getString("complemento"))
                        .createPessoaFisica();
                pessoaFisicas.add(pessoaFisica);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return pessoaFisicas;
        }catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: PessoaFisicaDAO->RetriveAluno\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public List<PessoaFisica> RetrievePersonal() {

        try {
            Connection conexao = ConectionFactory.getConection();

            PreparedStatement pstm = null;
            ResultSet rs = null;
            pstm = conexao.prepareStatement(SQL.PESSOA_FISICA_RETRIVE_ALL_PERSONAL);
            rs = pstm.executeQuery();

            List<PessoaFisica> pessoaFisicas = new ArrayList();

            while (rs.next()) {
                PessoaFisica pessoaFisica = new PessoaFisica.PessoaFisicaBuilder()
                        .setId(rs.getInt("id"))
                        .setNome(rs.getString("nome"))
                        .setRg(rs.getString("rg"))
                        .setCpf(rs.getString("cpf"))
                        .setDataDeNascimento(rs.getString("datanascimento"))
                        .setEndereco(
                                service.ServiceEndereco.Buscar(rs.getInt("enderecoid"))
                        )
                        .setTipo(rs.getString("tipo"))
                        .setTelefone1(rs.getString("telefone1"))
                        .setTelefone2(rs.getString("telefone2"))
                        .setEmail(rs.getString("email"))
                        .setObservacao(rs.getString("observacao"))
                        .setStatus(rs.getBoolean("status"))
                        .setComplemento(rs.getString("complemento"))
                        .createPessoaFisica();

                pessoaFisicas.add(pessoaFisica);
            }
            ConectionFactory.closeConnection(conexao, pstm, rs);
            return pessoaFisicas;
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: PessoaFisicaDAO->RetrivePersonal\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

}

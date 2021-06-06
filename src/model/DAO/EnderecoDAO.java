package model.DAO;

import model.DAO.Principal.InterfaceDAO;
import java.util.List;
import model.bo.Endereco;
import java.util.ArrayList;
import model.DAO.Principal.PS;
import model.DAO.SQL.SqlEndereco;

public class EnderecoDAO implements InterfaceDAO<Endereco>{
    private PS ps;
    private CidadeDAO cidadeDAO;
    private BairroDAO bairroDAO;

    public EnderecoDAO(){
        this.ps = new PS(new SqlEndereco());
    }
    
    @Override
    public void Create(Endereco objeto) {
        try{
            ps.criar();
            ps.preparedStatement.setString(1,objeto.getLogradouro());
            ps.preparedStatement.setString(2,objeto.getNumero());
            ps.preparedStatement.setString(3,objeto.getComplemento());
            ps.preparedStatement.setInt(4,objeto.getBairro().getId());
            ps.preparedStatement.setInt(5,objeto.getCidade().getId());
            ps.preparedStatement.setString(6,objeto.getCep());
            ps.preparedStatement.setBoolean(7,objeto.getStatus());
            ps.preparedStatement.executeUpdate();
            ps.fecharConexao();
        }catch(Exception ex){
            throw new RuntimeException("Erro na cricação de enderecoDAO: "+ ex);
        }
    }

    @Override
    public List<Endereco> Retrieve() {
        List<Endereco> enderecos = new ArrayList();
        try{
            ps.listarTodos();
            while(ps.resultSet.next()){
                Endereco endereco = new Endereco();
                endereco.setId(ps.resultSet.getInt("id"));
                endereco.setLogradouro(ps.resultSet.getString("logradouro"));
                endereco.setNumero(ps.resultSet.getString("numero"));
                endereco.setComplemento(ps.resultSet.getString("complemento"));
                try{
                 bairroDAO = new BairroDAO();
                endereco.setBairro(bairroDAO.Retrieve().get(ps.resultSet.getInt("bairroId")));
                }catch(Exception ex){
                    throw new RuntimeException("Erro na listagem geral de bairro: "+ ex);
                }
                try{
                 cidadeDAO = new CidadeDAO();
                endereco.setCidade(cidadeDAO.Retrieve().get(ps.resultSet.getInt("cidadeId")));
                }catch(Exception ex){
                    throw new RuntimeException("Erro na listagem geral de cidade: "+ ex);
                }
                endereco.setCep(ps.resultSet.getString("cep"));
                endereco.setStatus(ps.resultSet.getBoolean("status"));
                enderecos.add(endereco);
            }
            ps.fecharConexao();
            return enderecos;
        }catch(Exception ex){
            throw new RuntimeException("Erro na listagem geral de enderecoDAO: "+ ex);
        }
    }

    @Override
    public Endereco Retrieve(int id) {
        try{
            ps.listarId(id);
            Endereco endereco = new Endereco();
            while(ps.resultSet.next()){
                endereco.setId(ps.resultSet.getInt("id"));
                endereco.setLogradouro(ps.resultSet.getString("logradouro"));
                endereco.setNumero(ps.resultSet.getString("numero"));
                endereco.setComplemento(ps.resultSet.getString("complemento"));
                bairroDAO = new BairroDAO();
                endereco.setBairro(bairroDAO.Retrieve().get(ps.resultSet.getInt("bairroId")));
                cidadeDAO = new CidadeDAO();
                endereco.setCidade(cidadeDAO.Retrieve().get(ps.resultSet.getInt("cidadeId")));
                endereco.setCep(ps.resultSet.getString("cep"));
                endereco.setStatus(ps.resultSet.getBoolean("status"));
            }
            ps.fecharConexao();
            return endereco;
        }catch(Exception ex){
            throw new RuntimeException("Erro na listagem por Id de enreçoDAO: "+ ex);
        }
    }

    @Override
    public void Update(Endereco objeto) {
        try{
            ps.atualizar();
            ps.preparedStatement.setString(1, objeto.getLogradouro());
            ps.preparedStatement.setString(2, objeto.getNumero());
            ps.preparedStatement.setString(3, objeto.getComplemento());
            ps.preparedStatement.setInt(4, objeto.getBairro().getId());
            ps.preparedStatement.setInt(5, objeto.getCidade().getId());
            ps.preparedStatement.setString(6, objeto.getCep());
            ps.preparedStatement.setBoolean(7, objeto.getStatus());
            ps.preparedStatement.setInt(8, objeto.getId());
            ps.preparedStatement.executeUpdate();
            ps.fecharConexao();
        }catch(Exception ex){
            throw new RuntimeException("Erro na atualização de enderecoDAO: "+ ex);        
        }        
    }

    @Override
    public void Delete(Endereco objeto)  {
        try{
            ps.deletar(objeto.getId());
            ps.preparedStatement.executeUpdate(); 
            ps.fecharConexao();
        }catch(Exception ex){
            throw new RuntimeException("Erro em deletar enderecoDAO: "+ ex);
        }
    }
}

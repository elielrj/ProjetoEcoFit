package model.DAO;

import model.DAO.Principal.InterfaceDAO;
import java.util.List;
import model.bo.Cidade;
import java.util.ArrayList;
import model.DAO.Principal.PS;
import model.DAO.SQL.SqlCidade;

public class CidadeDAO implements InterfaceDAO<Cidade>{
    private PS ps;

    public CidadeDAO() {
        this.ps = new PS(new SqlCidade());
    }    

    @Override
    public void Create(Cidade objeto) {
        try{
            ps.criar();
            ps.preparedStatement.setString(1, objeto.getNome());
            ps.preparedStatement.setBoolean(2, objeto.getStatus());
            ps.preparedStatement.executeUpdate();
            ps.fecharConexao();
        }catch(Exception ex){
            throw new RuntimeException("Erro na criação de cidadeDAO: " +ex);
        }        
    }

    @Override
    public List<Cidade> Retrieve() {
        List<Cidade> cidades = new ArrayList();
        try{
            ps.listarTodos();
            while(ps.resultSet.next()){
                Cidade cidade = new Cidade();
                cidade.setId(ps.resultSet.getInt("id"));
                cidade.setNome(ps.resultSet.getString("nome"));               
                cidade.setStatus(ps.resultSet.getBoolean("status"));                
                cidades.add(cidade);
            }
            ps.fecharConexao();
            return cidades;
        }catch(Exception ex){        
            throw new  RuntimeException("Erro na listagem geral da cidadeDAO: " + ex);
        }
    }

    @Override
    public Cidade Retrieve(int id) {
        try{
            ps.listarId(id);
            Cidade cidade = new Cidade();
            while(ps.resultSet.next()){
                cidade.setId(ps.resultSet.getInt("id"));
                cidade.setNome(ps.resultSet.getString("nome"));
                cidade.setStatus(ps.resultSet.getBoolean("status"));
            }
            ps.fecharConexao();
            return cidade;
        }catch(Exception ex){
            throw new RuntimeException("Erro na lisgtagem por id de bairroDAO: " + ex);
        }
    }

    @Override
    public void Update(Cidade objeto){
        try{
            ps.atualizar();
            ps.preparedStatement.setString(1, objeto.getNome());
            ps.preparedStatement.setBoolean(2, objeto.getStatus());
            ps.preparedStatement.setInt(3, objeto.getId());
            ps.preparedStatement.executeUpdate();            
            ps.fecharConexao();
        }catch(Exception ex){
            throw new RuntimeException("Erro na atualização de cidadeDAO: "+ ex);
        }        
    }

    @Override
    public void Delete(Cidade objeto){
        try{
            ps.deletar(objeto.getId());
            ps.preparedStatement.executeUpdate();
            ps.fecharConexao();
        }catch(Exception ex){
            throw new RuntimeException("Erro em deletar cidadeDAO"+ ex);
        }        
    }
}
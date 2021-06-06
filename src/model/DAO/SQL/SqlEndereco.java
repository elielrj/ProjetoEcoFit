
package model.DAO.SQL;


public class SqlEndereco implements Sql{

    @Override
    public String criar() {
        return "INSERT INTO endereco(logradouro,numero,complemento,bairroId,cidadeId,cep,status) VALUES(?,?,?,?,?,?,?)";
    }

    @Override
    public String listarTodos() {
        return "SELECT id,logradouro,numero,complemento,bairroId, cidadeId,cep,status FROM endereco";
    }

    @Override
    public String listarId() {
        return "SELECT id,logradouro,numero,complemento,bairroId, cidadeId,cep,status FROM endereco WHERE endereco.id = ?";
    }

    @Override
    public String atualizar() {
        return "UPDATE endereco SET logradouro=?,numero=?,complemento=?,bairroId=?,cidadeId=?,cep=?,status=? WHERE id=?";
    }

    @Override
    public String deletar() {
        return "DELETE FROM endereco WHERE id =?";
    }
    
}


package model.DAO.SQL;


public class SqlCidade implements Sql{

    @Override
    public String criar() {
        return "INSERT INTO cidade(nome,status) VALUES(?,?)";
    }

    @Override
    public String listarTodos() {
        return "SELECT id,nome,status FROM cidade";
    }

    @Override
    public String listarId() {
        return "SELECT id,nome,status FROM cidade WHERE cidade.id = ?";
    }

    @Override
    public String atualizar() {
        return "UPDATE cidade SET nome=?,status=? WHERE id = ?";
    }

    @Override
    public String deletar() {
        return "DELETE FROM cidade WHERE id=?";
    }
    
}

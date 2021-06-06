
package model.DAO.SQL;


public class SqlBairro implements Sql{

    @Override
    public String criar() {
        return  "INSERT INTO bairro(nome,status) VALUES(?,?)";
    }

    @Override
    public String listarTodos() {
        return  "SELECT id,nome,status FROM bairro";

    }

    @Override
    public String listarId() {
    return "SELECT id,nome,status FROM bairro WHERE bairro.id=?";
    }

    @Override
    public String atualizar() {
        return "UPDATE bairro SET nome=?,status=?  WHERE id=?";
    }

    @Override
    public String deletar() {
        return "DELETE FROM bairro WHERE id = ?";
    }


}



   


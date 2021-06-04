
package model.DAO.SQL;


public interface Sql<Objeto> {
       
   public String criar();
   public String listarTodos();
   public String listarId();
   public String atualizar();
   public String deletar();
   
}

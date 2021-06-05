
package service;

import java.util.List;
import model.bo.Receber;
import model.DAO.ReceberDAO;

public class ServiceReceber {

    public static void Incluir(Receber objeto){

        ReceberDAO receberDAO = new ReceberDAO();
        receberDAO.Create(objeto);

    }    

    public static void Atualizar(Receber objeto) {
        ReceberDAO receberDAO = new ReceberDAO();
        receberDAO.Update(objeto);  
    }
    
    public static List<Receber> Buscar(){
        ReceberDAO receberDAO = new ReceberDAO();
        return (receberDAO.Retrieve());  
    }
    
     public static Receber Buscar(int id){
        ReceberDAO receberDAO = new ReceberDAO();
        return receberDAO.Retrieve(id);  
    }
     
     public static void Deletar(Receber objeto){
        ReceberDAO receberDAO = new ReceberDAO();
        receberDAO.Delete(objeto);  
    }
}

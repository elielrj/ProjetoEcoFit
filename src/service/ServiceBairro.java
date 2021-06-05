
package service;

import java.util.List;
import model.bo.Bairro;
import model.DAO.BairroDAO;

public class ServiceBairro {

    public static void Incluir(Bairro objeto){

        BairroDAO bairroDAO = new BairroDAO();
        bairroDAO.Create(objeto);

    }    

    public static void Atualizar(Bairro objeto) {
        BairroDAO bairroDAO = new BairroDAO();
        bairroDAO.Update(objeto);  
    }
    
    public static List<Bairro> Buscar(){
        BairroDAO bairroDAO = new BairroDAO();
        return (bairroDAO.Retrieve());  
    }
    
     public static Bairro Buscar(int id){
        BairroDAO bairroDAO = new BairroDAO();
        return bairroDAO.Retrieve(id);  
    }
     
     public static void Deletar(Bairro objeto){
        BairroDAO bairroDAO = new BairroDAO();
        bairroDAO.Delete(objeto);  
    }
}

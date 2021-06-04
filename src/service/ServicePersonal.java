
package service;

import java.util.List;
import model.bo.Personal;
import model.DAO.Vender.PersonalDAO;

public class ServicePersonal {

    public static void Incluir(Personal objeto){

        PersonalDAO personalDAO = new PersonalDAO();
        personalDAO.Create(objeto);

    }    

    public static void Atualizar(Personal objeto) {
        PersonalDAO personalDAO = new PersonalDAO();
        personalDAO.Update(objeto);  
    }
    
    public static List<Personal> Buscar(){
        PersonalDAO personalDAO = new PersonalDAO();
        return (personalDAO.Retrieve());  
    }
    
     public static Personal Buscar(int id){
        PersonalDAO personalDAO = new PersonalDAO();
        return personalDAO.Retrieve(id);  
    }
     
     public static void Deletar(Personal objeto){
        PersonalDAO personalDAO = new PersonalDAO();
        personalDAO.Delete(objeto);  
    }
}

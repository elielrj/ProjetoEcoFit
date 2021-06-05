
package service;

import java.util.List;
import model.bo.Cidade;
import model.DAO.CidadeDAO;

public class ServiceCidade {

    public static void Incluir(Cidade objeto){

        CidadeDAO cidadeDAO = new CidadeDAO();
        cidadeDAO.Create(objeto);

    }    

    public static void Atualizar(Cidade objeto) {
        CidadeDAO cidadeDAO = new CidadeDAO();
        cidadeDAO.Update(objeto);  
    }
    
    public static List<Cidade> Buscar(){
        CidadeDAO cidadeDAO = new CidadeDAO();
        return (cidadeDAO.Retrieve());  
    }
    
     public static Cidade Buscar(int id){
        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO.Retrieve(id);  
    }
     
     public static void Deletar(Cidade objeto){
        CidadeDAO cidadeDAO = new CidadeDAO();
        cidadeDAO.Delete(objeto);
    }
}

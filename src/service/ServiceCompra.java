
package service;

import java.util.List;
import model.bo.Compra;
import model.DAO.CompraDAO;

public class ServiceCompra {

    public static void Incluir(Compra objeto){

        CompraDAO compraDAO = new CompraDAO();
        compraDAO.Create(objeto);

    }    

    public static void Atualizar(Compra objeto) {
        CompraDAO compraDAO = new CompraDAO();
        compraDAO.Update(objeto);  
    }
    
    public static List<Compra> Buscar(){
        CompraDAO compraDAO = new CompraDAO();
        return (compraDAO.Retrieve());  
    }
    
     public static Compra Buscar(int id){
        CompraDAO compraDAO = new CompraDAO();
        return compraDAO.Retrieve(id);  
    }
     
     public static void Deletar(Compra objeto){
        CompraDAO compraDAO = new CompraDAO();
        compraDAO.Delete(objeto);  
    }
}

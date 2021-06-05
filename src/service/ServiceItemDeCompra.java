
package service;

import java.util.List;
import model.bo.ItemDeCompra;
import model.DAO.ItemDeCompraDAO;

public class ServiceItemDeCompra {

    public static void Incluir(ItemDeCompra objeto){

        ItemDeCompraDAO itemDeCompraDAO = new ItemDeCompraDAO();
        itemDeCompraDAO.Create(objeto);

    }    

    public static void Atualizar(ItemDeCompra objeto) {
        ItemDeCompraDAO itemDeCompraDAO = new ItemDeCompraDAO();
        itemDeCompraDAO.Update(objeto);  
    }
    
    public static List<ItemDeCompra> Buscar(){
        ItemDeCompraDAO itemDeCompraDAO = new ItemDeCompraDAO();
        return (itemDeCompraDAO.Retrieve());  
    }
    
     public static ItemDeCompra Buscar(int id){
        ItemDeCompraDAO itemDeCompraDAO = new ItemDeCompraDAO();
        return itemDeCompraDAO.Retrieve(id);  
    }
     
     public static void Deletar(ItemDeCompra objeto){
        ItemDeCompraDAO itemDeCompraDAO = new ItemDeCompraDAO();
        itemDeCompraDAO.Delete(objeto);  
    }
}

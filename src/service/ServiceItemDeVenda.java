
package service;

import java.util.List;
import model.bo.ItemDeVenda;
import model.DAO.ItemDeVendaDAO;

public class ServiceItemDeVenda {

    public static void Incluir(ItemDeVenda objeto){

        ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
        itemDeVendaDAO.Create(objeto);

    }    

    public static void Atualizar(ItemDeVenda objeto) {
        ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
        itemDeVendaDAO.Update(objeto);  
    }
    
    public static List<ItemDeVenda> Buscar(){
        ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
        return (itemDeVendaDAO.Retrieve());  
    }
    
     public static ItemDeVenda Buscar(int id){
        ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
        return itemDeVendaDAO.Retrieve(id);  
    }
     
     public static void Deletar(ItemDeVenda objeto){
        ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
        itemDeVendaDAO.Delete(objeto);  
    }
}

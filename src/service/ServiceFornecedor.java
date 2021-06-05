
package service;

import java.util.List;
import model.bo.Fornecedor;
import model.DAO.FornecedorDAO;

public class ServiceFornecedor {

    public static void Incluir(Fornecedor objeto){

        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.Create(objeto);

    }    

    public static void Atualizar(Fornecedor objeto) {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.Update(objeto);  
    }
    
    public static List<Fornecedor> Buscar(){
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        return (fornecedorDAO.Retrieve());  
    }
    
     public static Fornecedor Buscar(int id){
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        return fornecedorDAO.Retrieve(id);  
    }
     
     public static void Deletar(Fornecedor objeto){
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.Delete(objeto);  
    }
}


package service;

import java.util.List;
import model.bo.Endereco;
import model.DAO.EnderecoDAO;

public class ServiceEndereco {

    public static void Incluir(Endereco objeto){

        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.Create(objeto);

    }    

    public static void Atualizar(Endereco objeto) {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.Update(objeto);  
    }
    
    public static List<Endereco> Buscar(){
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        return (enderecoDAO.Retrieve());  
    }
    
     public static Endereco Buscar(int id){
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        return enderecoDAO.Retrieve(id);  
    }
     
     public static void Deletar(Endereco objeto){
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.Delete(objeto);  
    }
}

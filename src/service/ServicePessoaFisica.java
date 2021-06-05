
package service;

import java.util.List;
import model.bo.PessoaFisica;
import model.DAO.Vender.AlunoDAO;
import model.DAO.Enderecos.EnderecoDAO;
import model.bo.Endereco;

public class ServicePessoaFisica {

    public static void Incluir(PessoaFisica objeto){

        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.Create(objeto);
    }    

    public static void Atualizar(PessoaFisica objeto) {
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.Update(objeto);  
    }
    
    public static List<PessoaFisica> Buscar(){
        AlunoDAO alunoDAO = new AlunoDAO();
        return (alunoDAO.Retrieve());  
    }
    
     public static PessoaFisica Buscar(int id){
        AlunoDAO alunoDAO = new AlunoDAO();
        return alunoDAO.Retrieve(id);  
    }
     
     public static void Deletar(PessoaFisica objeto){
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.Delete(objeto);  
    }
}

package service;

import java.util.List;
import model.bo.Venda;
import model.DAO.VendaDAO;
import model.bo.PessoaFisica;

public class ServiceVenda {

    public static void Incluir(Venda objeto) {

        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.Create(objeto);

    }

    public static void Atualizar(Venda objeto) {
        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.Update(objeto);
    }

    public static List<Venda> Buscar() {
        VendaDAO vendaDAO = new VendaDAO();
        return vendaDAO.Retrieve();
    }

    public static Venda Buscar(int id) {
        VendaDAO vendaDAO = new VendaDAO();
        return vendaDAO.Retrieve(id);
    }
    
    public static int Buscar(Venda venda) {
        VendaDAO vendaDAO = new VendaDAO();
        return vendaDAO.Retrieve(venda);
    }

    public static void Deletar(Venda objeto) {
        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.Delete(objeto);
    }
    
    public static void Deletar(int idVenda) {
        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.Delete(idVenda);
    }
    
    public static  List<Venda> RetriveBuscaVendaDeUmCliente(PessoaFisica pessoaFisica){
        VendaDAO vendaDAO = new VendaDAO();
        return vendaDAO.RetrieveBuscaVendaDeUmCliente(pessoaFisica);
    }
}

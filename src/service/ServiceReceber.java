package service;

import java.util.List;
import model.bo.Receber;
import model.DAO.ReceberDAO;

public class ServiceReceber {

    public static void Incluir(Receber objeto) {
        ReceberDAO receberDAO = new ReceberDAO();
        receberDAO.Create(objeto);
    }

    public static void Atualizar(Receber objeto) {
        ReceberDAO receberDAO = new ReceberDAO();
        receberDAO.Update(objeto);
    }

    public static List<Receber> Buscar() {
        ReceberDAO receberDAO = new ReceberDAO();
        return (receberDAO.Retrieve());
    }
    
    public static List<Receber> BuscarPorVendasNaoRecebidas() {
        ReceberDAO receberDAO = new ReceberDAO();
        return (receberDAO.RetrieveBuscarPorVendasNaoRecebidas());
    }
    public static List<Receber> BuscarPorVendasRecebidas(int idContaAReceber) {
        ReceberDAO receberDAO = new ReceberDAO();
        return (receberDAO.RetrieveBuscarPorVendasRecebidas(idContaAReceber));
    }

    public static Receber Buscar(int id) {
        ReceberDAO receberDAO = new ReceberDAO();
        return receberDAO.Retrieve(id);
    }
    
    public static Receber BuscarPorUmaIdVendaRecebido(int idVenda) {
        ReceberDAO receberDAO = new ReceberDAO();
        return receberDAO.RetrievePorUmaIdVendaRecebido(idVenda);  
    }

    public static void Deletar(Receber objeto) {
        ReceberDAO receberDAO = new ReceberDAO();
        receberDAO.Delete(objeto);
    }
    public static void Deletar(int idReceber) {
        ReceberDAO receberDAO = new ReceberDAO();
        receberDAO.Delete(idReceber);
    }
}

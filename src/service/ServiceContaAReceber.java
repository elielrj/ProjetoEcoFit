package service;

import java.util.List;
import model.bo.ContaAReceber;
import model.DAO.ContaAReceberDAO;

public class ServiceContaAReceber {

    public static void Incluir(ContaAReceber objeto) {
        ContaAReceberDAO contaAReceberDAO = new ContaAReceberDAO();
        contaAReceberDAO.Create(objeto);
    }

    public static void Atualizar(ContaAReceber objeto) {
        ContaAReceberDAO contaAReceberDAO = new ContaAReceberDAO();
        contaAReceberDAO.Update(objeto);
    }

    public static List<ContaAReceber> Buscar() {
        ContaAReceberDAO contaAReceberDAO = new ContaAReceberDAO();
        return (contaAReceberDAO.Retrieve());
    }

    public static ContaAReceber Buscar(int id) {
        ContaAReceberDAO contaAReceberDAO = new ContaAReceberDAO();
        return contaAReceberDAO.Retrieve(id);
    }

    public static void Deletar(ContaAReceber objeto) {
        ContaAReceberDAO contaAReceberDAO = new ContaAReceberDAO();
        contaAReceberDAO.Delete(objeto);
    }
/*
    public static void Deletar(int idContaAReceber) {
        ContaAReceberDAO contaAReceberDAO = new ContaAReceberDAO();
        contaAReceberDAO.Delete(idContaAReceber);
    }

    public static List<ContaAReceber> BuscarPorCidade(int idCidade) {
        ContaAReceberDAO contaAReceberDAO = new ContaAReceberDAO();
        return contaAReceberDAO.RetrieveForCity(idCidade);
    }*/

    public static ContaAReceber BuscarIdDaContaAReceberPeloIdDaVenda(int idDaVenda) {
        ContaAReceberDAO contaAReceberDAO = new ContaAReceberDAO();
        return contaAReceberDAO.RetrieveIdDaContaAReceberPeloIdDaVenda(idDaVenda);
    }
}

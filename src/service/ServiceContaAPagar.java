package service;

import java.util.List;
import model.bo.ContaAPagar;
import model.DAO.ContaAPagarDAO;

public class ServiceContaAPagar {

    public static void Incluir(ContaAPagar objeto) {
        ContaAPagarDAO contaAPagarDAO = new ContaAPagarDAO();
        contaAPagarDAO.Create(objeto);
    }

    public static void Atualizar(ContaAPagar objeto) {
        ContaAPagarDAO contaAPagarDAO = new ContaAPagarDAO();
        contaAPagarDAO.Update(objeto);
    }

    public static List<ContaAPagar> Buscar() {
        ContaAPagarDAO contaAPagarDAO = new ContaAPagarDAO();
        return (contaAPagarDAO.Retrieve());
    }

    public static ContaAPagar Buscar(int id) {
        ContaAPagarDAO contaAPagarDAO = new ContaAPagarDAO();
        return contaAPagarDAO.Retrieve(id);
    }

    public static void Deletar(ContaAPagar objeto) {
        ContaAPagarDAO contaAPagarDAO = new ContaAPagarDAO();
        contaAPagarDAO.Delete(objeto);
    }
/*
    public static void Deletar(int idContaAPagar) {
        ContaAPagarDAO contaAPagarDAO = new ContaAPagarDAO();
        contaAPagarDAO.Delete(idContaAPagar);
    }

    public static List<ContaAPagar> BuscarPorCidade(int idCidade) {
        ContaAPagarDAO contaAPagarDAO = new ContaAPagarDAO();
        return contaAPagarDAO.RetrieveForCity(idCidade);
    }
*/
    public static ContaAPagar BuscarIdDaContaAPagarPeloIdDaCompra(int idDaCompra) {
        ContaAPagarDAO contaAPagarDAO = new ContaAPagarDAO();
        return contaAPagarDAO.RetrieveIdDaContaAPagarrPeloIdDaCompra(idDaCompra);
    }
}

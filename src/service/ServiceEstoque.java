package service;

import java.util.List;
import model.bo.Estoque;
import model.DAO.EstoqueDAO;

public class ServiceEstoque {

    public static void Incluir(Estoque objeto) {

        EstoqueDAO estoqueDAO = new EstoqueDAO();
        estoqueDAO.Create(objeto);

    }

    public static void Atualizar(Estoque objeto) {
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        estoqueDAO.Update(objeto);
    }

    public static List<Estoque> Buscar() {
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        return (estoqueDAO.Retrieve());
    }

    public static Estoque Buscar(int id) {
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        return estoqueDAO.Retrieve(id);
    }
    
    public static void Deletar(Estoque objeto) {
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        estoqueDAO.Delete(objeto);
    }
    
    public static Estoque BuscarEstoquePorIdDoProduto(int idDoProduto) {
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        return estoqueDAO.Retrieve(idDoProduto);
    }

}

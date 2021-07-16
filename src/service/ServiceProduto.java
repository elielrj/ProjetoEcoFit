package service;

import java.util.List;
import model.bo.Produto;
import model.DAO.ProdutoDAO;

public class ServiceProduto {

    public static void Incluir(Produto objeto) {

        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.Create(objeto);

    }

    public static void Atualizar(Produto objeto) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.Update(objeto);
    }

    public static List<Produto> Buscar() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return (produtoDAO.Retrieve());
    }

    public static Produto Buscar(int id) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.Retrieve(id);
    }

    public static Produto Buscar(String codigoDeBarrasDoProduto) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.Retrieve(codigoDeBarrasDoProduto);
    }
    
    public static void Deletar(Produto objeto) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.Delete(objeto);
    }
    
    public static void Deletar(int idProduto) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.Delete(idProduto);
    }
    
    public static boolean codigoDeBarrasValido(int codBarras){
                
        if (Buscar(codBarras).equals(null)){
            return false;
        } else {
            return true;
        }
        
    }
}

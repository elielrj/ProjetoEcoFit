package service;

import java.util.List;
import model.bo.Cidade;
import model.DAO.CidadeDAO;

public class ServiceCidade {

    public static void Incluir(Cidade objeto) {
        try {
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidadeDAO.Create(objeto);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceCidade->Incluir\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public static void Atualizar(Cidade objeto) {
        try {
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidadeDAO.Update(objeto);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceCidade->Atualizar\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public static List<Cidade> Buscar() {
        try {
            CidadeDAO cidadeDAO = new CidadeDAO();
            return (cidadeDAO.Retrieve());
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceCidade->Buscar\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public static Cidade Buscar(int id) {
        try {
            CidadeDAO cidadeDAO = new CidadeDAO();
            return cidadeDAO.Retrieve(id);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceCidade->Buscar(int id)\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public static void Deletar(Cidade objeto) {
        try {
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidadeDAO.Delete(objeto);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceCidade->Deletar\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public static void Deletar(int idCidade) {
        try {
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidadeDAO.Delete(idCidade);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceCidade->Deletar(int id)\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
}

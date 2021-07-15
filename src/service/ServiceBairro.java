package service;

import java.util.List;
import model.bo.Bairro;
import model.DAO.BairroDAO;

public class ServiceBairro {

    public static void Incluir(Bairro objeto) {
        try {
            BairroDAO bairroDAO = new BairroDAO();
            bairroDAO.Create(objeto);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceBairro->Incluir()\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }

    }

    public static void Atualizar(Bairro objeto) {
        try {
            BairroDAO bairroDAO = new BairroDAO();
            bairroDAO.Update(objeto);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceBairro->Atualizar(int id)\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public static List<Bairro> Buscar() {
        try {
            BairroDAO bairroDAO = new BairroDAO();
            return (bairroDAO.Retrieve());
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceBairro->Buscar()\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }

    }

    public static Bairro Buscar(int id) {
        try {
            BairroDAO bairroDAO = new BairroDAO();
            return bairroDAO.Retrieve(id);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceBairro->Buscar(int id)\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public static void Deletar(Bairro objeto) {
        try {
            BairroDAO bairroDAO = new BairroDAO();
            bairroDAO.Delete(objeto);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceBairro->Deletar()\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public static void Deletar(int idBairro) {
        try {
            BairroDAO bairroDAO = new BairroDAO();
            bairroDAO.Delete(idBairro);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceBairro->Deletar()\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public static List<Bairro> BuscarPorCidade(int idCidade) {
        try {
            BairroDAO bairroDAO = new BairroDAO();
            return bairroDAO.RetrieveForCity(idCidade);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceBairro->Buscar(int id)\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public static int BuscarIdDaCidade(int idBairro) {
        try {
            BairroDAO bairroDAO = new BairroDAO();
            return bairroDAO.RetrieveIdTheCity(idBairro);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceBairro->BuscarIdDaCidade(int idBairro)\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
}

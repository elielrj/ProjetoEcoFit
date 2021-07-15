package service;

import java.util.List;
import model.bo.Endereco;
import model.DAO.EnderecoDAO;

public class ServiceEndereco {

    public static void Incluir(Endereco objeto) {
        try {
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            enderecoDAO.Create(objeto);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceEndereco->Incluir\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public static void Atualizar(Endereco objeto) {
        try {
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            enderecoDAO.Update(objeto);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceEndereco->Atualizar\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public static List<Endereco> Buscar() {
        try {
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            return (enderecoDAO.Retrieve());
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceEndereco->Buscar\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public static Endereco Buscar(int id) {
        try {
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            return enderecoDAO.Retrieve(id);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceEndereco->Buscar(id)\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }

    public static void Deletar(Endereco objeto) {

        try {
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            enderecoDAO.Delete(objeto);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceEndereco->Deletar\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
    
    public static void Deletar(int idEndereco) {

        try {
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            enderecoDAO.Delete(idEndereco);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceEndereco->Deletar\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
    
    public static Endereco BuscarPorId(Endereco endereco) {
        try {
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            return enderecoDAO.RetrievePorId(endereco);
        } catch (Exception ex) {
            throw new RuntimeException(" \nCLASSE: ServiceEndereco->Buscar(id)\nMENSAGEM:"
                    + ex.getMessage() + "\nLOCALIZADO:"
                    + ex.getLocalizedMessage()
            );
        }
    }
}

package service;

import java.util.List;
import model.bo.PessoaFisica;
import model.DAO.EnderecoDAO;
import model.DAO.PessoaFisicaDAO;
import model.bo.Endereco;

public class ServicePessoaFisica {

    public static void Incluir(PessoaFisica objeto) {

        PessoaFisicaDAO alunoDAO = new PessoaFisicaDAO();
        alunoDAO.Create(objeto);
    }

    public static void Atualizar(PessoaFisica objeto) {
        PessoaFisicaDAO alunoDAO = new PessoaFisicaDAO();
        alunoDAO.Update(objeto);
    }

    public static List<PessoaFisica> Buscar() {
        PessoaFisicaDAO alunoDAO = new PessoaFisicaDAO();
        return (alunoDAO.Retrieve());
    }

    public static PessoaFisica Buscar(int id) {
        PessoaFisicaDAO alunoDAO = new PessoaFisicaDAO();
        return alunoDAO.Retrieve(id);
    }

    public static void Deletar(PessoaFisica objeto) {
        PessoaFisicaDAO alunoDAO = new PessoaFisicaDAO();
        alunoDAO.Delete(objeto);
    }

    public static List<PessoaFisica> BuscarAluno() {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        return (pessoaFisicaDAO.RetrieveAluno());
    }

    public static List<PessoaFisica> BuscarPersonal() {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        return (pessoaFisicaDAO.RetrievePersonal());
    }
}

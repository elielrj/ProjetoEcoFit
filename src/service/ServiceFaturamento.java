package service;

import java.util.List;
import model.bo.Faturamento;
import model.DAO.FaturamentoDAO;

public class ServiceFaturamento {

    public static void Incluir(Faturamento objeto) {

        FaturamentoDAO faturamentoDAO = new FaturamentoDAO();
        faturamentoDAO.Create(objeto);

    }

    public static void Atualizar(Faturamento objeto) {
        FaturamentoDAO faturamentoDAO = new FaturamentoDAO();
        faturamentoDAO.Update(objeto);
    }

    public static List<Faturamento> Buscar() {
        FaturamentoDAO faturamentoDAO = new FaturamentoDAO();
        return (faturamentoDAO.Retrieve());
    }

    public static Faturamento Buscar(int id) {
        FaturamentoDAO faturamentoDAO = new FaturamentoDAO();
        return faturamentoDAO.Retrieve(id);
    }

    public static void Deletar(Faturamento objeto) {
        FaturamentoDAO faturamentoDAO = new FaturamentoDAO();
        faturamentoDAO.Delete(objeto);
    }
}

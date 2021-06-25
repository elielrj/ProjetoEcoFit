package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import view.TelaBuscaFornecedor;
import model.bo.Fornecedor;

public class ControllerBuscaFornecedor implements ActionListener {

    TelaBuscaFornecedor telaBuscaFornecedor;

    public ControllerBuscaFornecedor(TelaBuscaFornecedor telaBuscaFornecedor) {
        this.telaBuscaFornecedor = telaBuscaFornecedor;

        this.telaBuscaFornecedor.getjButtonCarregar().addActionListener(this);
        this.telaBuscaFornecedor.getjButtonSair().addActionListener(this);

        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFornecedor.getjTable1().getModel();

        for (Fornecedor fornecedorDaLista : service.ServiceFornecedor.Buscar()) {
            tabela.addRow(new Object[]{
                fornecedorDaLista.getId(),
                fornecedorDaLista.getRazaoSocial(),
                fornecedorDaLista.getCnpj(),
                fornecedorDaLista.getInscricaoEstadual(),
                fornecedorDaLista.getTelefone1(),
                fornecedorDaLista.getTelefone2(),
                fornecedorDaLista.getEmail(),
                fornecedorDaLista.getObservacao(),
                fornecedorDaLista.getStatus(),
                fornecedorDaLista.getEndereco().toString()
            });
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaFornecedor.getjButtonCarregar()) {
            ControllerFornecedor.codigo = (int) this.telaBuscaFornecedor.getjTable1().getValueAt(this.telaBuscaFornecedor.getjTable1().getSelectedRow(), 0);
            this.telaBuscaFornecedor.dispose();
        }
        if (e.getSource() == this.telaBuscaFornecedor.getjButtonSair()) {
            this.telaBuscaFornecedor.dispose();
        }
    }

}

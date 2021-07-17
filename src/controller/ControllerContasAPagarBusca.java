package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.busca.TelaBuscaBairro;
import model.bo.Bairro;
import model.bo.ContaAPagar;
import model.bo.ContaAPagar;
import view.busca.TelaBuscaContaAPagar;

public class ControllerContasAPagarBusca implements ActionListener {

    TelaBuscaContaAPagar telaBuscaContaAPagar;

    public ControllerContasAPagarBusca(TelaBuscaContaAPagar telaBuscaContaAPagar) {
        this.telaBuscaContaAPagar = telaBuscaContaAPagar;
        
        this.telaBuscaContaAPagar.getjButtonSair().addActionListener(this);
        
        carregarDadosNaTabela();
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        if (e.getSource() == this.telaBuscaContaAPagar.getjButtonSair())
            this.telaBuscaContaAPagar.dispose();
       
    }

    private void carregarDadosNaTabela() {
        
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaContaAPagar.getjTable_ContasAPagar().getModel();
        tabela.getDataVector().removeAllElements();
        
        for (ContaAPagar contaAPagarDaLista : service.ServiceContaAPagar.Buscar()) {
            tabela.addRow(new Object[]{
                contaAPagarDaLista.getId(),
                contaAPagarDaLista.getCompraId(),
                contaAPagarDaLista.getValor(),
                contaAPagarDaLista.getStatus()
            });
        }
    }
}

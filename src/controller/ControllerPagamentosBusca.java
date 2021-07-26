package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bo.Pagar;
import view.busca.TelaBuscaPagar;

public class ControllerPagamentosBusca implements ActionListener {

    TelaBuscaPagar telaBuscaPagar;

    public ControllerPagamentosBusca(TelaBuscaPagar telaBuscaPagar) {
        this.telaBuscaPagar = telaBuscaPagar;

        this.telaBuscaPagar.getjButtonCarregar().addActionListener(this);
        this.telaBuscaPagar.getjButtonSair().addActionListener(this);

        carregarDadosNaTabela();
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaPagar.getjButtonSair()) 
            this.telaBuscaPagar.dispose();
        
        else if (e.getSource() == this.telaBuscaPagar.getjButtonCarregar()) {
            ControllerPagamentos.codigo = (int) this.telaBuscaPagar.getjTable_PagarBusca().getValueAt(
                    this.telaBuscaPagar.getjTable_PagarBusca().getSelectedRow(),0);
            this.telaBuscaPagar.dispose();
        }else if (e.getSource() == this.telaBuscaPagar.getjButton_Deletar()){
            try{
                service.ServiceBairro.Deletar((int) this.telaBuscaPagar.getjTable_PagarBusca().getValueAt(
                        this.telaBuscaPagar.getjTable_PagarBusca().getSelectedRow(),0));
                JOptionPane.showMessageDialog(null, "Bairro deletado com sucesso!");
                carregarDadosNaTabela();
            } catch (Exception ex) {
                throw new RuntimeException(" \nCLASSE: ControllerBuscaBairro->actionPerformed(ActionEvent e)->deletar\nMENSAGEM:" 
                        + ex.getMessage() + "\nLOCALIZADO:" 
                        + ex.getLocalizedMessage()
                );
            }
        }
        
    }

    private void carregarDadosNaTabela() {
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaPagar.getjTable_PagarBusca().getModel();

        for (Pagar pagamentosDaLista : service.ServicePagar.Buscar()) {
            tabela.addRow(new Object[]{
                pagamentosDaLista.getId(),
                pagamentosDaLista.getDataPagamento(),
                pagamentosDaLista.getHora(),
                pagamentosDaLista.getValorAcrescimo(),
                pagamentosDaLista.getValorPago(),
                pagamentosDaLista.getObservacao(),
                pagamentosDaLista.getContaAPagar().getId()
            });
        }
    }

}

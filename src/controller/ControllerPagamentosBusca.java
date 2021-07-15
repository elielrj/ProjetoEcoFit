package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import model.bo.Compra;
import view.TelaBuscaPagamentos;

public class ControllerPagamentosBusca implements ActionListener {

    TelaBuscaPagamentos telaBuscaPagamentos;

    public ControllerPagamentosBusca(TelaBuscaPagamentos telaBuscaPagamentos) {
        this.telaBuscaPagamentos = telaBuscaPagamentos;

        this.telaBuscaPagamentos.getjButtonCarregar().addActionListener(this);
        this.telaBuscaPagamentos.getjButtonSair().addActionListener(this);

        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaPagamentos.getjTable1().getModel();

        for (Compra pagamentosDaLista : service.ServiceCompra.Buscar()) {
            tabela.addRow(new Object[]{
                pagamentosDaLista.getId(),
                pagamentosDaLista.getData(),
                pagamentosDaLista.getHora(),
                pagamentosDaLista.getDataDeVencimento(),
                pagamentosDaLista.getObservacao(),
                pagamentosDaLista.getValorDeDesconto(),
                pagamentosDaLista.getValorTotal(),
                pagamentosDaLista.getStatus(),
                pagamentosDaLista.getFornecedor().toString()
            });
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaPagamentos.getjButtonCarregar()) {
            //ControllerCompra.codigo = (int) this.telaBuscaPagamentos.getjTable1().getValueAt(this.telaBuscaPagamentos.getjTable1().getSelectedRow(),0);
            this.telaBuscaPagamentos.dispose();
        }
        if (e.getSource() == this.telaBuscaPagamentos.getjButtonSair()) {
            this.telaBuscaPagamentos.dispose();
        }
    }

}

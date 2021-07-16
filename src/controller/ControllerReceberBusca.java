package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import view.busca.TelaBuscaReceber;
import model.bo.Receber;

public class ControllerReceberBusca implements ActionListener {

    TelaBuscaReceber telaBuscaReceber;

    public ControllerReceberBusca(TelaBuscaReceber telaBuscaReceber) {
        this.telaBuscaReceber = telaBuscaReceber;

        this.telaBuscaReceber.getjButtonCarregar().addActionListener(this);
        this.telaBuscaReceber.getjButtonSair().addActionListener(this);

        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaReceber.getjTable_ReceberBusca().getModel();

        for (Receber receberDaLista : service.ServiceReceber.Buscar()) {
            tabela.addRow(new Object[]{receberDaLista.getId(),
                receberDaLista.getData(),
                receberDaLista.getValorRecebido(),
                receberDaLista.getData(),
                receberDaLista.getData(),
                receberDaLista.getValorDeDescontoNegociado(),
                receberDaLista.getValorDeAcrescimo(),
                receberDaLista.getValorRecebido(),
                receberDaLista.getObservacao()
            });
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaReceber.getjButtonCarregar()) {
            ControllerRecebebimento.codigo = (int) this.telaBuscaReceber.getjTable_ReceberBusca().getValueAt(this.telaBuscaReceber.getjTable_ReceberBusca().getSelectedRow(), 0);
            this.telaBuscaReceber.dispose();
        }
        if (e.getSource() == this.telaBuscaReceber.getjButtonSair()) {
            this.telaBuscaReceber.dispose();
        }
    }

}

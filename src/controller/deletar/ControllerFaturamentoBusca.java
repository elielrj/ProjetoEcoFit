package controller.deletar;

import controller.deletar.ControllerFaturamento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import view.busca.TelaBuscaFaturamento;
import model.bo.deletar.Faturamento;

public class ControllerFaturamentoBusca implements ActionListener {

    TelaBuscaFaturamento telaBuscaFaturamento;

    public ControllerFaturamentoBusca(TelaBuscaFaturamento telaBuscaFaturamento) {
        
        this.telaBuscaFaturamento = telaBuscaFaturamento;
        this.telaBuscaFaturamento.getjButton_Carregar().addActionListener(this);
        this.telaBuscaFaturamento.getjButton_Sair().addActionListener(this);

        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFaturamento.getjTable_BucaFaturamento().getModel();

        for (Faturamento faturamentoDaLista : service.ServiceFaturamento.Buscar()) {
            tabela.addRow(new Object[]{
                faturamentoDaLista.getId(),
                faturamentoDaLista.getData(),
                faturamentoDaLista.getHora(),
                faturamentoDaLista.getDataDeVencimento(),
                faturamentoDaLista.getObservacao(),
                faturamentoDaLista.getValorDoDesconto(),
                faturamentoDaLista.getValorTotal(),
                faturamentoDaLista.getStatus(),
                faturamentoDaLista.getPessoaFisica().getNome(),
                faturamentoDaLista.getUserCaixa()                
            });
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaFaturamento.getjButton_Carregar()) {

            ControllerFaturamento.codigo = (int) this.telaBuscaFaturamento.getjTable_BucaFaturamento().getValueAt(this.telaBuscaFaturamento.getjTable_BucaFaturamento().getSelectedRow(), 0);
            this.telaBuscaFaturamento.dispose();
        }
        if (e.getSource() == this.telaBuscaFaturamento.getjButton_Sair()) {
            this.telaBuscaFaturamento.dispose();
        }
    }

}

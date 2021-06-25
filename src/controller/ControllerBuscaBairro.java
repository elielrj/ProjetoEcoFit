package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import view.TelaBuscaBairro;
import model.bo.Bairro;

public class ControllerBuscaBairro implements ActionListener {

    TelaBuscaBairro telaBuscaBairro;

    public ControllerBuscaBairro(TelaBuscaBairro telaBuscaBairro) {
        this.telaBuscaBairro = telaBuscaBairro;

        this.telaBuscaBairro.getjButtonCarregar().addActionListener(this);
        this.telaBuscaBairro.getjButtonSair().addActionListener(this);

        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaBairro.getjTable1().getModel();

        for (Bairro bairroDaLista : service.ServiceBairro.Buscar()) {
            tabela.addRow(new Object[]{
                bairroDaLista.getId(),
                bairroDaLista.getNome(),
                bairroDaLista.getStatus()
            });
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaBairro.getjButtonCarregar()) {

            ControllerBairro.codigo = (int) this.telaBuscaBairro.getjTable1().getValueAt(this.telaBuscaBairro.getjTable1().getSelectedRow(), 0);
            this.telaBuscaBairro.dispose();
        }
        if (e.getSource() == this.telaBuscaBairro.getjButtonSair()) {
            this.telaBuscaBairro.dispose();
        }
    }

}

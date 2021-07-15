package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.TelaBuscaCidade;
import model.bo.Cidade;
import service.ServiceCidade;

public class ControllerCidadeBusca implements ActionListener {

    TelaBuscaCidade telaBuscaCidade;

    public ControllerCidadeBusca(TelaBuscaCidade telaBuscaCidade) {
        this.telaBuscaCidade = telaBuscaCidade;

        this.telaBuscaCidade.getjButtonCarregar().addActionListener(this);
        this.telaBuscaCidade.getjButtonSair().addActionListener(this);
        this.telaBuscaCidade.getjButton_deletar().addActionListener(this);

        carregarDadosNaTabela();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaCidade.getjButtonSair()) {
            this.telaBuscaCidade.dispose();
        } else if (e.getSource() == this.telaBuscaCidade.getjButtonCarregar()) {
            ControllerCidade.codigo = (int) this.telaBuscaCidade.getjTable_Cidades().getValueAt(this.telaBuscaCidade.getjTable_Cidades().getSelectedRow(), 0);
            this.telaBuscaCidade.dispose();
        } else if (e.getSource() == this.telaBuscaCidade.getjButton_deletar()) {
            try {
                ServiceCidade.Deletar((int) this.telaBuscaCidade.getjTable_Cidades().getValueAt(this.telaBuscaCidade.getjTable_Cidades().getSelectedRow(), 0));
                JOptionPane.showMessageDialog(null, "Cidade deletado com sucesso!");
                carregarDadosNaTabela();
            } catch (Exception ex) {
                throw new RuntimeException(" \nCLASSE: ControllerBuscaCidade->actionPerformed(ActionEvent e)->deletar\nMENSAGEM:"
                        + ex.getMessage() + "\nLOCALIZADO:"
                        + ex.getLocalizedMessage()
                );
            }
        }
    }

    private void carregarDadosNaTabela() {
        
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaCidade.getjTable_Cidades().getModel();
        tabela.getDataVector().removeAllElements();
        
        for (Cidade cidadeDaLista : ServiceCidade.Buscar()) {
            tabela.addRow(new Object[]{
                cidadeDaLista.getId(),
                cidadeDaLista.getNome(),
                cidadeDaLista.getStatus()
            });
        }
    }
}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.busca.TelaBuscaBairro;
import model.bo.Bairro;

public class ControllerBairroBusca implements ActionListener {

    TelaBuscaBairro telaBuscaBairro;

    public ControllerBairroBusca(TelaBuscaBairro telaBuscaBairro) {
        
        this.telaBuscaBairro = telaBuscaBairro;

        this.telaBuscaBairro.getjButtonCarregar().addActionListener(this);
        this.telaBuscaBairro.getjButtonSair().addActionListener(this);
        this.telaBuscaBairro.getjButton_deletar().addActionListener(this);

        carregarDadosNaTabela();
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        if (e.getSource() == this.telaBuscaBairro.getjButtonSair())
            this.telaBuscaBairro.dispose();
        else if (e.getSource() == this.telaBuscaBairro.getjButtonCarregar()) {
            ControllerBairro.codigo = (int) this.telaBuscaBairro.getjTable1().getValueAt(this.telaBuscaBairro.getjTable1().getSelectedRow(), 0);
            this.telaBuscaBairro.dispose();                
        }else if (e.getSource() == this.telaBuscaBairro.getjButton_deletar()){
            try{
                service.ServiceBairro.Deletar((int) this.telaBuscaBairro.getjTable1().getValueAt(this.telaBuscaBairro.getjTable1().getSelectedRow(),0));
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
        
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaBairro.getjTable1().getModel();
        tabela.getDataVector().removeAllElements();
        
        for (Bairro bairroDaLista : service.ServiceBairro.Buscar()) {
            tabela.addRow(new Object[]{
                bairroDaLista.getId(),
                bairroDaLista.getNome(),
                bairroDaLista.getStatus(),
                bairroDaLista.getCidade().getNome()
            });
        }
    }
}

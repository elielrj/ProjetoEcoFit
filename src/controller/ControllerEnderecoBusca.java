package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.busca.TelaBuscaEndereco;
import model.bo.Endereco;

public class ControllerEnderecoBusca implements ActionListener {

    TelaBuscaEndereco telaBuscaEndereco;

    public ControllerEnderecoBusca(TelaBuscaEndereco telaBuscaEndereco) {
        this.telaBuscaEndereco = telaBuscaEndereco;

        this.telaBuscaEndereco.getjButtonCarregar().addActionListener(this);
        this.telaBuscaEndereco.getjButtonSair().addActionListener(this);
        this.telaBuscaEndereco.getjButton_Deletar().addActionListener(this);
        
        carregarDadosNaTabela();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaEndereco.getjButtonSair()) 
            this.telaBuscaEndereco.dispose();
        else if (e.getSource() == this.telaBuscaEndereco.getjButtonCarregar()) {
            ControllerEndereco.codigo = (int) this.telaBuscaEndereco.getjTable_Enderecos().getValueAt(this.telaBuscaEndereco.getjTable_Enderecos().getSelectedRow(), 0);
            this.telaBuscaEndereco.dispose();
        } else if(e.getSource() == this.telaBuscaEndereco.getjButton_Deletar()) {
            try{
                service.ServiceEndereco.Deletar(
                        (int) this.telaBuscaEndereco.getjTable_Enderecos().getValueAt(
                                this.telaBuscaEndereco.getjTable_Enderecos().getSelectedRow(),0
                        )
                );
                JOptionPane.showMessageDialog(null, "Endereço deletado com sucesso!");
                carregarDadosNaTabela();
            } catch (Exception ex) {
                throw new RuntimeException(" \nCLASSE: ControllerBuscaEndereço->actionPerformed(ActionEvent e)->deletar\nMENSAGEM:" 
                        + ex.getMessage() + "\nLOCALIZADO:" 
                        + ex.getLocalizedMessage()
                );
            }
        }
    }

    private void carregarDadosNaTabela() {
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaEndereco.getjTable_Enderecos().getModel();
        tabela.getDataVector().removeAllElements();

        for (Endereco enderecoDaLista : service.ServiceEndereco.Buscar()) {
            tabela.addRow(new Object[]{
                enderecoDaLista.getId(),
                enderecoDaLista.getLogradouro(),
                enderecoDaLista.getNumero(),
                enderecoDaLista.getBairro().getNome(),
                enderecoDaLista.getBairro().getCidade().getNome(),
                enderecoDaLista.getCep(),
                enderecoDaLista.getStatus()

            });
        }
    }
}

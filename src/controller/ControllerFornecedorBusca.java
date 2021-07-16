package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.busca.TelaBuscaFornecedor;
import model.bo.Fornecedor;

public class ControllerFornecedorBusca implements ActionListener {

    TelaBuscaFornecedor telaBuscaFornecedor;

    public ControllerFornecedorBusca(TelaBuscaFornecedor telaBuscaFornecedor) {
        this.telaBuscaFornecedor = telaBuscaFornecedor;

        this.telaBuscaFornecedor.getjButtonCarregar().addActionListener(this);
        this.telaBuscaFornecedor.getjButtonSair().addActionListener(this);
        this.telaBuscaFornecedor.getjButton_Deletar().addActionListener(this);

        carregarDadosNaTabela();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaFornecedor.getjButtonSair())
            this.telaBuscaFornecedor.dispose();
        else if (e.getSource() == this.telaBuscaFornecedor.getjButtonCarregar()) {
            ControllerFornecedor.codigo = (int) this.telaBuscaFornecedor.getjTable1().getValueAt(this.telaBuscaFornecedor.getjTable1().getSelectedRow(), 0);
            this.telaBuscaFornecedor.dispose();
        }else if(e.getSource() == this.telaBuscaFornecedor.getjButton_Deletar()) {
            try{
                service.ServiceFornecedor.Deletar(
                        (int) this.telaBuscaFornecedor.getjTable1().getValueAt(
                                this.telaBuscaFornecedor.getjTable1().getSelectedRow(),0
                        )
                );
                JOptionPane.showMessageDialog(null, "Fornecedor deletado com sucesso!");
                carregarDadosNaTabela();
            } catch (Exception ex) {
                throw new RuntimeException(" \nCLASSE: ControllerBuscaFornecedor->actionPerformed(ActionEvent e)->deletar\nMENSAGEM:" 
                        + ex.getMessage() + "\nLOCALIZADO:" 
                        + ex.getLocalizedMessage()
                );
            }
        }
    }

    private void carregarDadosNaTabela() {
        
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFornecedor.getjTable1().getModel();
        tabela.getDataVector().removeAllElements();
        
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
                fornecedorDaLista.getEndereco().toString(),
                fornecedorDaLista.getComplemento()
            });
        }
    }

}

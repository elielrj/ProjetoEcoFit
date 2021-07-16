package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.busca.TelaBuscaPessoaFisica;
import model.bo.PessoaFisica;

public class ControllerPessoaFisicaPersonalBusca implements ActionListener {

    TelaBuscaPessoaFisica telaBuscaPessoaFisica;
    int codigoPessoaFisica = 0;

    public ControllerPessoaFisicaPersonalBusca(TelaBuscaPessoaFisica telaBuscaPessoaFisica) {
        this.telaBuscaPessoaFisica = telaBuscaPessoaFisica;

        this.telaBuscaPessoaFisica.getjButtonCarregar().addActionListener(this);
        this.telaBuscaPessoaFisica.getjButtonSair().addActionListener(this);
        this.telaBuscaPessoaFisica.getjButton_Deletar().addActionListener(this);

        carregarDadosNaTabela();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaPessoaFisica.getjButtonSair()) {
            this.telaBuscaPessoaFisica.dispose();
        } else if (e.getSource() == this.telaBuscaPessoaFisica.getjButtonCarregar()) {
            codigoPessoaFisica = (int) this.telaBuscaPessoaFisica.getjTable1().getValueAt(this.telaBuscaPessoaFisica.getjTable1().getSelectedRow(), 0);
            ControllerPessoaFisica.codigo = codigoPessoaFisica;
            this.telaBuscaPessoaFisica.setIdDaPessoaFisica(codigoPessoaFisica);
            this.telaBuscaPessoaFisica.dispose();
        } else if (e.getSource() == this.telaBuscaPessoaFisica.getjButton_Deletar()) {
            try {
                service.ServicePessoaFisica.Deletar(
                        (int) this.telaBuscaPessoaFisica.getjTable1().getValueAt(
                                this.telaBuscaPessoaFisica.getjTable1().getSelectedRow(), 0
                        )
                );
                JOptionPane.showMessageDialog(null, "Pessoa deletado com sucesso!");
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
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaPessoaFisica.getjTable1().getModel();
        tabela.getDataVector().removeAllElements();

        for (PessoaFisica pessoaFisica : service.ServicePessoaFisica.BuscarPersonal()) {
            tabela.addRow(new Object[]{
                pessoaFisica.getId(),
                pessoaFisica.getNome(),
                pessoaFisica.getRg(),
                pessoaFisica.getCpf(),
                pessoaFisica.getDataDeNascimento(),
                pessoaFisica.getTelefone1(),
                pessoaFisica.getTelefone2(),
                pessoaFisica.getEmail(),
                pessoaFisica.getObservacao(),
                pessoaFisica.getStatus(),
                pessoaFisica.getEndereco().toString(),
                pessoaFisica.getTipo()
            });
        }
    }

}

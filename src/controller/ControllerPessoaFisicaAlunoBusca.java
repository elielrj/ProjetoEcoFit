package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import view.busca.TelaBuscaPessoaFisica;
import model.bo.PessoaFisica;

public class ControllerPessoaFisicaAlunoBusca implements ActionListener {

    TelaBuscaPessoaFisica telaBuscaPessoaFisica;

    public ControllerPessoaFisicaAlunoBusca(TelaBuscaPessoaFisica telaBuscaPessoaFisica) {
        this.telaBuscaPessoaFisica = telaBuscaPessoaFisica;

        this.telaBuscaPessoaFisica.getjButtonCarregar().addActionListener(this);
        this.telaBuscaPessoaFisica.getjButtonSair().addActionListener(this);

        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaPessoaFisica.getjTable1().getModel();

        for (PessoaFisica pessoaFisica : service.ServicePessoaFisica.BuscarAluno()) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaPessoaFisica.getjButtonCarregar()) {
            ControllerPessoaFisica.codigo = (int) this.telaBuscaPessoaFisica.getjTable1().getValueAt(this.telaBuscaPessoaFisica.getjTable1().getSelectedRow(), 0);
            this.telaBuscaPessoaFisica.dispose();
        }
        if (e.getSource() == this.telaBuscaPessoaFisica.getjButtonSair()) {
            this.telaBuscaPessoaFisica.dispose();
        }
    }

}

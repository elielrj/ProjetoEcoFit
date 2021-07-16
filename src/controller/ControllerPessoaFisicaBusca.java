package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import view.busca.TelaBuscaPessoaFisica;
import model.bo.PessoaFisica;

public class ControllerPessoaFisicaBusca implements ActionListener {

    TelaBuscaPessoaFisica telaBuscaPessoaFisica;
    int codigoPessoaFisica = 0;

    public ControllerPessoaFisicaBusca(TelaBuscaPessoaFisica telaBuscaPessoaFisica) {
        this.telaBuscaPessoaFisica = telaBuscaPessoaFisica;

        this.telaBuscaPessoaFisica.getjButtonCarregar().addActionListener(this);
        this.telaBuscaPessoaFisica.getjButtonSair().addActionListener(this);

        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaPessoaFisica.getjTable1().getModel();

        for (PessoaFisica pessoaFisica : service.ServicePessoaFisica.Buscar()) {
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
                pessoaFisica.getEndereco(),
                pessoaFisica.getTipo()
            });
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaPessoaFisica.getjButtonCarregar()) {
            codigoPessoaFisica = (int) this.telaBuscaPessoaFisica.getjTable1().getValueAt(this.telaBuscaPessoaFisica.getjTable1().getSelectedRow(), 0);
            ControllerPessoaFisica.codigo = codigoPessoaFisica;
            this.telaBuscaPessoaFisica.setIdDaPessoaFisica(codigoPessoaFisica);
            this.telaBuscaPessoaFisica.dispose();
        }
        if (e.getSource() == this.telaBuscaPessoaFisica.getjButtonSair()) {
            this.telaBuscaPessoaFisica.dispose();
        }
    }

    public int getCodigoPessoaFisica() {
        return codigoPessoaFisica;
    }

    public void setCodigoPessoaFisica(int codigoPessoaFisica) {
        this.codigoPessoaFisica = codigoPessoaFisica;
    }

}
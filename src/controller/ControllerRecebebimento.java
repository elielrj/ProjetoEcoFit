package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.bo.PessoaFisica;
import model.bo.Receber;
import model.bo.Venda;
import view.TelaBuscaReceber;
import view.TelaCadastroReceber;

public class ControllerRecebebimento implements ActionListener {

    TelaCadastroReceber telaCadastroReceber = new TelaCadastroReceber();
    public static int codigo;

    public ControllerRecebebimento(TelaCadastroReceber telaCadastroReceber) {

        this.telaCadastroReceber = telaCadastroReceber;

        this.telaCadastroReceber.getjButtonNovo().addActionListener(this);
        this.telaCadastroReceber.getjButtonBuscar().addActionListener(this);
        this.telaCadastroReceber.getjButtonCancelar().addActionListener(this);
        this.telaCadastroReceber.getjButtonGravar().addActionListener(this);
        this.telaCadastroReceber.getjButtonSair().addActionListener(this);

        Ativa(true);
        LimpaEstadoComponentes(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaCadastroReceber.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);
            this.telaCadastroReceber.getjTextFieldId().setEnabled(false);
            codigo = 0;
        } else if (e.getSource() == this.telaCadastroReceber.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);
        } else if (e.getSource() == this.telaCadastroReceber.getjButtonGravar()) {
            //montar objeto a persistir
            Receber receber = new Receber();

            receber.setValorRecebido(Float.parseFloat(this.telaCadastroReceber.getjFormattedTextFieldValorPago().getText()));
            receber.setValorDeDescontoNegociado(Float.parseFloat(this.telaCadastroReceber.getjFormattedTextFieldValorDesconto().getText()));
            receber.setValorDeAcrescimo(Float.parseFloat(this.telaCadastroReceber.getjFormattedTextFieldValorDesconto().getText()));
            receber.setData(this.telaCadastroReceber.getjFormattedTextFieldDataEmissao().getText());
            receber.setVenda((Venda) this.telaCadastroReceber.getjComboBoxVendasId().getSelectedItem());

            if (codigo == 0) {
                service.ServiceReceber.Incluir(receber);
            } else {
                receber.setId(Integer.parseInt(this.telaCadastroReceber.getjTextFieldId().getText()));
                service.ServiceReceber.Atualizar(receber);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        if (e.getSource() == this.telaCadastroReceber.getjButtonBuscar()) {

            codigo = 0;
            TelaBuscaReceber telaBuscaReceber = new TelaBuscaReceber(null, true);
            ControllerBuscaReceber controllerBuscaReceber = new ControllerBuscaReceber(telaBuscaReceber);
            telaBuscaReceber.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);
                Receber receber = new Receber();
                receber = service.ServiceReceber.Buscar(codigo);
                this.telaCadastroReceber.getjTextFieldId().setText(receber.getId() + "");
                this.telaCadastroReceber.getjFormattedTextFieldValorEmitido().setText(receber.getValorRecebido() + "");
                this.telaCadastroReceber.getjFormattedTextFieldValorDesconto().setText(receber.getValorDeDescontoNegociado() + "");
                this.telaCadastroReceber.getjFormattedTextFieldValorAcrescimo().setText(receber.getValorDeAcrescimo() + "");
                this.telaCadastroReceber.getjFormattedTextFieldDataEmissao().setText(receber.getData());
                this.telaCadastroReceber.getjComboBoxVendasId().setSelectedItem(receber.getVenda());

                this.telaCadastroReceber.getjTextFieldId().setEnabled(false);
            }
        }

        if (e.getSource() == this.telaCadastroReceber.getjButtonSair()) {
            this.telaCadastroReceber.dispose();
        }

    }

    public void Ativa(boolean estadoBotoes) {
        this.telaCadastroReceber.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroReceber.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroReceber.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroReceber.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroReceber.getjButtonSair().setEnabled(estadoBotoes);
    }

    public void LimpaEstadoComponentes(boolean estadoCompo) {
        Component[] componentes = this.telaCadastroReceber.getjPanelDados().getComponents(); //verificar
        for (Component componente : componentes) {
            if (componente instanceof JTextField) {
                ((JTextField) componente).setText("");
                componente.setEnabled(estadoCompo);
            }

            if (componente instanceof JFormattedTextField) {
                ((JFormattedTextField) componente).setText("");
                componente.setEnabled(estadoCompo);
            }

            if (componente instanceof JComboBox) {
                ((JComboBox) componente).setSelectedItem(0);
                componente.setEnabled(estadoCompo);
            }

            if ((componente instanceof JTextArea)) {
                ((JTextArea) componente).setToolTipText("");
                ((JTextArea) componente).setEditable(estadoCompo);
            }

            if ((componente instanceof JTextArea)) {
                ((JTextArea) componente).setText("");
                componente.setEnabled(estadoCompo);
            }
            if (componente instanceof JComboBox) {
                ((JComboBox) componente).setSelectedItem(0);
                componente.setEnabled(estadoCompo);
            }

        }
    }

}

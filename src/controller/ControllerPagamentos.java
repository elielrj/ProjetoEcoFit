package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.bo.Compra;
import model.bo.Pagar;
import view.TelaBuscaPagamentos;
import view.TelaCadastroPagamento;

public class ControllerPagamentos implements ActionListener {

    TelaCadastroPagamento telaCadastroPagamento = new TelaCadastroPagamento();
    public static int codigo;

    public ControllerPagamentos(TelaCadastroPagamento telaCadastroPagamento) {

        this.telaCadastroPagamento = telaCadastroPagamento;

        this.telaCadastroPagamento.getjButtonNovo().addActionListener(this);
        this.telaCadastroPagamento.getjButtonBuscar().addActionListener(this);
        this.telaCadastroPagamento.getjButtonCancelar().addActionListener(this);
        this.telaCadastroPagamento.getjButtonGravar().addActionListener(this);
        this.telaCadastroPagamento.getjButtonSair().addActionListener(this);

        Ativa(true);
        LimpaEstadoComponentes(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaCadastroPagamento.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);
            this.telaCadastroPagamento.getjTextFieldId().setEnabled(false);
            codigo = 0;
        } else if (e.getSource() == this.telaCadastroPagamento.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);
        } else if (e.getSource() == this.telaCadastroPagamento.getjButtonGravar()) {
            //montar objeto a persistir
            Pagar pagar = new Pagar();

            pagar.setValorPago(Float.parseFloat(this.telaCadastroPagamento.getjFormattedTextFieldValorPago().getText()));
            pagar.setValorDeDescontoNegociado(Float.parseFloat(this.telaCadastroPagamento.getjFormattedTextFieldValorDesconto().getText()));
            pagar.setValorDeAcrescimo(Float.parseFloat(this.telaCadastroPagamento.getjFormattedTextFieldValorDesconto().getText()));
            //pagar.setValorEmitido(Float.parseFloat(this.telaCadastroPagamento.getjFormattedTextFieldValorEmitido().getText()));
            pagar.setData(this.telaCadastroPagamento.getjFormattedTextFieldDataPagamento().getText());
            pagar.setCompra((Compra) this.telaCadastroPagamento.getjComboBoxCompra().getSelectedItem());

            if (codigo == 0) {
                service.ServicePagar.Incluir(pagar);
            } else {
                pagar.setId(Integer.parseInt(this.telaCadastroPagamento.getjTextFieldId().getText()));
                service.ServicePagar.Atualizar(pagar);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        if (e.getSource() == this.telaCadastroPagamento.getjButtonBuscar()) {

            codigo = 0;
            TelaBuscaPagamentos telaBuscaPagar = new TelaBuscaPagamentos(null, true);
            ControllerPagamentosBusca controllerBuscaPagar = new ControllerPagamentosBusca(telaBuscaPagar);
            telaBuscaPagar.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);
                Pagar pagar = new Pagar();
                pagar = service.ServicePagar.Buscar(codigo);
                this.telaCadastroPagamento.getjTextFieldId().setText(pagar.getId() + "");
                // this.telaCadastroPagamento.getjFormattedTextFieldValorEmitido().setText(pagar.getValorRecebido()+ "");
                this.telaCadastroPagamento.getjFormattedTextFieldValorDesconto().setText(pagar.getValorDeDescontoNegociado() + "");
                this.telaCadastroPagamento.getjFormattedTextFieldValorAcrescimo().setText(pagar.getValorDeAcrescimo() + "");
                //this.telaCadastroPagamento.getjFormattedTextFieldDataEmissao().setText(pagar.getData());
                //this.telaCadastroPagamento.getjComboBoxVendasId().setSelectedItem(pagar.getVenda());

                this.telaCadastroPagamento.getjTextFieldId().setEnabled(false);
            }
        }

        if (e.getSource() == this.telaCadastroPagamento.getjButtonSair()) {
            this.telaCadastroPagamento.dispose();
        }

    }

    public void Ativa(boolean estadoBotoes) {
        this.telaCadastroPagamento.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroPagamento.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroPagamento.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroPagamento.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroPagamento.getjButtonSair().setEnabled(estadoBotoes);
    }

    public void LimpaEstadoComponentes(boolean estadoCompo) {
        Component[] componentes = this.telaCadastroPagamento.getjPanelDados().getComponents(); //verificar
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

package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.bo.PessoaFisica;
import model.bo.Receber;
import model.bo.Venda;
import view.busca.TelaBuscaReceber;
import view.cadastro.TelaCadastroReceber;

public class ControllerRecebebimento implements ActionListener {

    TelaCadastroReceber telaCadastroReceber;
    public static int codigo;

    public ControllerRecebebimento(TelaCadastroReceber telaCadastroReceber) {

        this.telaCadastroReceber = telaCadastroReceber;

        this.telaCadastroReceber.getjButtonNovo().addActionListener(this);
        this.telaCadastroReceber.getjButtonBuscar().addActionListener(this);
        this.telaCadastroReceber.getjButtonCancelar().addActionListener(this);
        this.telaCadastroReceber.getjButtonGravar().addActionListener(this);
        this.telaCadastroReceber.getjButtonSair().addActionListener(this);

        

        //this.telaCadastroReceber.getjComboBox_Cliente().addItem("");
        //this.telaCadastroReceber.getjComboBox_Cliente().setSelectedItem("");
        for (PessoaFisica pessoaFisica : service.ServicePessoaFisica.Buscar()) {
            this.telaCadastroReceber.getjComboBox_Cliente().addItem(pessoaFisica);
        }

        this.telaCadastroReceber.getjComboBox_DataDaVenda().addActionListener(this);
        this.telaCadastroReceber.getjComboBox_DataDaVenda().addItem("");
        //this.telaCadastroReceber.getjComboBox_DataDaVenda().setSelectedItem("");
        
        this.telaCadastroReceber.getjComboBox_ValorDaVenda().addActionListener(this);
        this.telaCadastroReceber.getjComboBox_ValorDaVenda().addItem("");
        //this.telaCadastroReceber.getjComboBox_ValorDaVenda().setSelectedItem("");

        Ativa(true);
        LimpaEstadoComponentes(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaCadastroReceber.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);
            this.telaCadastroReceber.getjTextFieldId().setEnabled(false);
            this.telaCadastroReceber.getjTextField_VendaId().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextFieldDataEmissao().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextFieldValorDesconto().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextFieldValorAcrescimo().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextFieldValorEmitido().setEnabled(false);
            
            this.telaCadastroReceber.getjFormattedTextFieldDataVencimento().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextFieldDataPagamento().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextField_Hora().setEnabled(false);

            

            codigo = 0;
        } else if (e.getSource() == this.telaCadastroReceber.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);
        } else if (e.getSource() == this.telaCadastroReceber.getjButtonGravar()) {
            Receber receber = new Receber.ReceberBuilder()
                    .setDataRecebimento(this.telaCadastroReceber.getjFormattedTextFieldDataEmissao().getText())//2
                    .setHora(this.telaCadastroReceber.getjFormattedTextField_Hora().getText())//3
                    .setValorDesconto(Float.parseFloat(this.telaCadastroReceber.getjFormattedTextFieldValorDesconto().getText()))//4
                    .setValorAcrescimo(Float.parseFloat(this.telaCadastroReceber.getjFormattedTextFieldValorDesconto().getText()))//5
                    .setValorRecebido(Float.parseFloat(this.telaCadastroReceber.getjFormattedTextFieldValorPago().getText()))//6
                    .setObservacao(this.telaCadastroReceber.getjTextAreaObs().getText())//7
                    .setVenda((Venda) this.telaCadastroReceber.getjComboBoxVendasId().getSelectedItem())//8
                    .createReceber();
            if (codigo == 0) {
                service.ServiceReceber.Incluir(receber);
            } else {
                receber.setId(Integer.parseInt(this.telaCadastroReceber.getjTextFieldId().getText()));//1
                service.ServiceReceber.Atualizar(receber);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        if (e.getSource() == this.telaCadastroReceber.getjButtonBuscar()) {

            codigo = 0;
            TelaBuscaReceber telaBuscaReceber = new TelaBuscaReceber(null, true);
            ControllerReceberBusca controllerBuscaReceber = new ControllerReceberBusca(telaBuscaReceber);
            telaBuscaReceber.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);
                Receber receber = new Receber.ReceberBuilder().createReceber();
                receber = service.ServiceReceber.Buscar(codigo);
                this.telaCadastroReceber.getjTextFieldId().setText(receber.getId() + "");
                this.telaCadastroReceber.getjFormattedTextFieldValorEmitido().setText(receber.getVenda().getValorTotal()+ "");
                this.telaCadastroReceber.getjFormattedTextFieldValorDesconto().setText(receber.getVenda().getValorDoDesconto()+ "");
                this.telaCadastroReceber.getjFormattedTextFieldValorAcrescimo().setText(receber.getValorAcrescimo() + "");
                this.telaCadastroReceber.getjFormattedTextFieldValorPago().setText(receber.getValorAcrescimo() + "");
                this.telaCadastroReceber.getjFormattedTextFieldDataEmissao().setText(receber.getDataRecebimento());
                this.telaCadastroReceber.getjFormattedTextFieldDataVencimento().setText(receber.getDataRecebimento());
                this.telaCadastroReceber.getjFormattedTextFieldDataPagamento().setText(receber.getDataRecebimento());
                this.telaCadastroReceber.getjFormattedTextField_Hora().setText(receber.getDataRecebimento());
                
                Venda venda = service.ServiceVenda.Buscar(receber.getVenda().getId());
                
                this.telaCadastroReceber.getjTextField_VendaId().setText(venda.getId()+"");
                this.telaCadastroReceber.getjComboBox_Cliente().setSelectedItem(receber.getVenda().getPessoaFisica());
                this.telaCadastroReceber.getjComboBox_DataDaVenda().setSelectedItem(receber.getVenda().getData());
                this.telaCadastroReceber.getjComboBox_ValorDaVenda().setSelectedItem(receber.getVenda().getValorTotal());
                
                this.telaCadastroReceber.getjTextAreaObs().setText(receber.getObservacao());

                this.telaCadastroReceber.getjTextFieldId().setEnabled(false);
            }
        }

        if (e.getSource() == this.telaCadastroReceber.getjButtonSair()) {
            this.telaCadastroReceber.dispose();
        }
        if (e.getSource() == this.telaCadastroReceber.getjComboBox_ValorDaVenda()) {
            //if (aDataDaVendaEstaSelecionada()) {

                for (Venda venda : service.ServiceVenda.RetriveBuscaVendaDeUmCliente((PessoaFisica) this.telaCadastroReceber.getjComboBox_Cliente().getSelectedItem())) {
                    if (venda.getData().equals(this.telaCadastroReceber.getjComboBox_DataDaVenda().getSelectedItem())) {
                        this.telaCadastroReceber.getjComboBox_ValorDaVenda().addItem(venda.getValorTotal());
                        this.telaCadastroReceber.getjTextField_VendaId().setText(venda.getId()+"");
                    }
                }
           /* } else {
                JOptionPane.showMessageDialog(null, "Selecione 1º um clienete e data da venda!");
            }*/
        }
        if (e.getSource() == this.telaCadastroReceber.getjComboBox_DataDaVenda()) {            
            //if (oClienteEstaSelecionado()) {
                PessoaFisica pessoaFisica = (PessoaFisica) this.telaCadastroReceber.getjComboBox_Cliente().getSelectedItem();
                //List<Venda> vendasDoCliente = buscaVendaDeUmCliente(pessoaFisica);
                for (Venda venda : service.ServiceVenda.RetriveBuscaVendaDeUmCliente((PessoaFisica) this.telaCadastroReceber.getjComboBox_Cliente().getSelectedItem())) {
                    this.telaCadastroReceber.getjComboBox_DataDaVenda().addItem(venda.getData());
                }

            /*} else {
                JOptionPane.showMessageDialog(null, "Selecione um cliente 1º!");
                this.telaCadastroReceber.getjComboBox_Cliente().requestFocus();
            }*/
        }

    }

    public void Ativa(boolean estadoBotoes) {
        this.telaCadastroReceber.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroReceber.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroReceber.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroReceber.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroReceber.getjButtonSair().setEnabled(estadoBotoes);

        this.telaCadastroReceber.getjFormattedTextFieldValorEmitido().setEnabled(estadoBotoes);
        this.telaCadastroReceber.getjFormattedTextFieldValorDesconto().setEnabled(estadoBotoes);
        this.telaCadastroReceber.getjFormattedTextFieldValorAcrescimo().setEnabled(estadoBotoes);
        this.telaCadastroReceber.getjFormattedTextFieldValorPago().setEnabled(estadoBotoes);

        this.telaCadastroReceber.getjFormattedTextFieldDataVencimento().setEnabled(estadoBotoes);
        this.telaCadastroReceber.getjFormattedTextFieldDataPagamento().setEnabled(!estadoBotoes);
        this.telaCadastroReceber.getjComboBox_Cliente().setEnabled(estadoBotoes);
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
            this.telaCadastroReceber.getjTextAreaObs().setEnabled(estadoCompo);

        }
    }


    private boolean oClienteEstaSelecionado() {
        return !this.telaCadastroReceber.getjComboBox_Cliente().getSelectedItem().equals("");

    }

    private boolean aDataDaVendaEstaSelecionada() {
        if (!this.telaCadastroReceber.getjComboBox_DataDaVenda().equals("")) {
            return true;
        }
        return false;

    }

}

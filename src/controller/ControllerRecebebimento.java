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
        
        this.telaCadastroReceber.getjComboBox_DataDaVenda().addActionListener(this);
        
        

        for(PessoaFisica pessoaFisica : service.ServicePessoaFisica.Buscar()){
            this.telaCadastroReceber.getjComboBox_Cliente().addItem(pessoaFisica);
        }
        
        this.telaCadastroReceber.getjComboBox_DataDaVenda().addItem("");
        this.telaCadastroReceber.getjComboBox_DataDaVenda().setSelectedItem("");
        
        Ativa(true);
        LimpaEstadoComponentes(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaCadastroReceber.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);
            this.telaCadastroReceber.getjTextFieldId().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextFieldDataEmissao().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextFieldValorDesconto().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextFieldValorAcrescimo().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextFieldDataEmissao().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextFieldDataVencimento().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextFieldDataPagamento().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextField_Hora().setEnabled(false);
            
            if(oClienteEstaSelecionado()){
                PessoaFisica pessoaFisica = (PessoaFisica) this.telaCadastroReceber.getjComboBox_Cliente().getSelectedItem();
                
                for(Venda venda : buscaVendaDeUmCliente(pessoaFisica)){
                    this.telaCadastroReceber.getjComboBox_DataDaVenda().addItem(venda.getData());
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um cliente!");
                this.telaCadastroReceber.getjComboBox_Cliente().requestFocus();
            }
            
            codigo = 0;
        } else if (e.getSource() == this.telaCadastroReceber.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);
        } else if (e.getSource() == this.telaCadastroReceber.getjButtonGravar()) {
            Receber receber = new Receber.ReceberBuilder()
                    .setData(this.telaCadastroReceber.getjFormattedTextFieldDataEmissao().getText())//2
                    .setHora(this.telaCadastroReceber.getjFormattedTextField_Hora().getText())//3
                    .setValorDeDescontoNegociado(Float.parseFloat(this.telaCadastroReceber.getjFormattedTextFieldValorDesconto().getText()))//4
                    .setValorDeAcrescimo(Float.parseFloat(this.telaCadastroReceber.getjFormattedTextFieldValorDesconto().getText()))//5
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
    
    private List<Venda> buscaVendaDeUmCliente(PessoaFisica pessoaFisica){
        return service.ServiceVenda.RetriveBuscaVendaDeUmCliente(pessoaFisica);
    }

    private boolean oClienteEstaSelecionado() {
        return !this.telaCadastroReceber.getjComboBox_Cliente().getSelectedItem().equals("");    
            
    }
    

}

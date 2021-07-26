package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.bo.Receber;
import model.bo.Venda;
import view.busca.TelaBuscaReceber;
import view.busca.TelaBuscaVenda;
import view.cadastro.TelaCadastroReceber;

public class ControllerRecebebimento implements ActionListener {

    TelaCadastroReceber telaCadastroReceber;
    public static int codigo;
    Venda venda;
    private String data;
    private String Hora;

    public ControllerRecebebimento(TelaCadastroReceber telaCadastroReceber) {

        this.telaCadastroReceber = telaCadastroReceber;

        this.telaCadastroReceber.getjButtonNovo().addActionListener(this);
        this.telaCadastroReceber.getjButtonBuscar().addActionListener(this);
        this.telaCadastroReceber.getjButtonCancelar().addActionListener(this);
        this.telaCadastroReceber.getjButtonGravar().addActionListener(this);
        this.telaCadastroReceber.getjButtonSair().addActionListener(this);

        this.telaCadastroReceber.getjButton_VendaBuscaId().addActionListener(this);

        Ativa(true);
        LimpaEstadoComponentes(false);

        this.venda = new Venda.VendaBuilder().createVenda();
        dataHora();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaCadastroReceber.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);
            this.telaCadastroReceber.getjTextFieldId().setEnabled(false);
            this.telaCadastroReceber.getjTextField_VendaId().setEnabled(false);

            this.telaCadastroReceber.getjFormattedTextFieldDataEmissao().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextFieldDataVencimento().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextFieldDataPagamento().setEnabled(false);

            this.telaCadastroReceber.getjFormattedTextFieldValorEmitido().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextField_Hora().setEnabled(false);

            this.telaCadastroReceber.getjFormattedTextField_VendaValor().setEnabled(false);
            this.telaCadastroReceber.getjFormattedTextField_VendaData().setEnabled(false);
            this.telaCadastroReceber.getjTextField_VendaCliente().setEnabled(false);
            
            this.telaCadastroReceber.getjFormattedTextFieldDataPagamento().setText(data);
            this.telaCadastroReceber.getjFormattedTextField_Hora().setText(Hora);

            codigo = 0;

            

        } else if (e.getSource() == this.telaCadastroReceber.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);
        } else if (e.getSource() == this.telaCadastroReceber.getjButtonGravar()) {
            Receber receber = new Receber.ReceberBuilder()
                    .setDataRecebimento(this.telaCadastroReceber.getjFormattedTextFieldDataEmissao().getText())//2
                    .setHora(this.telaCadastroReceber.getjFormattedTextField_Hora().getText())//3
                    .setValorAcrescimo(Float.parseFloat(substituirVirgolaPorPonto(this.telaCadastroReceber.getjFormattedTextFieldValorAcrescimo().getText())))//5
                    .setValorRecebido(Float.parseFloat(substituirVirgolaPorPonto(this.telaCadastroReceber.getjFormattedTextFieldValorPago().getText())))//6
                    .setObservacao(this.telaCadastroReceber.getjTextAreaObs().getText())//7
                    .setContaAReceber(
                            service.ServiceContaAReceber.BuscarIdDaContaAReceberPeloIdDaVenda(
                                    Integer.parseInt(this.telaCadastroReceber.getjTextField_VendaId().getText())
                            )
                    )//8
                    .createReceber();
            if (codigo == 0) {
                service.ServiceReceber.Incluir(receber);
            } else {
                receber.setId(Integer.parseInt(substituirVirgolaPorPonto(this.telaCadastroReceber.getjTextFieldId().getText())));//1
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

                //ID'S
                this.telaCadastroReceber.getjTextFieldId().setText(receber.getId() + "");
                this.telaCadastroReceber.getjTextField_VendaId().setText(receber.getContaAReceber().getVendaId() + "");
                //VENDA
                this.venda = service.ServiceVenda.Buscar(receber.getContaAReceber().getId());
                this.telaCadastroReceber.getjTextField_VendaId().setText(receber.getContaAReceber().getVendaId() + "");
                this.telaCadastroReceber.getjTextField_VendaCliente().setText(venda.getPessoaFisica().getNome());
                this.telaCadastroReceber.getjFormattedTextField_VendaData().setText(venda.getData());
                this.telaCadastroReceber.getjFormattedTextField_VendaValor().setText(venda.getValorTotal() + "");
                //DATAS
                this.telaCadastroReceber.getjFormattedTextFieldDataEmissao().setText(venda.getData());
                this.telaCadastroReceber.getjFormattedTextFieldDataVencimento().setText(venda.getDataDeVencimento());
                this.telaCadastroReceber.getjFormattedTextFieldDataPagamento().setText(receber.getDataRecebimento());
                //VALORES
                this.telaCadastroReceber.getjFormattedTextFieldValorEmitido().setText(receber.getContaAReceber().getValor() + "");
                this.telaCadastroReceber.getjFormattedTextFieldValorAcrescimo().setText(receber.getValorAcrescimo() + "");
                this.telaCadastroReceber.getjFormattedTextFieldValorPago().setText(receber.getValorRecebido() + "");
                //HORA
                this.telaCadastroReceber.getjFormattedTextField_Hora().setText(receber.getHora());
                //OBS
                this.telaCadastroReceber.getjTextAreaObs().setText(receber.getObservacao());
                //FALSE
                this.telaCadastroReceber.getjTextFieldId().setEnabled(false);
                this.telaCadastroReceber.getjTextField_VendaId().setEnabled(false);
                this.telaCadastroReceber.getjFormattedTextFieldDataEmissao().setEnabled(false);
                this.telaCadastroReceber.getjFormattedTextFieldDataVencimento().setEnabled(false);
                this.telaCadastroReceber.getjFormattedTextFieldDataPagamento().setEnabled(false);
                this.telaCadastroReceber.getjFormattedTextFieldValorEmitido().setEnabled(false);
                this.telaCadastroReceber.getjFormattedTextField_Hora().setEnabled(false);
                this.telaCadastroReceber.getjFormattedTextField_VendaValor().setEnabled(false);
                this.telaCadastroReceber.getjFormattedTextField_VendaData().setEnabled(false);
                this.telaCadastroReceber.getjTextField_VendaCliente().setEnabled(false);
            }
        }
        else if (e.getSource() == this.telaCadastroReceber.getjButton_VendaBuscaId()) {

                TelaBuscaVenda telaBuscaVenda = new TelaBuscaVenda(null,true);
                ControllerVendaBusca controllerVendaBusca = new ControllerVendaBusca(telaBuscaVenda);
                telaBuscaVenda.setVisible(true);

                //VENDA
                venda = service.ServiceVenda.Buscar(telaBuscaVenda.getIdVenda());
                this.telaCadastroReceber.getjTextField_VendaCliente().setText(venda.getPessoaFisica().getNome());
                this.telaCadastroReceber.getjFormattedTextField_VendaData().setText(venda.getData());
                this.telaCadastroReceber.getjFormattedTextField_VendaValor().setText(venda.getValorTotal() + "");
                //DATAS
                this.telaCadastroReceber.getjFormattedTextFieldDataEmissao().setText(venda.getData());
                this.telaCadastroReceber.getjFormattedTextFieldDataVencimento().setText(venda.getDataDeVencimento());
                //VALOR                
                this.telaCadastroReceber.getjFormattedTextFieldValorEmitido().setText(venda.getValorTotal() + "");
                //ID DA VENDA
                this.telaCadastroReceber.getjTextField_VendaId().setText(venda.getId() + "");
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
        this.telaCadastroReceber.getjFormattedTextFieldValorAcrescimo().setEnabled(estadoBotoes);
        this.telaCadastroReceber.getjFormattedTextFieldValorPago().setEnabled(estadoBotoes);

        this.telaCadastroReceber.getjFormattedTextFieldDataVencimento().setEnabled(estadoBotoes);
        this.telaCadastroReceber.getjFormattedTextFieldDataPagamento().setEnabled(!estadoBotoes);
        this.telaCadastroReceber.getjButton_VendaBuscaId().setEnabled(!estadoBotoes);
        //this.telaCadastroReceber.getjComboBox_Cliente().setEnabled(estadoBotoes);
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

    private void dataHora() {

        // data/hora atual
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        this.setData(formatterData.format(agora));

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.setHora(formatterHora.format(agora));

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }


    private static String substituirVirgolaPorPonto(String dado) {
        //dado = dado.replaceAll("\\.", "");
        dado = dado.replaceAll("\\,", ".");
        return dado;
    }


}

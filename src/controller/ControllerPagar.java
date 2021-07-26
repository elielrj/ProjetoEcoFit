package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.bo.Compra;
import model.bo.Pagar;
import view.busca.TelaBuscaCompra;
import view.busca.TelaBuscaPagar;
import view.cadastro.TelaCadastroPagar;

public class ControllerPagar implements ActionListener {

    TelaCadastroPagar telaCadastroPagar;
    public static int codigo;
    Compra compra;
    private String data;
    private String Hora; 

    public ControllerPagar(TelaCadastroPagar telaCadastroPagar) {

        this.telaCadastroPagar = telaCadastroPagar;

        this.telaCadastroPagar.getjButtonNovo().addActionListener(this);
        this.telaCadastroPagar.getjButtonBuscar().addActionListener(this);
        this.telaCadastroPagar.getjButtonCancelar().addActionListener(this);
        this.telaCadastroPagar.getjButtonGravar().addActionListener(this);
        this.telaCadastroPagar.getjButtonSair().addActionListener(this);
        
        this.telaCadastroPagar.getjButton_CompraBuscaId().addActionListener(this);

        Ativa(true);
        LimpaEstadoComponentes(false);
        this.compra = new Compra.CompraBuilder().createCompra();
        dataHora();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaCadastroPagar.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);
            
            this.telaCadastroPagar.getjTextFieldId().setEnabled(false);
            
            this.telaCadastroPagar.getjTextField_CompraId().setEnabled(false);

            this.telaCadastroPagar.getjFormattedTextFieldDataEmissao().setEnabled(false);
            this.telaCadastroPagar.getjFormattedTextFieldDataVencimento().setEnabled(false);
            this.telaCadastroPagar.getjFormattedTextFieldDataPagamento().setEnabled(false);

            this.telaCadastroPagar.getjFormattedTextFieldValorEmitido().setEnabled(false);
            this.telaCadastroPagar.getjFormattedTextField_Hora().setEnabled(false);

            this.telaCadastroPagar.getjFormattedTextField_CompraValor().setEnabled(false);
            this.telaCadastroPagar.getjFormattedTextField_CompraData().setEnabled(false);
            this.telaCadastroPagar.getjTextField_CompraFornecedor().setEnabled(false);
            
            this.telaCadastroPagar.getjFormattedTextFieldDataPagamento().setText(data);
            this.telaCadastroPagar.getjFormattedTextField_Hora().setText(Hora);
            codigo = 0;
        } else if (e.getSource() == this.telaCadastroPagar.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);
        } else if (e.getSource() == this.telaCadastroPagar.getjButtonGravar()) {
            Pagar pagar = new Pagar.PagarBuilder()
                    .setDataPagamento(this.telaCadastroPagar.getjFormattedTextFieldDataPagamento().getText())//2
                    .setHora(this.telaCadastroPagar.getjFormattedTextField_Hora().getText())//3
                    .setValorPago(Float.parseFloat(this.substituirVirgolaPorPonto(this.telaCadastroPagar.getjFormattedTextFieldValorPago().getText())))//6            
                    .setValorAcrescimo(Float.parseFloat(this.substituirVirgolaPorPonto(this.telaCadastroPagar.getjFormattedTextFieldValorAcrescimo().getText())))//6            
                    .setObservacao(this.telaCadastroPagar.getjTextAreaObs().getText())//7
                    .setContaAPagar(
                            service.ServiceContaAPagar.BuscarIdDaContaAPagarPeloIdDaCompra(
                                    Integer.parseInt(this.telaCadastroPagar.getjTextField_CompraId().getText())
                            )
                    )//8
                    .createPagar();

            if (codigo == 0) {
                service.ServicePagar.Incluir(pagar);
                
            } else {
                pagar.setId(Integer.parseInt(this.telaCadastroPagar.getjTextFieldId().getText()));
                service.ServicePagar.Atualizar(pagar);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        if (e.getSource() == this.telaCadastroPagar.getjButtonBuscar()) {

            codigo = 0;
            TelaBuscaPagar telaBuscaPagar = new TelaBuscaPagar(null, true);
            ControllerPagarBusca controllerBuscaPagar = new ControllerPagarBusca(telaBuscaPagar);
            telaBuscaPagar.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);
                Pagar pagar = service.ServicePagar.Buscar(codigo);
                
               //ID'S
                this.telaCadastroPagar.getjTextFieldId().setText(pagar.getId() + "");
                this.telaCadastroPagar.getjTextField_CompraId().setText(pagar.getContaAPagar().getCompraId() + "");
                //COMPRA
                this.compra = service.ServiceCompra.Buscar(pagar.getContaAPagar().getCompraId());
                this.telaCadastroPagar.getjTextField_CompraId().setText(pagar.getContaAPagar().getCompraId() + "");
                this.telaCadastroPagar.getjTextField_CompraFornecedor().setText(compra.getFornecedor().getRazaoSocial());
                this.telaCadastroPagar.getjFormattedTextField_CompraData().setText(compra.getData());
                this.telaCadastroPagar.getjFormattedTextField_CompraValor().setText(compra.getValorTotal() + "");
                //DATAS
                this.telaCadastroPagar.getjFormattedTextFieldDataEmissao().setText(compra.getData());
                this.telaCadastroPagar.getjFormattedTextFieldDataVencimento().setText(compra.getDataDeVencimento());
                this.telaCadastroPagar.getjFormattedTextFieldDataPagamento().setText(pagar.getDataPagamento());
                //VALORES
                this.telaCadastroPagar.getjFormattedTextFieldValorEmitido().setText(pagar.getContaAPagar().getValor() + "");
                this.telaCadastroPagar.getjFormattedTextFieldValorAcrescimo().setText(pagar.getValorAcrescimo() + "");
                this.telaCadastroPagar.getjFormattedTextFieldValorPago().setText(pagar.getValorPago()+ "");
                //HORA
                this.telaCadastroPagar.getjFormattedTextField_Hora().setText(pagar.getHora());
                //OBS
                this.telaCadastroPagar.getjTextAreaObs().setText(pagar.getObservacao());
                //FALSE
                this.telaCadastroPagar.getjTextFieldId().setEnabled(false);
                this.telaCadastroPagar.getjTextField_CompraId().setEnabled(false);
                this.telaCadastroPagar.getjFormattedTextFieldDataEmissao().setEnabled(false);
                this.telaCadastroPagar.getjFormattedTextFieldDataVencimento().setEnabled(false);
                this.telaCadastroPagar.getjFormattedTextFieldDataPagamento().setEnabled(false);
                this.telaCadastroPagar.getjFormattedTextFieldValorEmitido().setEnabled(false);
                this.telaCadastroPagar.getjFormattedTextField_Hora().setEnabled(false);
                this.telaCadastroPagar.getjFormattedTextField_CompraValor().setEnabled(false);
                this.telaCadastroPagar.getjFormattedTextField_CompraData().setEnabled(false);
                this.telaCadastroPagar.getjTextField_CompraFornecedor().setEnabled(false);
            }
        }
        else if (e.getSource() == this.telaCadastroPagar.getjButton_CompraBuscaId()) {

                TelaBuscaCompra telaBuscaCompra = new TelaBuscaCompra(null,true);
                ControllerCompraBusca controllerCompraBusca = new ControllerCompraBusca(telaBuscaCompra);
                telaBuscaCompra.setVisible(true);

                //VENDA
                compra = service.ServiceCompra.Buscar(telaBuscaCompra.getIdCompra());
                this.telaCadastroPagar.getjTextField_CompraFornecedor().setText(compra.getFornecedor().getRazaoSocial());
                this.telaCadastroPagar.getjFormattedTextField_CompraData().setText(compra.getData());
                this.telaCadastroPagar.getjFormattedTextField_CompraValor().setText(compra.getValorTotal() + "");
                //DATAS
                this.telaCadastroPagar.getjFormattedTextFieldDataEmissao().setText(compra.getData());
                this.telaCadastroPagar.getjFormattedTextFieldDataVencimento().setText(compra.getDataDeVencimento());
                //VALOR                
                this.telaCadastroPagar.getjFormattedTextFieldValorEmitido().setText(compra.getValorTotal() + "");
                //ID DA VENDA
                this.telaCadastroPagar.getjTextField_CompraId().setText(compra.getId() + "");
            }

        if (e.getSource() == this.telaCadastroPagar.getjButtonSair()) {
            this.telaCadastroPagar.dispose();
        }

    }

    public void Ativa(boolean estadoBotoes) {
        this.telaCadastroPagar.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroPagar.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroPagar.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroPagar.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroPagar.getjButtonSair().setEnabled(estadoBotoes);
        
        this.telaCadastroPagar.getjFormattedTextFieldValorEmitido().setEnabled(estadoBotoes);
        this.telaCadastroPagar.getjFormattedTextFieldValorAcrescimo().setEnabled(estadoBotoes);
        this.telaCadastroPagar.getjFormattedTextFieldValorPago().setEnabled(estadoBotoes);

        this.telaCadastroPagar.getjFormattedTextFieldDataVencimento().setEnabled(estadoBotoes);
        this.telaCadastroPagar.getjFormattedTextFieldDataPagamento().setEnabled(!estadoBotoes);
        this.telaCadastroPagar.getjButton_CompraBuscaId().setEnabled(!estadoBotoes);
    }

    public void LimpaEstadoComponentes(boolean estadoCompo) {
        Component[] componentes = this.telaCadastroPagar.getjPanelDados().getComponents(); //verificar
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

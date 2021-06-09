
package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.bo.Pagar;
import model.bo.Venda;
import view.TelaBuscaPagar;
import view.TelaCadastroPagar;


public class ControllerPagar implements ActionListener{
    
    TelaCadastroPagar telaCadastroPagar = new TelaCadastroPagar();
    public static int codigo;
    
    public ControllerPagar(TelaCadastroPagar telaCadastroPagar){
       
        this.telaCadastroPagar = telaCadastroPagar;
        
        this.telaCadastroPagar.getjButtonNovo().addActionListener(this);
        this.telaCadastroPagar.getjButtonBuscar().addActionListener(this);
        this.telaCadastroPagar.getjButtonCancelar().addActionListener(this);
        this.telaCadastroPagar.getjButtonGravar().addActionListener(this);
        this.telaCadastroPagar.getjButtonSair().addActionListener(this);
             
        Ativa(true);
        LimpaEstadoComponentes(false);
        
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.telaCadastroPagar.getjButtonNovo()){            
            Ativa(false);
            LimpaEstadoComponentes(true);            
            this.telaCadastroPagar.getjTextFieldId().setEnabled(false);
            codigo = 0;
        }        
        else if(e.getSource() == this.telaCadastroPagar.getjButtonCancelar()){
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        else if(e.getSource() == this.telaCadastroPagar.getjButtonGravar()){            
            //montar objeto a persistir
            Pagar pagar = new Pagar();           
            
            pagar.setValorRecebido(Float.parseFloat(this.telaCadastroPagar.getjFormattedTextFieldValorPago().getText()));
            pagar.setValorDeDescontoNegociado(Float.parseFloat(this.telaCadastroPagar.getjFormattedTextFieldValorDesconto().getText()));
            pagar.setValorDeAcrescimo(Float.parseFloat(this.telaCadastroPagar.getjFormattedTextFieldValorDesconto().getText()));
            pagar.setData(this.telaCadastroPagar.getjFormattedTextFieldDataEmissao().getText());
            pagar.setVenda((Venda)this.telaCadastroPagar.getjComboBoxVendasId().getSelectedItem());
            
            if(codigo == 0){
                service.ServicePagar.Incluir(pagar);
            }else{
                pagar.setId(Integer.parseInt(this.telaCadastroPagar.getjTextFieldId().getText()));
                service.ServicePagar.Atualizar(pagar);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }        
        if(e.getSource() == this.telaCadastroPagar.getjButtonBuscar()){
           
            codigo =0;
            TelaBuscaPagar telaBuscaPagar = new TelaBuscaPagar(null, true);
            ControllerBuscaPagar controllerBuscaPagar = new ControllerBuscaPagar(telaBuscaPagar);
            telaBuscaPagar.setVisible(true);
            
            
           
            
            if(codigo != 0){
                Ativa(false);
                LimpaEstadoComponentes(true);
                Pagar pagar = new Pagar();
                pagar = service.ServicePagar.Buscar(codigo);
                this.telaCadastroPagar.getjTextFieldId().setText(pagar.getId() + "");
                this.telaCadastroPagar.getjFormattedTextFieldValorEmitido().setText(pagar.getValorRecebido()+ "");
                this.telaCadastroPagar.getjFormattedTextFieldValorDesconto().setText(pagar.getValorDeDescontoNegociado()+"");
                this.telaCadastroPagar.getjFormattedTextFieldValorAcrescimo().setText(pagar.getValorDeAcrescimo()+"");
                this.telaCadastroPagar.getjFormattedTextFieldDataEmissao().setText(pagar.getData());
                this.telaCadastroPagar.getjComboBoxVendasId().setSelectedItem(pagar.getVenda());
               
                
                this.telaCadastroPagar.getjTextFieldId().setEnabled(false);
            }
        }
        
        if(e.getSource() == this.telaCadastroPagar.getjButtonSair()){
            this.telaCadastroPagar.dispose();
        }

    }
    
    public void Ativa(boolean estadoBotoes){
        this.telaCadastroPagar.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroPagar.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroPagar.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroPagar.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroPagar.getjButtonSair().setEnabled(estadoBotoes);        
    }
    
    public void LimpaEstadoComponentes(boolean estadoCompo){
        Component[] componentes = this.telaCadastroPagar.getjPanelDados().getComponents(); //verificar
        for(Component componente : componentes){
             if(componente instanceof JTextField){
                    ((JTextField)componente).setText("");
                    componente.setEnabled(estadoCompo);
                }

                if(componente instanceof JFormattedTextField){
                    ((JFormattedTextField) componente).setText("");
                    componente.setEnabled(estadoCompo);
                }

                if(componente instanceof JComboBox){
                    ((JComboBox) componente).setSelectedItem(0);
                    componente.setEnabled(estadoCompo);
                }


                if((componente instanceof JTextArea)){
                    ((JTextArea) componente).setToolTipText("");
                    ((JTextArea) componente).setEditable(estadoCompo);
                }

                if((componente instanceof JTextArea)){
                    ((JTextArea) componente).setText("");
                    componente.setEnabled(estadoCompo);
                }
                if(componente instanceof  JComboBox){
                    ((JComboBox) componente).setSelectedItem(0);
                    componente.setEnabled(estadoCompo);
                }
            
        } 
    }
    
}


package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import model.bo.Compra;
import view.TelaBuscaCompra;
import view.TelaCadastroCompra;


public class ControllerCompra implements ActionListener{
    
    TelaCadastroCompra telaCadastroCompra = new TelaCadastroCompra();
    public static int codigo;
    
    public ControllerCompra(TelaCadastroCompra telaCadastroCompra){
       
        this.telaCadastroCompra = telaCadastroCompra;
        
        this.telaCadastroCompra.getjButtonNovo().addActionListener(this);
        this.telaCadastroCompra.getjButtonBuscar().addActionListener(this);
        this.telaCadastroCompra.getjButtonCancelar().addActionListener(this);
        this.telaCadastroCompra.getjButtonGravar().addActionListener(this);
        this.telaCadastroCompra.getjButtonSair().addActionListener(this);
             
        Ativa(true);
        LimpaEstadoComponentes(false);
        
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.telaCadastroCompra.getjButtonNovo()){            
            Ativa(false);
            LimpaEstadoComponentes(true);            
            this.telaCadastroCompra.getjTextFieldId().setEnabled(false);
            codigo = 0;
        }        
        else if(e.getSource() == this.telaCadastroCompra.getjButtonCancelar()){
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        else if(e.getSource() == this.telaCadastroCompra.getjButtonGravar()){            
            //montar objeto a persistir
            Compra compra = new Compra();           
            
            compra.setData(this.telaCadastroCompra.getjFormattedTextFieldDataVenda().getText());
            compra.setHora(this.telaCadastroCompra.getjFormattedTextFieldHoraVenda().getText());
            compra.setValorDeDesconto(Float.parseFloat(this.telaCadastroCompra.getjFormattedTextFieldValorDesconto().getText()));
            compra.setValorTotal(Float.parseFloat(this.telaCadastroCompra.getjFormattedTextFieldValorTotal().getText()));
            
            if(codigo == 0){
                service.ServiceCompra.Incluir(compra);
            }else{
                compra.setId(Integer.parseInt(this.telaCadastroCompra.getjTextFieldId().getText()));
                service.ServiceCompra.Atualizar(compra);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }        
        if(e.getSource() == this.telaCadastroCompra.getjButtonBuscar()){
           
            codigo =0;
            TelaBuscaCompra telaBuscaCompra = new TelaBuscaCompra(null, true);
            ControllerBuscaCompra controllerBuscaCompra = new ControllerBuscaCompra(telaBuscaCompra);
            telaBuscaCompra.getjButtonCarregar().setVisible(true);
            
            
           
            
            if(codigo != 0){
                Ativa(false);
                LimpaEstadoComponentes(true);
                Compra compra = new Compra();
                compra = service.ServiceCompra.Buscar(codigo);
                this.telaCadastroCompra.getjTextFieldId().setText(compra.getId() + "");
                this.telaCadastroCompra.getjFormattedTextFieldDataVenda().setText(compra.getData());
                this.telaCadastroCompra.getjFormattedTextFieldHoraVenda().setText(compra.getHora());
                this.telaCadastroCompra.getjFormattedTextFieldValorDesconto().setText(compra.getValorDeDesconto()+"");
                this.telaCadastroCompra.getjFormattedTextFieldValorTotal().setText(compra.getValorTotal()+"");
                
                this.telaCadastroCompra.getjTextFieldId().setEnabled(false);
            }
        }
        
        if(e.getSource() == this.telaCadastroCompra.getjButtonSair()){
            this.telaCadastroCompra.dispose();
        }

    }
    
    public void Ativa(boolean estadoBotoes){
        this.telaCadastroCompra.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroCompra.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroCompra.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroCompra.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroCompra.getjButtonSair().setEnabled(estadoBotoes);        
    }
    
    public void LimpaEstadoComponentes(boolean estadoCompo){
        Component[] componentes = this.telaCadastroCompra.getjPanelDados().getComponents(); //verificar
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
            
        } 
    }
    
}

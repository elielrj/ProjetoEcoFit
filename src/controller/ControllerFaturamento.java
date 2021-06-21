
package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.bo.Venda;
import view.TelaFaturamento;


public class ControllerFaturamento implements ActionListener{
    
    TelaFaturamento telaFaturamento = new TelaFaturamento();
    public static int codigo;
    
    public ControllerFaturamento(TelaFaturamento telaFaturamento){
       
        this.telaFaturamento = telaFaturamento;
        
        this.telaFaturamento.getjButtonNovo().addActionListener(this);
        this.telaFaturamento.getjButtonBuscar().addActionListener(this);
        this.telaFaturamento.getjButtonCancelar().addActionListener(this);
        this.telaFaturamento.getjButtonGravar().addActionListener(this);
        this.telaFaturamento.getjButtonSair().addActionListener(this);
             
        Ativa(true);
        LimpaEstadoComponentes(false);
        KeyEvent e;
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.telaFaturamento.getjButtonNovo()){            
            Ativa(false);
            LimpaEstadoComponentes(true);            
            this.telaFaturamento.getjTextFieldClienteId().setEnabled(false);
            this.telaFaturamento.getjTextFieldClienteNome().setEnabled(false);
            this.telaFaturamento.getjTextFieldClienteCidade().setEnabled(false);
            this.telaFaturamento.getjTextFieldClienteBairro().setEnabled(false);
            this.telaFaturamento.getjTextFieldClienteEmail().setEnabled(false);
            this.telaFaturamento.getjComboBoxClienteTels().setEnabled(false);
            codigo = 0;
        }        
        else if(e.getSource() == this.telaFaturamento.getjButtonCancelar()){
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        else if(e.getSource() == this.telaFaturamento.getjButtonGravar()){            
            //montar objeto a persistir
            Venda venda = new Venda();           
            
            venda.setData(this.telaFaturamento.getjFormattedTextFieldFaturamentoData().getText());
            venda.setHora(this.telaFaturamento.getjFormattedTextFieldFaturamentoHora().getText());
            venda.setStatus(false);
            
            
            if(codigo == 0){
                service.ServiceVenda.Incluir(venda);
            }else{
                venda.setId(Integer.parseInt(this.telaFaturamento.getjTextFieldIdVenda().getText()));
                service.ServiceVenda.Atualizar(venda);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }        
        if(e.getSource() == this.telaFaturamento.getjButtonBuscar()){
           
            codigo =0;
            //TelaBuscaFaturamento telaBuscaVenda = new TelaBuscaFaturamento(null, true);
            //ControllerBuscaVenda controllerBuscaVenda = new ControllerBuscaVenda(telaBuscaVenda);
            //telaBuscaVenda.setVisible(true);
            
            
           
            
            if(codigo != 0){
                Ativa(false);
                LimpaEstadoComponentes(true);
                Venda venda = new Venda();
                venda = service.ServiceVenda.Buscar(codigo);
                
                this.telaFaturamento.getjTextFieldIdVenda().setText(venda.getId() + "");
                this.telaFaturamento.getjFormattedTextFieldFaturamentoData().setText(venda.getData());
                this.telaFaturamento.getjFormattedTextFieldFaturamentoHora().setText(venda.getHora());                
                //this.telaFaturamento.getjComboBoxStatus().setSelectedItem(venda.getStatus());
                
                this.telaFaturamento.getjTextFieldIdVenda().setEnabled(false);
            }
        }
        
        if(e.getSource() == this.telaFaturamento.getjButtonSair()){
            this.telaFaturamento.dispose();
        }
        
       

    }
    
    public void Ativa(boolean estadoBotoes){
        this.telaFaturamento.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaFaturamento.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaFaturamento.getjButtonSair().setEnabled(estadoBotoes);        
    }
    
        public void LimpaEstadoComponentes(boolean estadoCompo){

            Component[] componentes = this.telaFaturamento.getjPanelDados().getComponents(); //verificar
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

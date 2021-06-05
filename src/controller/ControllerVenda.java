
package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import model.bo.PessoaFisica;
import model.bo.Venda;
import view.TelaBuscaVenda;
import view.TelaCadastroVenda;


public class ControllerVenda implements ActionListener{
    
    TelaCadastroVenda telaCadastroVenda = new TelaCadastroVenda();
    public static int codigo;
    
    public ControllerVenda(TelaCadastroVenda telaCadastroVenda){
       
        this.telaCadastroVenda = telaCadastroVenda;
        
        this.telaCadastroVenda.getjButtonNovo().addActionListener(this);
        this.telaCadastroVenda.getjButtonBuscar().addActionListener(this);
        this.telaCadastroVenda.getjButtonCancelar().addActionListener(this);
        this.telaCadastroVenda.getjButtonGravar().addActionListener(this);
        this.telaCadastroVenda.getjButtonSair().addActionListener(this);
             
        Ativa(true);
        LimpaEstadoComponentes(false);
        
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.telaCadastroVenda.getjButtonNovo()){            
            Ativa(false);
            LimpaEstadoComponentes(true);            
            this.telaCadastroVenda.getjTextFieldId().setEnabled(false);
            codigo = 0;
        }        
        else if(e.getSource() == this.telaCadastroVenda.getjButtonCancelar()){
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        else if(e.getSource() == this.telaCadastroVenda.getjButtonGravar()){            
            //montar objeto a persistir
            Venda venda = new Venda();           
            
            venda.setData(this.telaCadastroVenda.getjFormattedTextFieldDataVenda().getText());
            venda.setHora(this.telaCadastroVenda.getjFormattedTextFieldHoraVenda().getText());
            venda.setValorDoDesconto(Float.parseFloat(this.telaCadastroVenda.getjFormattedTextFieldValorDesconto().getText()));
            venda.setValorTotal(Float.parseFloat(this.telaCadastroVenda.getjFormattedTextFieldValorTotal().getText()));
            venda.setPessoaFisica((PessoaFisica)this.telaCadastroVenda.getjComboBoxPessoaFisica().getSelectedItem());
            venda.setDataDeVencimento(this.telaCadastroVenda.getjFormattedTextFieldDataDeVencimento().getText());
            venda.setStatus(this.telaCadastroVenda.getjComboBoxStatus().getSelectedItem().equals("Sim"));
            venda.setObservacao(this.telaCadastroVenda.getjTextAreaObs().getText());
            
            if(codigo == 0){
                service.ServiceVenda.Incluir(venda);
            }else{
                venda.setId(Integer.parseInt(this.telaCadastroVenda.getjTextFieldId().getText()));
                service.ServiceVenda.Atualizar(venda);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }        
        if(e.getSource() == this.telaCadastroVenda.getjButtonBuscar()){
           
            codigo =0;
            TelaBuscaVenda telaBuscaVenda = new TelaBuscaVenda(null, true);
            ControllerBuscaVenda controllerBuscaVenda = new ControllerBuscaVenda(telaBuscaVenda);
            telaBuscaVenda.setVisible(true);
            
            
           
            
            if(codigo != 0){
                Ativa(false);
                LimpaEstadoComponentes(true);
                Venda venda = new Venda();
                venda = service.ServiceVenda.Buscar(codigo);
                this.telaCadastroVenda.getjTextFieldId().setText(venda.getId() + "");
                this.telaCadastroVenda.getjFormattedTextFieldDataVenda().setText(venda.getData());
                this.telaCadastroVenda.getjFormattedTextFieldHoraVenda().setText(venda.getHora());
                this.telaCadastroVenda.getjFormattedTextFieldValorDesconto().setText(venda.getValorDoDesconto()+"");
                this.telaCadastroVenda.getjFormattedTextFieldValorTotal().setText(venda.getValorTotal()+"");
                this.telaCadastroVenda.getjComboBoxAluno().setSelectedItem(venda.getPessoaFisica());
                this.telaCadastroVenda.getjFormattedTextFieldDataDeVencimento().setText(venda.getDataDeVencimento());
                this.telaCadastroVenda.getjTextAreaObs().setText(venda.getObservacao());
                this.telaCadastroVenda.getjTextFieldId().setEnabled(false);
            }
        }
        
        if(e.getSource() == this.telaCadastroVenda.getjButtonSair()){
            this.telaCadastroVenda.dispose();
        }

    }
    
    public void Ativa(boolean estadoBotoes){
        this.telaCadastroVenda.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroVenda.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroVenda.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroVenda.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroVenda.getjButtonSair().setEnabled(estadoBotoes);        
    }
    
    public void LimpaEstadoComponentes(boolean estadoCompo){
        Component[] componentes = this.telaCadastroVenda.getjPanelDados().getComponents(); //verificar
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

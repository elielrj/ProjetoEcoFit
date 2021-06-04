
package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import model.bo.Personal;
import model.bo.Bairro;
import model.bo.Cidade;
import model.bo.Endereco;

import view.TelaBuscaPersonal;
import view.TelaCadastroPersonal;


public class ControllerPersonal implements ActionListener{
    
    TelaCadastroPersonal telaCadastroPersonal = new TelaCadastroPersonal();
    public static int codigo;
    
    public ControllerPersonal(TelaCadastroPersonal telaCadastroPersonal){
       
        this.telaCadastroPersonal = telaCadastroPersonal;
        
        this.telaCadastroPersonal.getjButtonNovo().addActionListener(this);
        this.telaCadastroPersonal.getjButtonBuscar().addActionListener(this);
        this.telaCadastroPersonal.getjButtonCancelar().addActionListener(this);
        this.telaCadastroPersonal.getjButtonGravar().addActionListener(this);
        this.telaCadastroPersonal.getjButtonSair().addActionListener(this);
             
        Ativa(true);
        LimpaEstadoComponentes(false);
        
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.telaCadastroPersonal.getjButtonNovo()){            
            Ativa(false);
            LimpaEstadoComponentes(true);            
            this.telaCadastroPersonal.getjTextFieldId().setEnabled(false);
            codigo = 0;
        }        
        else if(e.getSource() == this.telaCadastroPersonal.getjButtonCancelar()){
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        else if(e.getSource() == this.telaCadastroPersonal.getjButtonGravar()){            
            //montar objeto a persistir
            Personal personal = new Personal();           
            personal.setNome(this.telaCadastroPersonal.getjTextFieldNome().getText());
            personal.setTelefone1(this.telaCadastroPersonal.getjFormattedTextFieldTel1().getText());
            personal.setTelefone2(this.telaCadastroPersonal.getjFormattedTextFieldTel2().getText());
            personal.setRg(this.telaCadastroPersonal.getjFormattedTextFieldRg().getText());
            personal.setCpf(this.telaCadastroPersonal.getjFormattedTextFieldCpf().getText());
            personal.setComplemento(this.telaCadastroPersonal.getjTextFieldComplemento().getText());            
            personal.setDataDeNascimento(this.telaCadastroPersonal.getjFormattedTextFieldDataNascimento().getText());
            personal.setEmail(this.telaCadastroPersonal.getjTextFieldEmail().getText());            
            
            
            //Endereco endereco = new Endereco();

            personal.setEndereco((Endereco) this.telaCadastroPersonal.getjComboBoxEndereco().getSelectedItem());
            
            
            
            
            //set busca endereco.Id e seta em Endereco           
            if(codigo == 0){
               
                service.ServicePersonal.Incluir(personal);
            }else{
                
                
                personal.setId(Integer.parseInt(this.telaCadastroPersonal.getjTextFieldId().getText()));
                service.ServicePersonal.Atualizar(personal);
            }
            Ativa(true);
                LimpaEstadoComponentes(false);
        }        
        if(e.getSource() == this.telaCadastroPersonal.getjButtonBuscar()){
           
            codigo =0;
            TelaBuscaPersonal telaCadastroPersonal = new TelaBuscaPersonal(null, true);
            ControllerBuscaPersonal controllerBuscaPersonal = new ControllerBuscaPersonal(telaCadastroPersonal);
            telaCadastroPersonal.setVisible(true);
            
            
           
            
            if(codigo != 0){
                Ativa(false);
                LimpaEstadoComponentes(true);
                Personal personal = new Personal();
                personal = service.ServicePersonal.Buscar(codigo);
                this.telaCadastroPersonal.getjTextFieldId().setText(personal.getId() + "");
                this.telaCadastroPersonal.getjTextFieldNome().setText(personal.getNome());
                this.telaCadastroPersonal.getjTextFieldEmail().setText(personal.getEmail());
                this.telaCadastroPersonal.getjFormattedTextFieldTel1().setText(personal.getTelefone1());
                this.telaCadastroPersonal.getjFormattedTextFieldTel2().setText(personal.getTelefone2());
                this.telaCadastroPersonal.getjFormattedTextFieldRg().setText(personal.getRg());
                this.telaCadastroPersonal.getjFormattedTextFieldCpf().setText(personal.getCpf());
                this.telaCadastroPersonal.getjFormattedTextFieldDataNascimento().setText(personal.getDataDeNascimento());
                this.telaCadastroPersonal.getjTextFieldComplemento().setText(personal.getComplemento());
                
               // Endereco endereco = new Endereco();
                this.telaCadastroPersonal.getjComboBoxEndereco().setSelectedItem(personal.getEndereco());
                
                this.telaCadastroPersonal.getjTextFieldId().setEnabled(false);
            }
        }
        
        if(e.getSource() == this.telaCadastroPersonal.getjButtonSair()){
            this.telaCadastroPersonal.dispose();
        }

    }
    
    public void Ativa(boolean estadoBotoes){
        this.telaCadastroPersonal.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroPersonal.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroPersonal.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroPersonal.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroPersonal.getjButtonSair().setEnabled(estadoBotoes);        
    }
    
    public void LimpaEstadoComponentes(boolean estadoCompo){
        Component[] componentes = this.telaCadastroPersonal.getjPanelDados().getComponents(); //verificar
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

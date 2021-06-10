
package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.bo.PessoaFisica;
import model.bo.Endereco;

import view.TelaBuscaPessoaFisica;
import view.TelaCadastroPessoaFisica;


public class ControllerPessoaFisica implements ActionListener{
    
    TelaCadastroPessoaFisica telaCadastroPessoaFisica = new TelaCadastroPessoaFisica();
    public static int codigo;
   

    
    public ControllerPessoaFisica(TelaCadastroPessoaFisica telaCadastroAluno){
       
        this.telaCadastroPessoaFisica = telaCadastroAluno;
        
        this.telaCadastroPessoaFisica.getjButtonNovo().addActionListener(this);
        this.telaCadastroPessoaFisica.getjButtonBuscar().addActionListener(this);
        this.telaCadastroPessoaFisica.getjButtonCancelar().addActionListener(this);
        this.telaCadastroPessoaFisica.getjButtonGravar().addActionListener(this);
        this.telaCadastroPessoaFisica.getjButtonSair().addActionListener(this);
        
          
        Ativa(true);
                LimpaEstadoComponentes(false);

        
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.telaCadastroPessoaFisica.getjButtonNovo()){            
            Ativa(false);
            LimpaEstadoComponentes(true);  
            this.telaCadastroPessoaFisica.getjTextFieldId().setEnabled(false);
            codigo = 0;
        }        
        else if(e.getSource() == this.telaCadastroPessoaFisica.getjButtonCancelar()){
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        else if(e.getSource() == this.telaCadastroPessoaFisica.getjButtonGravar()){            
            //montar objeto a persistir
            PessoaFisica pessoaFisica = new PessoaFisica();           
            
            pessoaFisica.setNome(this.telaCadastroPessoaFisica.getjTextFieldNome().getText());
            pessoaFisica.setTelefone1(this.telaCadastroPessoaFisica.getjFormattedTextFieldTel1().getText());
            pessoaFisica.setTelefone2(this.telaCadastroPessoaFisica.getjFormattedTextFieldTel2().getText());
            pessoaFisica.setRg(this.telaCadastroPessoaFisica.getjFormattedTextFieldRg().getText());
            
            pessoaFisica.setCpf(this.telaCadastroPessoaFisica.getjFormattedTextFieldCpf().getText());
            pessoaFisica.setObservacao(this.telaCadastroPessoaFisica.getjTextAreaObservacao().getText());            
            pessoaFisica.setDataDeNascimento(this.telaCadastroPessoaFisica.getjFormattedTextFieldDataNascimento().getText());
            pessoaFisica.setEmail(this.telaCadastroPessoaFisica.getjTextFieldEmail().getText());    
            
            pessoaFisica.setStatus(this.telaCadastroPessoaFisica.getjComboBoxStatus().getSelectedItem().equals("Sim"));            
            pessoaFisica.setEndereco((Endereco) this.telaCadastroPessoaFisica.getjComboBoxEndereco().getSelectedItem());
            pessoaFisica.setTipo((String) this.telaCadastroPessoaFisica.getjComboBoxTipo().getSelectedItem());
        

                      
            if(codigo == 0){
                service.ServicePessoaFisica.Incluir(pessoaFisica);              
            }else{

                
                pessoaFisica.setId(Integer.parseInt(this.telaCadastroPessoaFisica.getjTextFieldId().getText()));
                service.ServicePessoaFisica.Atualizar(pessoaFisica);
            }
            Ativa(true);
                LimpaEstadoComponentes(false);
        }        
        if(e.getSource() == this.telaCadastroPessoaFisica.getjButtonBuscar()){
           
            codigo =0;
            TelaBuscaPessoaFisica telaBuscaPessoaFisica = new TelaBuscaPessoaFisica(null, true);
            ControllerBuscaPessoaFisica controllerBuscaPessoaFisica = new ControllerBuscaPessoaFisica(telaBuscaPessoaFisica);
            telaBuscaPessoaFisica.setVisible(true);
            
            
           
            
            if(codigo != 0){
                Ativa(false);
                LimpaEstadoComponentes(true);
                PessoaFisica pessoaFisica = new PessoaFisica();
                pessoaFisica = service.ServicePessoaFisica.Buscar(codigo);
                
                this.telaCadastroPessoaFisica.getjTextFieldId().setText(pessoaFisica.getId() + "");
                this.telaCadastroPessoaFisica.getjTextFieldNome().setText(pessoaFisica.getNome());
                this.telaCadastroPessoaFisica.getjTextFieldEmail().setText(pessoaFisica.getEmail());
                
                this.telaCadastroPessoaFisica.getjFormattedTextFieldTel1().setText(pessoaFisica.getTelefone1());
                this.telaCadastroPessoaFisica.getjFormattedTextFieldTel2().setText(pessoaFisica.getTelefone2());
                this.telaCadastroPessoaFisica.getjFormattedTextFieldRg().setText(pessoaFisica.getRg());
                
                this.telaCadastroPessoaFisica.getjFormattedTextFieldCpf().setText(pessoaFisica.getCpf());
                this.telaCadastroPessoaFisica.getjFormattedTextFieldDataNascimento().setText(pessoaFisica.getDataDeNascimento());
                this.telaCadastroPessoaFisica.getjTextAreaObservacao().setText(pessoaFisica.getObservacao());
                
                this.telaCadastroPessoaFisica.getjComboBoxStatus().setSelectedItem(pessoaFisica.getStatus());
                
                //Endereco endereco = new Endereco();

                this.telaCadastroPessoaFisica.getjComboBoxEndereco().setSelectedItem(pessoaFisica.getEndereco());                
                this.telaCadastroPessoaFisica.getjComboBoxTipo().setSelectedItem(pessoaFisica.getTipo());                
                
                this.telaCadastroPessoaFisica.getjTextFieldId().setEnabled(false);
            }
        }
        
        if(e.getSource() == this.telaCadastroPessoaFisica.getjButtonSair()){
            this.telaCadastroPessoaFisica.dispose();
        }

    }
    
    public void Ativa(boolean estadoBotoes){
        this.telaCadastroPessoaFisica.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroPessoaFisica.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroPessoaFisica.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroPessoaFisica.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroPessoaFisica.getjButtonSair().setEnabled(estadoBotoes);        
    }
    
   public void LimpaEstadoComponentes(boolean estadoCompo){
    
        Component[] componentes = this.telaCadastroPessoaFisica.getjPanelDados().getComponents(); //verificar
    
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

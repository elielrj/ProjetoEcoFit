
package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DAO.Enderecos.EnderecoDAO;
import model.bo.PessoaFisica;
import model.bo.Bairro;
import model.bo.Cidade;
import model.bo.Endereco;

import view.TelaBuscaPessoaFisica;
import view.TelaCadastroAluno;


public class ControllerPessoaFisica implements ActionListener{
    
    TelaCadastroAluno telaCadastroAluno = new TelaCadastroAluno();
    public static int codigo;
   

    
    public ControllerPessoaFisica(TelaCadastroAluno telaCadastroAluno){
       
        this.telaCadastroAluno = telaCadastroAluno;
        
        this.telaCadastroAluno.getjButtonNovo().addActionListener(this);
        this.telaCadastroAluno.getjButtonBuscar().addActionListener(this);
        this.telaCadastroAluno.getjButtonCancelar().addActionListener(this);
        this.telaCadastroAluno.getjButtonGravar().addActionListener(this);
        this.telaCadastroAluno.getjButtonSair().addActionListener(this);
        
        
          
        
        Ativa(true);
        LimpaEstadoComponentes(false);
        
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.telaCadastroAluno.getjButtonNovo()){            
            Ativa(false);
            LimpaEstadoComponentes(true);            
            this.telaCadastroAluno.getjTextFieldId().setEnabled(false);
            codigo = 0;
        }        
        else if(e.getSource() == this.telaCadastroAluno.getjButtonCancelar()){
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        else if(e.getSource() == this.telaCadastroAluno.getjButtonGravar()){            
            //montar objeto a persistir
            PessoaFisica aluno = new PessoaFisica();           
            aluno.setNome(this.telaCadastroAluno.getjTextFieldNome().getText());
            aluno.setTelefone1(this.telaCadastroAluno.getjFormattedTextFieldTel1().getText());
            aluno.setTelefone2(this.telaCadastroAluno.getjFormattedTextFieldTel2().getText());
            aluno.setRg(this.telaCadastroAluno.getjFormattedTextFieldRg().getText());
            aluno.setCpf(this.telaCadastroAluno.getjFormattedTextFieldCpf().getText());
            aluno.setObservacao(this.telaCadastroAluno.getjTextAreaObservacao().getText());            
            aluno.setDataDeNascimento(this.telaCadastroAluno.getjFormattedTextFieldDataNascimento().getText());
            aluno.setEmail(this.telaCadastroAluno.getjTextFieldEmail().getText());    
            aluno.setStatus(this.telaCadastroAluno.getjComboBoxStatus().getSelectedItem().equals("Sim"));
            
            
            
            aluno.setEndereco((Endereco) this.telaCadastroAluno.getjComboBoxEndereco().getSelectedItem());
        

                      
            if(codigo == 0){
                service.ServicePessoaFisica.Incluir(aluno);              
            }else{

                
                aluno.setId(Integer.parseInt(this.telaCadastroAluno.getjTextFieldId().getText()));
                service.ServicePessoaFisica.Atualizar(aluno);
            }
            Ativa(true);
                LimpaEstadoComponentes(false);
        }        
        if(e.getSource() == this.telaCadastroAluno.getjButtonBuscar()){
           
            codigo =0;
            TelaBuscaPessoaFisica telaCadastroAluno = new TelaBuscaPessoaFisica(null, true);
            ControllerBuscaAluno controllerBuscaAluno = new ControllerBuscaAluno(telaCadastroAluno);
            telaCadastroAluno.setVisible(true);
            
            
           
            
            if(codigo != 0){
                Ativa(false);
                LimpaEstadoComponentes(true);
                PessoaFisica aluno = new PessoaFisica();
                aluno = service.ServicePessoaFisica.Buscar(codigo);
                
                this.telaCadastroAluno.getjTextFieldId().setText(aluno.getId() + "");
                this.telaCadastroAluno.getjTextFieldNome().setText(aluno.getNome());
                this.telaCadastroAluno.getjTextFieldEmail().setText(aluno.getEmail());
                this.telaCadastroAluno.getjFormattedTextFieldTel1().setText(aluno.getTelefone1());
                this.telaCadastroAluno.getjFormattedTextFieldTel2().setText(aluno.getTelefone2());
                this.telaCadastroAluno.getjFormattedTextFieldRg().setText(aluno.getRg());
                this.telaCadastroAluno.getjFormattedTextFieldCpf().setText(aluno.getCpf());
                this.telaCadastroAluno.getjFormattedTextFieldDataNascimento().setText(aluno.getDataDeNascimento());

                this.telaCadastroAluno.getjTextAreaObservacao().setText(aluno.getObservacao());
                this.telaCadastroAluno.getjComboBoxStatus().setSelectedItem(aluno.getStatus());
                
                //Endereco endereco = new Endereco();

                this.telaCadastroAluno.getjComboBoxEndereco().setSelectedItem(aluno.getEndereco());                
                
                this.telaCadastroAluno.getjTextFieldId().setEnabled(false);
            }
        }
        
        if(e.getSource() == this.telaCadastroAluno.getjButtonSair()){
            this.telaCadastroAluno.dispose();
        }

    }
    
    public void Ativa(boolean estadoBotoes){
        this.telaCadastroAluno.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroAluno.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroAluno.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroAluno.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroAluno.getjButtonSair().setEnabled(estadoBotoes);        
    }
    
    public void LimpaEstadoComponentes(boolean estadoCompo){
    
        Component[] componentes = this.telaCadastroAluno.getjPanelDados().getComponents(); //verificar
    
        LimpaEstadoDeComponentes.limpa(estadoCompo, componentes);
        
    }    
    
}

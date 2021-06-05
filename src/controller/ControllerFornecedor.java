
package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import model.DAO.EnderecoDAO;
import model.bo.Fornecedor;
import model.bo.Bairro;
import model.bo.Cidade;
import model.bo.Endereco;

import view.TelaBuscaFornecedor;
import view.TelaCadastroFornecedor;


public class ControllerFornecedor implements ActionListener{
    
    TelaCadastroFornecedor telaCadastroFornecedor = new TelaCadastroFornecedor();
    public static int codigo;
   

    
    public ControllerFornecedor(TelaCadastroFornecedor telaCadastroFornecedor){
       
        this.telaCadastroFornecedor = telaCadastroFornecedor;
        
        this.telaCadastroFornecedor.getjButtonNovo().addActionListener(this);
        this.telaCadastroFornecedor.getjButtonBuscar().addActionListener(this);
        this.telaCadastroFornecedor.getjButtonCancelar().addActionListener(this);
        this.telaCadastroFornecedor.getjButtonGravar().addActionListener(this);
        this.telaCadastroFornecedor.getjButtonSair().addActionListener(this);
        
        
          
        
        Ativa(true);
        LimpaEstadoComponentes(false);
        
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.telaCadastroFornecedor.getjButtonNovo()){            
            Ativa(false);
            LimpaEstadoComponentes(true);            
            this.telaCadastroFornecedor.getjTextFieldId().setEnabled(false);
            codigo = 0;
        }        
        else if(e.getSource() == this.telaCadastroFornecedor.getjButtonCancelar()){
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        else if(e.getSource() == this.telaCadastroFornecedor.getjButtonGravar()){            
            //montar objeto a persistir
            Fornecedor fornecedor = new Fornecedor();           
            
            fornecedor.setRazaoSocial(this.telaCadastroFornecedor.getjTextFieldRazaoSocial().getText());
            fornecedor.setCnpj(this.telaCadastroFornecedor.getjFormattedTextFieldCnpj().getText());
            fornecedor.setInscricaoEstadual(this.telaCadastroFornecedor.getjFormattedTextFieldInscEst().getText());
            fornecedor.setTelefone1(this.telaCadastroFornecedor.getjFormattedTextFieldTel1().getText());
            fornecedor.setTelefone2(this.telaCadastroFornecedor.getjFormattedTextFieldtel2().getText());
            
            fornecedor.setEmail(this.telaCadastroFornecedor.getjTextFieldEmail().getText());            
            fornecedor.setEndereco((Endereco)this.telaCadastroFornecedor.getjComboBoxEndereco().getSelectedItem());
            fornecedor.setEmail(this.telaCadastroFornecedor.getjTextFieldEmail().getText());            
            fornecedor.setObservaca(this.telaCadastroFornecedor.getjTextAreaObs().getText());
            fornecedor.setStatus(this.telaCadastroFornecedor.getjComboBoxStatus().getSelectedItem().equals("Sim"));
            
            
             

                      
            if(codigo == 0){
                service.ServiceFornecedor.Incluir(fornecedor);              
            }else{

                
                fornecedor.setId(Integer.parseInt(this.telaCadastroFornecedor.getjTextFieldId().getText()));
                service.ServiceFornecedor.Atualizar(fornecedor);
            }
            Ativa(true);
                LimpaEstadoComponentes(false);
        }        
        if(e.getSource() == this.telaCadastroFornecedor.getjButtonBuscar()){
           
            codigo =0;
            TelaBuscaFornecedor telaCadastroFornecedor = new TelaBuscaFornecedor(null, true);
            ControllerBuscaFornecedor controllerBuscaFornecedor = new ControllerBuscaFornecedor(telaCadastroFornecedor);
            telaCadastroFornecedor.setVisible(true);
            
            
           
            
            if(codigo != 0){
                Ativa(false);
                LimpaEstadoComponentes(true);
                Fornecedor fornecedor = new Fornecedor();
                fornecedor = service.ServiceFornecedor.Buscar(codigo);
                
                this.telaCadastroFornecedor.getjTextFieldId().setText(fornecedor.getId()+"");
                this.telaCadastroFornecedor.getjTextFieldRazaoSocial().setText(fornecedor.getRazaoSocial());
                this.telaCadastroFornecedor.getjFormattedTextFieldCnpj().setText(fornecedor.getCnpj());
                this.telaCadastroFornecedor.getjFormattedTextFieldInscEst().setText(fornecedor.getInscricaoEstadual());
                
                this.telaCadastroFornecedor.getjFormattedTextFieldTel1().setText(fornecedor.getTelefone1());
                this.telaCadastroFornecedor.getjFormattedTextFieldtel2().setText(fornecedor.getTelefone2());
                this.telaCadastroFornecedor.getjTextFieldEmail().setText(fornecedor.getEmail());
                

                this.telaCadastroFornecedor.getjTextAreaObs().setText(fornecedor.getObservaca());
                this.telaCadastroFornecedor.getjComboBoxStatus().setSelectedItem(fornecedor.getStatus());
                this.telaCadastroFornecedor.getjComboBoxEndereco().setSelectedItem(fornecedor.getEndereco());
                //Endereco endereco = new Endereco();

                
                
                this.telaCadastroFornecedor.getjTextFieldId().setEnabled(false);
            }
        }
        
        if(e.getSource() == this.telaCadastroFornecedor.getjButtonSair()){
            this.telaCadastroFornecedor.dispose();
        }

    }
    
    public void Ativa(boolean estadoBotoes){
        this.telaCadastroFornecedor.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroFornecedor.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroFornecedor.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroFornecedor.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroFornecedor.getjButtonSair().setEnabled(estadoBotoes);        
    }
    
    public void LimpaEstadoComponentes(boolean estadoCompo){
        Component[] componentes = this.telaCadastroFornecedor.getjPanelDados().getComponents(); //verificar
        
        LimpaEstadoDeComponentes.limpa(estadoCompo, componentes);
    }
    
}

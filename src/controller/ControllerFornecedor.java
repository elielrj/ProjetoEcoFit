package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.bo.Bairro;
import model.bo.Fornecedor;
import model.bo.Endereco;

import view.busca.TelaBuscaFornecedor;
import view.cadastro.TelaCadastroFornecedor;

public class ControllerFornecedor implements ActionListener {

    TelaCadastroFornecedor telaCadastroFornecedor = new TelaCadastroFornecedor();
    public static int codigo;

    public ControllerFornecedor(TelaCadastroFornecedor telaCadastroFornecedor) {

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
        if (e.getSource() == this.telaCadastroFornecedor.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);
            this.telaCadastroFornecedor.getjTextFieldId().setEnabled(false);
            this.telaCadastroFornecedor.getjTextField_EnderecoID().setEnabled(false);
            codigo = 0;
        } else if (e.getSource() == this.telaCadastroFornecedor.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);
        } else if (e.getSource() == this.telaCadastroFornecedor.getjButtonGravar()) {
            //2º Fornecedor c/ endereço já existente     
            Fornecedor fornecedor = new Fornecedor.FornecedorBuilder()
                    .setRazaoSocial(this.telaCadastroFornecedor.getjTextFieldRazaoSocial().getText())//2
                    .setInscricaoEstadual(this.telaCadastroFornecedor.getjFormattedTextFieldInscEst().getText())//3
                    .setCnpj(this.telaCadastroFornecedor.getjFormattedTextFieldCnpj().getText())//4
                    .setEndereco(
                            new Endereco.EnderecoBuilder()
                                    //5-1 ID 
                                    .setLogradouro(this.telaCadastroFornecedor.getjTextField_EnderecoLogradouro().getText())//5-2
                                    .setNumero(this.telaCadastroFornecedor.getjTextField_EnderecoNumero().getText())//5-3
                                    .setBairro((Bairro) this.telaCadastroFornecedor.getjComboBox_EnderecoBairro().getSelectedItem())//5-4
                                    .setCep(this.telaCadastroFornecedor.getjFormattedTextField_EnderecoCEP().getText())//5-5
                                    .setStatus(this.telaCadastroFornecedor.getjComboBoxStatus().getSelectedItem().equals("Sim"))//5-6
                                    .createEndereco()
                    )//5
                    .setTelefone1(this.telaCadastroFornecedor.getjFormattedTextFieldTel1().getText())//6
                    .setTelefone2(this.telaCadastroFornecedor.getjFormattedTextFieldtel2().getText())//7
                    .setEmail(this.telaCadastroFornecedor.getjTextFieldEmail().getText())//8
                    .setObservacao(this.telaCadastroFornecedor.getjTextAreaObs().getText())//9
                    .setStatus(this.telaCadastroFornecedor.getjComboBoxStatus().getSelectedItem().equals("Sim"))//10
                    .setComplemento(this.telaCadastroFornecedor.getjTextField_EnderecoComplemento().getText())//11
                    .createFornecedor();

            if (codigo == 0) {
                service.ServiceFornecedor.Incluir(fornecedor);
            } else {
                
                fornecedor.setId(Integer.parseInt(this.telaCadastroFornecedor.getjTextFieldId().getText())); //1
                fornecedor.getEndereco().setId(Integer.parseInt(this.telaCadastroFornecedor.getjTextField_EnderecoID().getText())); //2
                service.ServiceFornecedor.Atualizar(fornecedor);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        if (e.getSource() == this.telaCadastroFornecedor.getjButtonBuscar()) {

            codigo = 0;
            TelaBuscaFornecedor telaBuscaFornecedor = new TelaBuscaFornecedor(null, true);
            ControllerFornecedorBusca controllerBuscaFornecedor = new ControllerFornecedorBusca(telaBuscaFornecedor);
            telaBuscaFornecedor.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);
                Fornecedor fornecedor = service.ServiceFornecedor.Buscar(codigo);

                this.telaCadastroFornecedor.getjTextFieldId().setText(fornecedor.getId() + "");//1
                this.telaCadastroFornecedor.getjTextFieldRazaoSocial().setText(fornecedor.getRazaoSocial());//2
                this.telaCadastroFornecedor.getjFormattedTextFieldInscEst().setText(fornecedor.getInscricaoEstadual());//3
                this.telaCadastroFornecedor.getjFormattedTextFieldCnpj().setText(fornecedor.getCnpj());//4
                //ENDEREÇO - id, logra, nr,bairro, cid, cep
                this.telaCadastroFornecedor.getjTextField_EnderecoID().setText(fornecedor.getEndereco().getId() + "");//5-1
                this.telaCadastroFornecedor.getjTextField_EnderecoLogradouro().setText(fornecedor.getEndereco().getLogradouro());//5-2
                this.telaCadastroFornecedor.getjTextField_EnderecoNumero().setText(fornecedor.getEndereco().getNumero());//5-3
                this.telaCadastroFornecedor.getjComboBox_EnderecoBairro().setSelectedItem(fornecedor.getEndereco().getBairro());//5-4
                this.telaCadastroFornecedor.getjComboBox_EnderecoCidade().setSelectedItem(fornecedor.getEndereco().getBairro().getCidade());//5-?
                this.telaCadastroFornecedor.getjFormattedTextField_EnderecoCEP().setText(fornecedor.getEndereco().getCep());//5-5
                //5-6 status do CEP
                this.telaCadastroFornecedor.getjFormattedTextFieldTel1().setText(fornecedor.getTelefone1());//6
                this.telaCadastroFornecedor.getjFormattedTextFieldtel2().setText(fornecedor.getTelefone2());//7
                this.telaCadastroFornecedor.getjTextFieldEmail().setText(fornecedor.getEmail());//8
                this.telaCadastroFornecedor.getjTextAreaObs().setText(fornecedor.getObservacao());//9
                this.telaCadastroFornecedor.getjComboBoxStatus().setSelectedItem(fornecedor.getStatus());//10
                this.telaCadastroFornecedor.getjTextField_EnderecoComplemento().setText(fornecedor.getComplemento());//11

                this.telaCadastroFornecedor.getjTextFieldId().setEnabled(false);
                this.telaCadastroFornecedor.getjTextField_EnderecoID().setEnabled(false);
            }
        }

        if (e.getSource() == this.telaCadastroFornecedor.getjButtonSair()) {
            this.telaCadastroFornecedor.dispose();
        }

    }

    public void Ativa(boolean estadoBotoes) {
        this.telaCadastroFornecedor.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroFornecedor.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroFornecedor.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroFornecedor.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroFornecedor.getjButtonSair().setEnabled(estadoBotoes);
        this.telaCadastroFornecedor.getjTextAreaObs().setEnabled(!estadoBotoes);
    }

    public void LimpaEstadoComponentes(boolean estadoCompo) {
        Component[] componentes = this.telaCadastroFornecedor.getjPanelDados().getComponents(); //verificar

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

}

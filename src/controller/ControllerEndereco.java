package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.bo.Bairro;
import model.bo.Cidade;
import model.bo.Endereco;
import view.TelaBuscaEndereco;

import view.TelaCadastroEndereco;

public class ControllerEndereco implements ActionListener {

    TelaCadastroEndereco telaCadastroEndereco;
    public static int codigo;

    public ControllerEndereco(TelaCadastroEndereco telaCadastroEndereco) {

        this.telaCadastroEndereco = telaCadastroEndereco;

        this.telaCadastroEndereco.getjButtonNovo().addActionListener(this);
        this.telaCadastroEndereco.getjButtonBuscar().addActionListener(this);
        this.telaCadastroEndereco.getjButtonCancelar().addActionListener(this);
        this.telaCadastroEndereco.getjButtonGravar().addActionListener(this);
        this.telaCadastroEndereco.getjButtonSair().addActionListener(this);

        Ativa(true);
        LimpaEstadoComponentes(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaCadastroEndereco.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);
            this.telaCadastroEndereco.getjTextFieldId().setEnabled(false);
            codigo = 0;
        } else if (e.getSource() == this.telaCadastroEndereco.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);
        } else if (e.getSource() == this.telaCadastroEndereco.getjButtonGravar()) {
            //montar objeto a persistir
            Endereco endereco = new Endereco.EnderecoBuilder()
                    .setCep(this.telaCadastroEndereco.getjFormattedTextFieldCep().getText()) //5
                    .setLogradouro(this.telaCadastroEndereco.getjTextFieldLogradouro().getText() )//2
                    .setNumero(this.telaCadastroEndereco.getjTextFieldNumero().getText()) //3
                    .setBairro((Bairro) this.telaCadastroEndereco.getjComboBoxBairro().getSelectedItem()) //4
                    .setStatus(this.telaCadastroEndereco.getjComboBoxStatus().getSelectedItem().equals("Sim")) //6
                    .createEndereco();

            if (codigo == 0) {
                service.ServiceEndereco.Incluir(endereco);
            } else {

                endereco.setId(Integer.parseInt(this.telaCadastroEndereco.getjTextFieldId().getText()));
                service.ServiceEndereco.Atualizar(endereco);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        if (e.getSource() == this.telaCadastroEndereco.getjButtonBuscar()) {

            codigo = 0;

            TelaBuscaEndereco telaBuscaEndereco = new TelaBuscaEndereco(null, true);
            ControllerEnderecoBusca controllerBuscaEndereco = new ControllerEnderecoBusca(telaBuscaEndereco);
            telaBuscaEndereco.setVisible(true);//verificarControllerBuscaEndereco

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);

                Endereco endereco = new Endereco.EnderecoBuilder().createEndereco();
                endereco = service.ServiceEndereco.Buscar(codigo);

                this.telaCadastroEndereco.getjTextFieldId().setText(endereco.getId() + "");  //1
                this.telaCadastroEndereco.getjTextFieldLogradouro().setText(endereco.getLogradouro());  //2
                this.telaCadastroEndereco.getjTextFieldNumero().setText(endereco.getNumero());             //3   
                this.telaCadastroEndereco.getjComboBoxBairro().setSelectedItem(endereco.getBairro()); //4
                this.telaCadastroEndereco.getjComboBoxCidade().setSelectedItem(endereco.getBairro().getCidade()); //5
                this.telaCadastroEndereco.getjFormattedTextFieldCep().setText(endereco.getCep()); //6
                this.telaCadastroEndereco.getjComboBoxStatus().setSelectedItem(endereco.getStatus()); //7
                
                this.telaCadastroEndereco.getjTextFieldId().setEnabled(false);
            }
        }

        if (e.getSource() == this.telaCadastroEndereco.getjButtonSair()) {
            this.telaCadastroEndereco.dispose();
        }

    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void Ativa(boolean estadoBotoes) {
        this.telaCadastroEndereco.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroEndereco.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroEndereco.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroEndereco.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroEndereco.getjButtonSair().setEnabled(estadoBotoes);
    }

    public void LimpaEstadoComponentes(boolean estadoCompo) {
        Component[] componentes = this.telaCadastroEndereco.getjPanelDados().getComponents(); //verificar!
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

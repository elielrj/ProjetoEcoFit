package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.swing.table.DefaultTableModel;
import model.bo.Bairro;
import model.bo.Cidade;
import model.bo.Endereco;
import view.TelaBuscaEndereco;
//import view.TelaCadastroBairro;
import view.TelaCadastroEndereco;

public class ControllerEndereco implements ActionListener {

    TelaCadastroEndereco telaCadastroEndereco = new TelaCadastroEndereco();
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
            Endereco endereco = new Endereco();
            endereco.setCep((String) this.telaCadastroEndereco.getjFormattedTextFieldCep().getText());
            endereco.setLogradouro(this.telaCadastroEndereco.getjTextFieldLogradouro().getText());
            endereco.setNumero(this.telaCadastroEndereco.getjTextFieldNumero().getText());
            endereco.setComplemento(this.telaCadastroEndereco.getjTextFieldComplemento().getText());

            endereco.setBairro((Bairro) this.telaCadastroEndereco.getjComboBoxBairro().getSelectedItem());
            endereco.setCidade((Cidade) this.telaCadastroEndereco.getjComboBoxCidade().getSelectedItem());
            endereco.setStatus(this.telaCadastroEndereco.getjComboBoxStatus().getSelectedItem().equals("Sim"));

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
            ControllerBuscaEndereco controllerBuscaEndereco = new ControllerBuscaEndereco(telaBuscaEndereco);
            telaBuscaEndereco.setVisible(true);//verificarControllerBuscaEndereco

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);

                Endereco endereco = new Endereco();
                endereco = service.ServiceEndereco.Buscar(codigo);

                this.telaCadastroEndereco.getjTextFieldId().setText(endereco.getId() + "");

                this.telaCadastroEndereco.getjTextFieldLogradouro().setText(endereco.getLogradouro());
                this.telaCadastroEndereco.getjTextFieldNumero().setText(endereco.getNumero());
                this.telaCadastroEndereco.getjTextFieldComplemento().setText(endereco.getComplemento());
                this.telaCadastroEndereco.getjComboBoxBairro().setSelectedItem(endereco.getBairro());
                this.telaCadastroEndereco.getjComboBoxCidade().setSelectedItem(endereco.getCidade());
                this.telaCadastroEndereco.getjFormattedTextFieldCep().setText(endereco.getCep());
                this.telaCadastroEndereco.getjComboBoxStatus().setSelectedItem(endereco.getStatus());

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

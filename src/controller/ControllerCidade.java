package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.bo.Cidade;
import view.TelaBuscaCidade;
import view.TelaCadastroCidade;

public class ControllerCidade implements ActionListener {

    TelaCadastroCidade telaCadastroCidade = new TelaCadastroCidade();
    public static int codigo;

    public ControllerCidade(TelaCadastroCidade telaCadastroCidade) {
        this.telaCadastroCidade = telaCadastroCidade;

        this.telaCadastroCidade.getjButtonNovo().addActionListener(this);
        this.telaCadastroCidade.getjButtonBuscar().addActionListener(this);
        this.telaCadastroCidade.getjButtonCancelar().addActionListener(this);
        this.telaCadastroCidade.getjButtonGravar().addActionListener(this);
        this.telaCadastroCidade.getjButtonSair().addActionListener(this);

        Ativa(true);
        LimpaEstadoComponentes(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaCadastroCidade.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);
            this.telaCadastroCidade.getjTextFieldId().setEnabled(false);
            codigo = 0;
        } else if (e.getSource() == this.telaCadastroCidade.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);
        } else if (e.getSource() == this.telaCadastroCidade.getjButtonGravar()) {
            //montar objeto a persistir
            Cidade cidade = new Cidade();

            cidade.setNome(this.telaCadastroCidade.getjTextFieldDescricao().getText());

            cidade.setStatus(this.telaCadastroCidade.getjComboBoxStatus().getSelectedItem().equals("Sim"));

            if (codigo == 0) {
                service.ServiceCidade.Incluir(cidade);
            } else {
                cidade.setId(Integer.parseInt(this.telaCadastroCidade.getjTextFieldId().getText()));
                service.ServiceCidade.Atualizar(cidade);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        if (e.getSource() == this.telaCadastroCidade.getjButtonBuscar()) {

            codigo = 0;

            TelaBuscaCidade telaBuscaCidade = new TelaBuscaCidade(null, true);
            ControllerBuscaCidade controllerBuscaCidade = new ControllerBuscaCidade(telaBuscaCidade);
            telaBuscaCidade.setVisible(true);//verificarControllerBuscaCidade

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);

                Cidade cidade = new Cidade();
                cidade = service.ServiceCidade.Buscar(codigo);

                this.telaCadastroCidade.getjTextFieldId().setText(cidade.getId() + "");
                this.telaCadastroCidade.getjTextFieldDescricao().setText(cidade.getNome());

                this.telaCadastroCidade.getjComboBoxStatus().setSelectedItem(cidade.getStatus());

                this.telaCadastroCidade.getjTextFieldId().setEnabled(false);
            }
        }

        if (e.getSource() == this.telaCadastroCidade.getjButtonSair()) {
            this.telaCadastroCidade.dispose();
        }

    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void Ativa(boolean estadoBotoes) {
        this.telaCadastroCidade.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroCidade.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroCidade.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroCidade.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroCidade.getjButtonSair().setEnabled(estadoBotoes);
    }

    public void LimpaEstadoComponentes(boolean estadoCompo) {
        Component[] componentes = this.telaCadastroCidade.getjPanelDados().getComponents(); //verificar!
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

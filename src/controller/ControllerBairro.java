package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import model.bo.Bairro;
import model.bo.Cidade;
import view.TelaBuscaBairro;
import view.TelaCadastroBairro;

public class ControllerBairro implements ActionListener {

    protected TelaCadastroBairro telaBuscaBairro;
    protected static int codigo;

    public ControllerBairro(TelaCadastroBairro telaBuscaBairro) {

        this.telaBuscaBairro = telaBuscaBairro;

        this.telaBuscaBairro.getjButtonNovo().addActionListener(this);
        this.telaBuscaBairro.getjButtonBuscar().addActionListener(this);
        this.telaBuscaBairro.getjButtonCancelar().addActionListener(this);
        this.telaBuscaBairro.getjButtonGravar().addActionListener(this);
        this.telaBuscaBairro.getjButtonSair().addActionListener(this);

        Ativa(true);
        LimpaEstadoComponentes(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaBairro.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);

            this.telaBuscaBairro.getjTextFieldId().setEnabled(false);
            codigo = 0;
        } else if (e.getSource() == this.telaBuscaBairro.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);

        } else if (e.getSource() == this.telaBuscaBairro.getjButtonGravar()) {
            
            Bairro bairro = new Bairro.BairroBuilder()
            .setNome(this.telaBuscaBairro.getjTextFieldDescricao().getText())
            .setStatus(this.telaBuscaBairro.getjComboBoxStatus().getSelectedItem().equals("Sim"))
            .setCidade((Cidade) this.telaBuscaBairro.getjComboBox_cidade().getSelectedItem())
            .createBairro();            

            if (codigo == 0) {
                service.ServiceBairro.Incluir(bairro);
            } else {
                bairro.setId(Integer.parseInt(this.telaBuscaBairro.getjTextFieldId().getText()));
                service.ServiceBairro.Atualizar(bairro);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);

        }
        if (e.getSource() == this.telaBuscaBairro.getjButtonBuscar()) {

            codigo = 0;
            TelaBuscaBairro telaBuscaBairro = new TelaBuscaBairro(null, true);
            ControllerBairroBusca controllerBuscaBairro = new ControllerBairroBusca(telaBuscaBairro);
            telaBuscaBairro.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);
                Bairro bairro = new Bairro.BairroBuilder().createBairro();
                bairro = service.ServiceBairro.Buscar(codigo);

                this.telaBuscaBairro.getjTextFieldId().setText(bairro.getId() + "");
                this.telaBuscaBairro.getjTextFieldDescricao().setText(bairro.getNome());
                this.telaBuscaBairro.getjComboBox_cidade().setSelectedItem(bairro.getCidade());
                this.telaBuscaBairro.getjComboBoxStatus().setSelectedItem(bairro.getStatus());

                this.telaBuscaBairro.getjTextFieldId().setEnabled(false);
            }
        }

        if (e.getSource() == this.telaBuscaBairro.getjButtonSair()) {
            this.telaBuscaBairro.dispose();
        }

    }

    public void Ativa(boolean estadoBotoes) {
        this.telaBuscaBairro.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaBuscaBairro.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaBuscaBairro.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaBuscaBairro.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaBuscaBairro.getjButtonSair().setEnabled(estadoBotoes);
    }

    public void LimpaEstadoComponentes(boolean estadoCompo) {
        Component[] componentes = this.telaBuscaBairro.getjPanelDados().getComponents(); //verificar
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

        }
    }

}

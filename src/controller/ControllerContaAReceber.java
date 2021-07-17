package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import model.bo.Bairro;
import model.bo.Cidade;
import view.busca.TelaBuscaBairro;
import view.cadastro.TelaCadastroContaAReceber;


public class ControllerContaAReceber implements ActionListener {

    protected TelaCadastroContaAReceber telaCadastroContaAReceber;
    protected static int codigo;

    public ControllerContaAReceber(TelaCadastroContaAReceber telaCadastroContaAReceber) {

        this.telaCadastroContaAReceber = telaCadastroContaAReceber;

        this.telaCadastroContaAReceber.getjButtonNovo().addActionListener(this);
        this.telaCadastroContaAReceber.getjButtonBuscar().addActionListener(this);
        this.telaCadastroContaAReceber.getjButtonCancelar().addActionListener(this);
        this.telaCadastroContaAReceber.getjButtonGravar().addActionListener(this);
        this.telaCadastroContaAReceber.getjButtonSair().addActionListener(this);

        Ativa(true);
        LimpaEstadoComponentes(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaCadastroContaAReceber.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);

            this.telaCadastroContaAReceber.getjTextFieldId().setEnabled(false);
            codigo = 0;
        } else if (e.getSource() == this.telaCadastroContaAReceber.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);

        } else if (e.getSource() == this.telaCadastroContaAReceber.getjButtonGravar()) {
            
            Bairro bairro = new Bairro.BairroBuilder()
            .setNome(this.telaCadastroContaAReceber.getjTextFieldDescricao().getText())
            .setStatus(this.telaCadastroContaAReceber.getjComboBoxStatus().getSelectedItem().equals("Sim"))
            .setCidade((Cidade) this.telaCadastroContaAReceber.getjComboBox_cidade().getSelectedItem())
            .createBairro();            

            if (codigo == 0) {
                service.ServiceBairro.Incluir(bairro);
            } else {
                bairro.setId(Integer.parseInt(this.telaCadastroContaAReceber.getjTextFieldId().getText()));
                service.ServiceBairro.Atualizar(bairro);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);

        }
        if (e.getSource() == this.telaCadastroContaAReceber.getjButtonBuscar()) {

            codigo = 0;
            TelaBuscaBairro telaCadastroContaAReceber = new TelaBuscaBairro(null, true);
            ControllerBairroBusca controllerBuscaBairro = new ControllerBairroBusca(telaCadastroContaAReceber);
            telaCadastroContaAReceber.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);
                Bairro bairro = new Bairro.BairroBuilder().createBairro();
                bairro = service.ServiceBairro.Buscar(codigo);

                this.telaCadastroContaAReceber.getjTextFieldId().setText(bairro.getId() + "");
                this.telaCadastroContaAReceber.getjTextFieldDescricao().setText(bairro.getNome());
                this.telaCadastroContaAReceber.getjComboBox_cidade().setSelectedItem(bairro.getCidade());
                this.telaCadastroContaAReceber.getjComboBoxStatus().setSelectedItem(bairro.getStatus());

                this.telaCadastroContaAReceber.getjTextFieldId().setEnabled(false);
            }
        }

        if (e.getSource() == this.telaCadastroContaAReceber.getjButtonSair()) {
            this.telaCadastroContaAReceber.dispose();
        }

    }

    public void Ativa(boolean estadoBotoes) {
        this.telaCadastroContaAReceber.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroContaAReceber.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroContaAReceber.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroContaAReceber.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroContaAReceber.getjButtonSair().setEnabled(estadoBotoes);
    }

    public void LimpaEstadoComponentes(boolean estadoCompo) {
        Component[] componentes = this.telaCadastroContaAReceber.getjPanelDados().getComponents(); //verificar
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

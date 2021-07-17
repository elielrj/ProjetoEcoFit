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
import view.cadastro.TelaCadastroContaAPagar; 


public class ControllerContaAPagar implements ActionListener {

    protected TelaCadastroContaAPagar telaCadastroContaAPagar;
    protected static int codigo;

    public ControllerContaAPagar(TelaCadastroContaAPagar telaCadastroContaAPagar) {

        this.telaCadastroContaAPagar = telaCadastroContaAPagar;

        this.telaCadastroContaAPagar.getjButtonNovo().addActionListener(this);
        this.telaCadastroContaAPagar.getjButtonBuscar().addActionListener(this);
        this.telaCadastroContaAPagar.getjButtonCancelar().addActionListener(this);
        this.telaCadastroContaAPagar.getjButtonGravar().addActionListener(this);
        this.telaCadastroContaAPagar.getjButtonSair().addActionListener(this);

        Ativa(true);
        LimpaEstadoComponentes(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaCadastroContaAPagar.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);

            this.telaCadastroContaAPagar.getjTextFieldId().setEnabled(false);
            codigo = 0;
        } else if (e.getSource() == this.telaCadastroContaAPagar.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);

        } else if (e.getSource() == this.telaCadastroContaAPagar.getjButtonGravar()) {
            
            Bairro bairro = new Bairro.BairroBuilder()
            .setNome(this.telaCadastroContaAPagar.getjTextFieldDescricao().getText())
            .setStatus(this.telaCadastroContaAPagar.getjComboBoxStatus().getSelectedItem().equals("Sim"))
            .setCidade((Cidade) this.telaCadastroContaAPagar.getjComboBox_cidade().getSelectedItem())
            .createBairro();            

            if (codigo == 0) {
                service.ServiceBairro.Incluir(bairro);
            } else {
                bairro.setId(Integer.parseInt(this.telaCadastroContaAPagar.getjTextFieldId().getText()));
                service.ServiceBairro.Atualizar(bairro);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);

        }
        if (e.getSource() == this.telaCadastroContaAPagar.getjButtonBuscar()) {

            codigo = 0;
            TelaBuscaBairro telaCadastroContaAPagar = new TelaBuscaBairro(null, true);
            ControllerBairroBusca controllerBuscaBairro = new ControllerBairroBusca(telaCadastroContaAPagar);
            telaCadastroContaAPagar.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);
                Bairro bairro = new Bairro.BairroBuilder().createBairro();
                bairro = service.ServiceBairro.Buscar(codigo);

                this.telaCadastroContaAPagar.getjTextFieldId().setText(bairro.getId() + "");
                this.telaCadastroContaAPagar.getjTextFieldDescricao().setText(bairro.getNome());
                this.telaCadastroContaAPagar.getjComboBox_cidade().setSelectedItem(bairro.getCidade());
                this.telaCadastroContaAPagar.getjComboBoxStatus().setSelectedItem(bairro.getStatus());

                this.telaCadastroContaAPagar.getjTextFieldId().setEnabled(false);
            }
        }

        if (e.getSource() == this.telaCadastroContaAPagar.getjButtonSair()) {
            this.telaCadastroContaAPagar.dispose();
        }

    }

    public void Ativa(boolean estadoBotoes) {
        this.telaCadastroContaAPagar.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroContaAPagar.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroContaAPagar.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroContaAPagar.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroContaAPagar.getjButtonSair().setEnabled(estadoBotoes);
    }

    public void LimpaEstadoComponentes(boolean estadoCompo) {
        Component[] componentes = this.telaCadastroContaAPagar.getjPanelDados().getComponents(); //verificar
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

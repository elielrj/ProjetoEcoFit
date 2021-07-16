package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.bo.Bairro;
import model.bo.PessoaFisica;
import model.bo.Endereco;

import view.busca.TelaBuscaPessoaFisica;
import view.cadastro.TelaCadastroPessoaFisica;

public class ControllerPessoaFisica implements ActionListener {

    TelaCadastroPessoaFisica telaCadastroPessoaFisica = new TelaCadastroPessoaFisica();
    public static int codigo;

    public ControllerPessoaFisica(TelaCadastroPessoaFisica telaCadastroAluno) {

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
        if (e.getSource() == this.telaCadastroPessoaFisica.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);
            this.telaCadastroPessoaFisica.getjTextFieldId().setEnabled(false);
            codigo = 0;
        } else if (e.getSource() == this.telaCadastroPessoaFisica.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);
        } else if (e.getSource() == this.telaCadastroPessoaFisica.getjButtonGravar()) {

            PessoaFisica pessoaFisica = new PessoaFisica.PessoaFisicaBuilder()
                    .setNome(this.telaCadastroPessoaFisica.getjTextFieldNome().getText())
                    .setRg(this.telaCadastroPessoaFisica.getjFormattedTextFieldRg().getText())
                    .setCpf(this.telaCadastroPessoaFisica.getjFormattedTextFieldCpf().getText())
                    .setDataDeNascimento(this.telaCadastroPessoaFisica.getjFormattedTextFieldDataNascimento().getText())
                    .setEndereco(
                            new Endereco.EnderecoBuilder()
                                    .setLogradouro(this.telaCadastroPessoaFisica.getjTextField_EnderecoLogradouro().getText())
                                    .setNumero(this.telaCadastroPessoaFisica.getjTextField_EnderecoNr().getText())
                                    .setBairro((Bairro) this.telaCadastroPessoaFisica.getjComboBox_EnderecoBairro().getSelectedItem())
                                    .setCep(this.telaCadastroPessoaFisica.getjFormattedTextField_EnderecoCEP().getText())
                                    .setStatus(this.telaCadastroPessoaFisica.getjComboBoxStatus().getSelectedItem().equals("Sim"))
                                    .createEndereco()
                    )
                    .setTipo((String) this.telaCadastroPessoaFisica.getjComboBoxTipo().getSelectedItem())
                    .setTelefone1(this.telaCadastroPessoaFisica.getjFormattedTextFieldTel1().getText())
                    .setTelefone2(this.telaCadastroPessoaFisica.getjFormattedTextFieldTel2().getText())
                    .setEmail(this.telaCadastroPessoaFisica.getjTextFieldEmail().getText())
                    .setObservacao(this.telaCadastroPessoaFisica.getjTextAreaObservacao().getText())
                    .setStatus(this.telaCadastroPessoaFisica.getjComboBoxStatus().getSelectedItem().equals("Sim"))
                    .setComplemento(this.telaCadastroPessoaFisica.getjTextField_Complemento().getText())
                    .createPessoaFisica();

            if (codigo == 0) {
                service.ServicePessoaFisica.Incluir(pessoaFisica);
            } else {
                pessoaFisica.setId(Integer.parseInt(this.telaCadastroPessoaFisica.getjTextFieldId().getText()));
                service.ServicePessoaFisica.Atualizar(pessoaFisica);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        if (e.getSource() == this.telaCadastroPessoaFisica.getjButtonBuscar()) {

            codigo = 0;
            TelaBuscaPessoaFisica telaBuscaPessoaFisica = new TelaBuscaPessoaFisica(null, true);
            ControllerPessoaFisicaBusca controllerBuscaPessoaFisica = new ControllerPessoaFisicaBusca(telaBuscaPessoaFisica);
            telaBuscaPessoaFisica.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);

                PessoaFisica pessoaFisica = service.ServicePessoaFisica.Buscar(codigo);

                this.telaCadastroPessoaFisica.getjTextFieldId().setText(pessoaFisica.getId() + "");//1
                this.telaCadastroPessoaFisica.getjTextFieldNome().setText(pessoaFisica.getNome());//2
                this.telaCadastroPessoaFisica.getjFormattedTextFieldRg().setText(pessoaFisica.getRg());//2
                this.telaCadastroPessoaFisica.getjFormattedTextFieldCpf().setText(pessoaFisica.getCpf());//4
                this.telaCadastroPessoaFisica.getjFormattedTextFieldDataNascimento().setText(pessoaFisica.getDataDeNascimento());//5
                //ENDEREÇO -> ID, LOGRADOURO, NUMERO, BAIRRO, CEP, STATUS                
                this.telaCadastroPessoaFisica.getjTextField_EnderecoId().setText(pessoaFisica.getEndereco().getId() + "");//6-1
                this.telaCadastroPessoaFisica.getjTextField_EnderecoLogradouro().setText(pessoaFisica.getEndereco().getLogradouro() + "");//6-2
                this.telaCadastroPessoaFisica.getjTextField_EnderecoNr().setText(pessoaFisica.getEndereco().getNumero() + "");//6-3
                this.telaCadastroPessoaFisica.getjComboBox_EnderecoBairro().setSelectedItem(pessoaFisica.getEndereco().getBairro().getNome());//6-4
                this.telaCadastroPessoaFisica.getjFormattedTextField_EnderecoCEP().setText(pessoaFisica.getEndereco().getCep());//6-5
                //Status do endereço - 6-6

                this.telaCadastroPessoaFisica.getjComboBoxTipo().setSelectedItem(pessoaFisica.getTipo());//7
                this.telaCadastroPessoaFisica.getjFormattedTextFieldTel1().setText(pessoaFisica.getTelefone1());//8
                this.telaCadastroPessoaFisica.getjFormattedTextFieldTel2().setText(pessoaFisica.getTelefone2());//9
                this.telaCadastroPessoaFisica.getjTextFieldEmail().setText(pessoaFisica.getEmail());//10
                this.telaCadastroPessoaFisica.getjTextAreaObservacao().setText(pessoaFisica.getObservacao());//1
                this.telaCadastroPessoaFisica.getjComboBoxStatus().setSelectedItem(pessoaFisica.getStatus());//12
                this.telaCadastroPessoaFisica.getjTextField_Complemento().setText(pessoaFisica.getComplemento());//13

                this.telaCadastroPessoaFisica.getjTextFieldId().setEnabled(false);
            }
        }

        if (e.getSource() == this.telaCadastroPessoaFisica.getjButtonSair()) {
            this.telaCadastroPessoaFisica.dispose();
        }

    }

    public void Ativa(boolean estadoBotoes) {
        this.telaCadastroPessoaFisica.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroPessoaFisica.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroPessoaFisica.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroPessoaFisica.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroPessoaFisica.getjButtonSair().setEnabled(estadoBotoes);
        this.telaCadastroPessoaFisica.getjTextAreaObservacao().setEnabled(!estadoBotoes);

        this.telaCadastroPessoaFisica.getjComboBox_EnderecoBairro().setEnabled(!estadoBotoes);
        this.telaCadastroPessoaFisica.getjComboBox_EnderecoCidade().setEnabled(!estadoBotoes);
        this.telaCadastroPessoaFisica.getjTextField_EnderecoId().setEnabled(false);
        this.telaCadastroPessoaFisica.getjTextField_Complemento().setEnabled(!estadoBotoes);
        this.telaCadastroPessoaFisica.getjFormattedTextField_EnderecoCEP().setEnabled(!estadoBotoes);
        this.telaCadastroPessoaFisica.getjTextField_EnderecoLogradouro().setEnabled(!estadoBotoes);
    }

    public void LimpaEstadoComponentes(boolean estadoCompo) {

        Component[] componentes = this.telaCadastroPessoaFisica.getjPanelDados().getComponents(); //verificar

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

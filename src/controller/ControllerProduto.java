package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.bo.Produto;
import view.TelaBuscaProduto;
import view.TelaCadastroProduto;

public class ControllerProduto implements ActionListener {

    TelaCadastroProduto telaCadastroProduto = new TelaCadastroProduto();
    public static int codigo;

    public ControllerProduto(TelaCadastroProduto telaCadastroProduto) {

        this.telaCadastroProduto = telaCadastroProduto;

        this.telaCadastroProduto.getjButtonNovo().addActionListener(this);
        this.telaCadastroProduto.getjButtonBuscar().addActionListener(this);
        this.telaCadastroProduto.getjButtonCancelar().addActionListener(this);
        this.telaCadastroProduto.getjButtonGravar().addActionListener(this);
        this.telaCadastroProduto.getjButtonSair().addActionListener(this);

        Ativa(true);
        LimpaEstadoComponentes(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaCadastroProduto.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);
            this.telaCadastroProduto.getjTextFieldId().setEnabled(false);
            codigo = 0;
        } else if (e.getSource() == this.telaCadastroProduto.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);
        } else if (e.getSource() == this.telaCadastroProduto.getjButtonGravar()) {
            //montar objeto a persistir
            Produto produto = new Produto();
            produto.setDescricao(this.telaCadastroProduto.getjTextFieldDescricao().getText());
            produto.setUnidadeDeCompra(this.telaCadastroProduto.getjTextFieldUnCompra().getText());
            produto.setUnidadeDeVenda((String) this.telaCadastroProduto.getjTextFieldUnVenda().getText());
            produto.setCorrelacaoUnidade((String) this.telaCadastroProduto.getjTextFieldCorrelacao().getText());
            produto.setValor(Float.parseFloat(this.telaCadastroProduto.getjTextFieldValorProduto().getText()));

            produto.setQuantidadeDeEstoque(Integer.parseInt(this.telaCadastroProduto.getjTextFieldQtdEstoque().getText()));
            produto.setCodigoDeBarras(this.telaCadastroProduto.getjTextFieldCodBarras().getText());
            produto.setStatus(this.telaCadastroProduto.getjComboBoxStatus().getSelectedItem().equals("Sim"));
            produto.setObservacao(this.telaCadastroProduto.getjTextAreaObs().getText());

            if (codigo == 0) {
                service.ServiceProduto.Incluir(produto);
            } else {
                produto.setId(Integer.parseInt(this.telaCadastroProduto.getjTextFieldId().getText()));
                service.ServiceProduto.Atualizar(produto);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        if (e.getSource() == this.telaCadastroProduto.getjButtonBuscar()) {

            codigo = 0;
            TelaBuscaProduto telaBuscaProduto = new TelaBuscaProduto(null, true);
            ControllerBuscaProduto controllerBuscaProduto = new ControllerBuscaProduto(telaBuscaProduto);
            telaBuscaProduto.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);
                Produto produto = new Produto();
                produto = service.ServiceProduto.Buscar(codigo);
                this.telaCadastroProduto.getjTextFieldId().setText(produto.getId() + "");
                this.telaCadastroProduto.getjTextFieldDescricao().setText(produto.getDescricao());
                this.telaCadastroProduto.getjTextFieldUnCompra().setText(produto.getUnidadeDeCompra());
                this.telaCadastroProduto.getjTextFieldUnVenda().setText(produto.getUnidadeDeVenda());
                this.telaCadastroProduto.getjTextFieldCorrelacao().setText(produto.getCorrelacaoUnidade());
                this.telaCadastroProduto.getjTextFieldValorProduto().setText(produto.getValor() + "");

                this.telaCadastroProduto.getjTextFieldQtdEstoque().setText(produto.getQuantidadeDeEstoque() + "");
                this.telaCadastroProduto.getjTextFieldCodBarras().setText(produto.getCodigoDeBarras());
                this.telaCadastroProduto.getjComboBoxStatus().setSelectedItem(produto.getStatus());
                this.telaCadastroProduto.getjTextAreaObs().setText(produto.getObservacao());

                this.telaCadastroProduto.getjTextFieldId().setEnabled(false);
            }
        }

        if (e.getSource() == this.telaCadastroProduto.getjButtonSair()) {
            this.telaCadastroProduto.dispose();
        }

    }

    public void Ativa(boolean estadoBotoes) {
        this.telaCadastroProduto.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaCadastroProduto.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaCadastroProduto.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaCadastroProduto.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaCadastroProduto.getjButtonSair().setEnabled(estadoBotoes);
    }

    public void LimpaEstadoComponentes(boolean estadoCompo) {
        Component[] componentes = this.telaCadastroProduto.getjPanelDados().getComponents(); //verificar
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

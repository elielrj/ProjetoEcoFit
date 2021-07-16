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
import model.bo.Estoque;
import model.bo.Produto;
import view.busca.TelaBuscaProduto;
import view.cadastro.TelaCadastroProduto;

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
            Produto produto = new Produto.ProdutoBuilder()
                .setDescricao(this.telaCadastroProduto.getjTextFieldDescricao().getText())//2
                .setUnidadeDeCompra(this.telaCadastroProduto.getjTextFieldUnCompra().getText())//3
                .setUnidadeDeVenda((String) this.telaCadastroProduto.getjTextFieldUnVenda().getText())//4
                .setCorrelacaoUnidade((String) this.telaCadastroProduto.getjTextFieldCorrelacao().getText())//5
                .setValor(Float.parseFloat(this.telaCadastroProduto.getjTextFieldValorProduto().getText()))//6
                .setCodigoDeBarras(this.telaCadastroProduto.getjTextFieldCodBarras().getText())//7
                .setStatus(this.telaCadastroProduto.getjComboBoxStatus().getSelectedItem().equals("Sim"))//8
                .setObservacao(this.telaCadastroProduto.getjTextAreaObs().getText())//9
                .createProduto();
            Estoque estoque = new Estoque.EstoqueBuilder()                    
                    .setQuantidade(Integer.parseInt(this.telaCadastroProduto.getjTextFieldQtdEstoque().getText()))
                    .createEstoque();
            
            
            if (codigo == 0) {
                service.ServiceProduto.Incluir(produto);
                
                produto = service.ServiceProduto.Buscar(produto.getCodigoDeBarras());
                estoque.setProdutoId(produto.getId());
                service.ServiceEstoque.Incluir(estoque);
                
                
            } else {
                produto.setId(Integer.parseInt(this.telaCadastroProduto.getjTextFieldId().getText()));
                service.ServiceProduto.Atualizar(produto);
                
                estoque.setProdutoId(produto.getId());
                
                estoque.setId(
                    service.ServiceEstoque.BuscarEstoquePorIdPeloProduto(produto.getId())
                );
                        
                service.ServiceEstoque.Atualizar(estoque);

            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        if (e.getSource() == this.telaCadastroProduto.getjButtonBuscar()) {

            codigo = 0;
            TelaBuscaProduto telaBuscaProduto = new TelaBuscaProduto(null, true);
            ControllerProdutoBusca controllerBuscaProduto = new ControllerProdutoBusca(telaBuscaProduto);
            telaBuscaProduto.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);
                Produto produto = service.ServiceProduto.Buscar(codigo);
                
                this.telaCadastroProduto.getjTextFieldId().setText(produto.getId() + "");//1
                this.telaCadastroProduto.getjTextFieldDescricao().setText(produto.getDescricao());//2
                this.telaCadastroProduto.getjTextFieldUnCompra().setText(produto.getUnidadeDeCompra());//3
                this.telaCadastroProduto.getjTextFieldUnVenda().setText(produto.getUnidadeDeVenda());//4
                this.telaCadastroProduto.getjTextFieldCorrelacao().setText(produto.getCorrelacaoUnidade());//5
                this.telaCadastroProduto.getjTextFieldValorProduto().setText(produto.getValor() + "");//6
                this.telaCadastroProduto.getjTextFieldCodBarras().setText(produto.getCodigoDeBarras());//7
                this.telaCadastroProduto.getjComboBoxStatus().setSelectedItem(produto.getStatus());//8
                this.telaCadastroProduto.getjTextAreaObs().setText(produto.getObservacao());//9

                this.telaCadastroProduto.getjTextFieldQtdEstoque().setText(
                        service.ServiceEstoque.BuscarEstoquePorIdPeloProduto(
                                produto.getId())+""
                );
                
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
        this.telaCadastroProduto.getjTextAreaObs().setEnabled(!estadoBotoes);
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

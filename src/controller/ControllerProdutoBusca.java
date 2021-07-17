package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.busca.TelaBuscaProduto;
import model.bo.Produto;

public class ControllerProdutoBusca implements ActionListener {

    TelaBuscaProduto telaBuscaProduto;
    int codigoProduto = 0;

    public ControllerProdutoBusca(TelaBuscaProduto telaBuscaProduto) {
        this.telaBuscaProduto = telaBuscaProduto;

        this.telaBuscaProduto.getjButtonCarregar().addActionListener(this);
        this.telaBuscaProduto.getjButtonSair().addActionListener(this);
        this.telaBuscaProduto.getjButton_Deletar().addActionListener(this);

        carregarDadosNaTabela();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaProduto.getjButtonSair()) 
            this.telaBuscaProduto.dispose();
        else if (e.getSource() == this.telaBuscaProduto.getjButtonCarregar()) {
            codigoProduto = (int) this.telaBuscaProduto.getjTable1().getValueAt(this.telaBuscaProduto.getjTable1().getSelectedRow(), 0);
            ControllerProduto.codigo = codigoProduto;
            this.telaBuscaProduto.setCodProduto(codigoProduto);
            this.telaBuscaProduto.dispose();        
        }else if(e.getSource() == this.telaBuscaProduto.getjButton_Deletar()) {
            try{
                service.ServiceProduto.Deletar(
                        (int) this.telaBuscaProduto.getjTable1().getValueAt(
                                this.telaBuscaProduto.getjTable1().getSelectedRow(),0
                        )
                );
                JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");
                carregarDadosNaTabela();
            } catch (Exception ex) {
                throw new RuntimeException(" \nCLASSE: ControllerBuscaProduto->actionPerformed(ActionEvent e)->deletar\nMENSAGEM:" 
                        + ex.getMessage() + "\nLOCALIZADO:" 
                        + ex.getLocalizedMessage()
                );
            }
        }
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    private void carregarDadosNaTabela() {
       
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaProduto.getjTable1().getModel();
        tabela.getDataVector().removeAllElements();

        for (Produto produtoDaLista : service.ServiceProduto.Buscar()) {
            tabela.addRow(new Object[]{
                produtoDaLista.getId(),
                produtoDaLista.getDescricao(),
                produtoDaLista.getUnidadeDeCompra(),
                produtoDaLista.getUnidadeDeVenda(),
                produtoDaLista.getCorrelacaoUnidade(),
                produtoDaLista.getValor(),
                service.ServiceEstoque.BuscarAQuantidadeNoEstoqueComOIdDoProduto(produtoDaLista.getId()),
                produtoDaLista.getCodigoDeBarras(),
                produtoDaLista.getObservacao(),
                produtoDaLista.getStatus()
            });
        }
    }

}

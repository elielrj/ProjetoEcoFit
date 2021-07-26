package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bo.ContaAReceber;
import model.bo.Estoque;
import model.bo.ItemDeVenda;
import view.busca.TelaBuscaVenda;
import model.bo.Venda;

public class ControllerVendaBusca implements ActionListener {

    TelaBuscaVenda telaBuscaVenda;
    int idVenda = 0;

    public ControllerVendaBusca(TelaBuscaVenda telaBuscaVenda) {

        this.telaBuscaVenda = telaBuscaVenda;

        this.telaBuscaVenda.getjButtonCarregar().addActionListener(this);
        this.telaBuscaVenda.getjButtonSair().addActionListener(this);
        this.telaBuscaVenda.getjButton_deletar().addActionListener(this);
        
        carregarDadosNaTabela();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaBuscaVenda.getjButtonSair()) {
            this.telaBuscaVenda.dispose();
        } else if (e.getSource() == this.telaBuscaVenda.getjButtonCarregar()) {

            idVenda = (int) this.telaBuscaVenda.getjTable_BuscaVendas().getValueAt(this.telaBuscaVenda.getjTable_BuscaVendas().getSelectedRow(), 0);
            ControllerVenda.codigo = idVenda;
            this.telaBuscaVenda.setIdVenda(idVenda);
            this.telaBuscaVenda.dispose();
        } else if (e.getSource() == this.telaBuscaVenda.getjButton_deletar()) {
            Venda venda
                    = service.ServiceVenda.Buscar(
                            (int) this.telaBuscaVenda.getjTable_BuscaVendas().getValueAt(
                                    this.telaBuscaVenda.getjTable_BuscaVendas().getSelectedRow(), 0)
                    );
            //try {

            //1º RECEBER
            //2º CONTAS A RECEBER
            ContaAReceber contaAReceber = service.ServiceContaAReceber.BuscarIdDaContaAReceberPeloIdDaVenda(venda.getId());
            service.ServiceContaAReceber.Deletar(contaAReceber);
            //3º ITEM DE VENDA
            List<ItemDeVenda> itensDeVenda = service.ServiceItemDeVenda.BuscarUmaListaDeItemDeVendaPeloIdDaVenda(venda.getId());
            for (ItemDeVenda item : itensDeVenda) {
                service.ServiceItemDeVenda.Deletar(item);
            }
            //4º ESTOQUE
            for (ItemDeVenda item : itensDeVenda) {
                Estoque estoque = service.ServiceEstoque.BuscarEstoquePorIdDoProduto(item.getProduto().getId());
                estoque.setQuantidade(estoque.getQuantidade() + item.getQuantidade());
                service.ServiceEstoque.Atualizar(estoque);
            }
            //5º VENDA
            service.ServiceVenda.Deletar(venda);
            JOptionPane.showMessageDialog(null, "Venda deletado com sucesso!");
            carregarDadosNaTabela();
            /*} catch (Exception ex) {
                throw new RuntimeException(" \nCLASSE: ControllerVendaBusca->actionPerformed(ActionEvent e)->deletar\nMENSAGEM:"
                        + ex.getMessage() + "\nLOCALIZADO:"
                        + ex.getLocalizedMessage()
                );
            }*/
        }

    }

    private void carregarDadosNaTabela() {

        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVenda.getjTable_BuscaVendas().getModel();
        tabela.getDataVector().removeAllElements();
        List<Venda> vendas = service.ServiceVenda.Buscar();
        for (Venda vendaDaLista : vendas) {
            if (vendas.size() > 0) {
                tabela.addRow(new Object[]{
                    vendaDaLista.getId(),//1
                    vendaDaLista.getData(),//2
                    vendaDaLista.getHora(),//3
                    vendaDaLista.getUserCaixa(),//4
                    vendaDaLista.getDataDeVencimento(),//5
                    vendaDaLista.getObservacao(),//6
                    vendaDaLista.getValorDoDesconto(),//7
                    vendaDaLista.getValorTotal(),//8
                    vendaDaLista.getStatus(),//9
                    vendaDaLista.getPessoaFisica().getNome(),//10
                    vendaDaLista.quantidadeDeItensNaLista()//10
                });
            } else {
                tabela.addRow(new Object[]{});
            }
        }
    }

    
}

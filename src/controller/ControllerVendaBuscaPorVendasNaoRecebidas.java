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

public class ControllerVendaBuscaPorVendasNaoRecebidas implements ActionListener {

    TelaBuscaVenda telaBuscaVenda;
    int idVenda = 0;

    public ControllerVendaBuscaPorVendasNaoRecebidas(TelaBuscaVenda telaBuscaVenda) {

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
        } 

    }

    private void carregarDadosNaTabela() {

        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVenda.getjTable_BuscaVendas().getModel();
        tabela.getDataVector().removeAllElements();
        List<Venda> vendas = service.ServiceVenda.Buscar();
        for (Venda vendaDaLista : vendas) {
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
        }
    }

    
}

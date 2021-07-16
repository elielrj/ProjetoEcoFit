package controller;

import controller.deletar.ControllerFaturamento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.busca.TelaBuscaVenda;
import model.bo.Venda;

public class ControllerVendaBusca implements ActionListener {

    TelaBuscaVenda telaBuscaVenda;

    public ControllerVendaBusca(TelaBuscaVenda telaBuscaVenda) {
        
        this.telaBuscaVenda = telaBuscaVenda;
        
        this.telaBuscaVenda.getjButtonCarregar().addActionListener(this);
        this.telaBuscaVenda.getjButtonSair().addActionListener(this);
        this.telaBuscaVenda.getjButton_deletar().addActionListener(this);

        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVenda.getjTable_BuscaVendas().getModel();

        for (Venda vendaDaLista : service.ServiceVenda.Buscar()) {
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

    @Override
    public void actionPerformed(ActionEvent e) { 
        if (e.getSource() == this.telaBuscaVenda.getjButtonSair()) 
            this.telaBuscaVenda.dispose();
        else if (e.getSource() == this.telaBuscaVenda.getjButtonCarregar()) {

            ControllerFaturamento.codigo = (int) this.telaBuscaVenda.getjTable_BuscaVendas().getValueAt(this.telaBuscaVenda.getjTable_BuscaVendas().getSelectedRow(), 0);
            this.telaBuscaVenda.dispose();
        }else if (e.getSource() == this.telaBuscaVenda.getjButton_deletar()){
            try{
                service.ServiceBairro.Deletar((int) this.telaBuscaVenda.getjTable_BuscaVendas().getValueAt(this.telaBuscaVenda.getjTable_BuscaVendas().getSelectedRow(),0));
                JOptionPane.showMessageDialog(null, "Bairro deletado com sucesso!");
                carregarDadosNaTabela();
            } catch (Exception ex) {
                throw new RuntimeException(" \nCLASSE: ControllerBuscaBairro->actionPerformed(ActionEvent e)->deletar\nMENSAGEM:" 
                        + ex.getMessage() + "\nLOCALIZADO:" 
                        + ex.getLocalizedMessage()
                );
            }
        }
       
    }
    
    private void carregarDadosNaTabela() {
        
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVenda.getjTable_BuscaVendas().getModel();
        tabela.getDataVector().removeAllElements();
        
        for (Venda vendaDaLista : service.ServiceVenda.Buscar()) {
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

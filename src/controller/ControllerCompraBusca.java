package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.busca.TelaBuscaCompra;
import model.bo.Venda;

public class ControllerCompraBusca implements ActionListener {

    TelaBuscaCompra telaBuscaCompra;

    public ControllerCompraBusca(TelaBuscaCompra telaBuscaCompra) {
        
        this.telaBuscaCompra = telaBuscaCompra;
        
        this.telaBuscaCompra.getjButtonCarregar().addActionListener(this);
        this.telaBuscaCompra.getjButtonSair().addActionListener(this);
        this.telaBuscaCompra.getjButton_deletar().addActionListener(this);

        
       carregarDadosNaTabela();

    }

    @Override
    public void actionPerformed(ActionEvent e) { 
        if (e.getSource() == this.telaBuscaCompra.getjButtonSair()) 
            this.telaBuscaCompra.dispose();
        else if (e.getSource() == this.telaBuscaCompra.getjButtonCarregar()) {

            ControllerVenda.codigo = (int) this.telaBuscaCompra.getjTable_BuscaVendas().getValueAt(this.telaBuscaCompra.getjTable_BuscaVendas().getSelectedRow(), 0);
            this.telaBuscaCompra.dispose();
        }else if (e.getSource() == this.telaBuscaCompra.getjButton_deletar()){
            try{
                service.ServiceVenda.Deletar((int) 
                        
                        this.telaBuscaCompra.getjTable_BuscaVendas().getValueAt(
                        this.telaBuscaCompra.getjTable_BuscaVendas().getSelectedRow(),0)
                );
                JOptionPane.showMessageDialog(null, "Venda deletado com sucesso!");
                carregarDadosNaTabela();
            } catch (Exception ex) {
                throw new RuntimeException(" \nCLASSE: ControllerVendaBusca->actionPerformed(ActionEvent e)->deletar\nMENSAGEM:" 
                        + ex.getMessage() + "\nLOCALIZADO:" 
                        + ex.getLocalizedMessage()
                );
            }
        }
       
    }
    
    private void carregarDadosNaTabela() {
        
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaCompra.getjTable_BuscaVendas().getModel();
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

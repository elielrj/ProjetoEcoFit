
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import view.TelaBuscaVenda;
import model.bo.Venda;

public class ControllerBuscaVenda implements ActionListener{
    
    TelaBuscaVenda telaBuscaVenda;
    
    public ControllerBuscaVenda(TelaBuscaVenda telaBuscaVenda){
        this.telaBuscaVenda = telaBuscaVenda;
        
        this.telaBuscaVenda.getjButtonCarregar().addActionListener(this);
        this.telaBuscaVenda.getjButtonSair().addActionListener(this);
        
        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVenda.getjTable1().getModel();
        
        for (Venda vendaDaLista : service.ServiceVenda.Buscar()) {
              tabela.addRow(new Object[]{
                  vendaDaLista.getId(),
                  vendaDaLista.getData(),
                  vendaDaLista.getHora(),
                  
                  vendaDaLista.getDataDeVencimento(),
                  vendaDaLista.getObservacao(),
                  vendaDaLista.getValorDoDesconto(),
                  
                  vendaDaLista.getValorTotal(),
                  vendaDaLista.getStatus(),
                  vendaDaLista.getPessoaFisica().toString()
              });
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == this.telaBuscaVenda.getjButtonCarregar()){
           ControllerVenda.codigo = (int) this.telaBuscaVenda.getjTable1().getValueAt(this.telaBuscaVenda.getjTable1().getSelectedRow(),0);
           this.telaBuscaVenda.dispose();
       }
       if(e.getSource() == this.telaBuscaVenda.getjButtonSair()){
           this.telaBuscaVenda.dispose();
       }
    }
    
}

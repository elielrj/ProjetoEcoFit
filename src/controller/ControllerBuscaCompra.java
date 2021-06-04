
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import view.TelaBuscaCompra;
import model.bo.Compra;

public class ControllerBuscaCompra implements ActionListener{
    
    TelaBuscaCompra telaBuscaCompra;
    
    public ControllerBuscaCompra(TelaBuscaCompra telaBuscaCompra){
        this.telaBuscaCompra = telaBuscaCompra;
        
        this.telaBuscaCompra.getjButtonCarregar().addActionListener(this);
        this.telaBuscaCompra.getjButtonSair().addActionListener(this);
        
        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaCompra.getjTable1().getModel();
        
        for (Compra compraDaLista : service.ServiceCompra.Buscar()) {
              tabela.addRow(new Object[]{compraDaLista.getId(),
                  compraDaLista.getData(),
                  compraDaLista.getHora(),
                  compraDaLista.getValorDeDesconto(),
                  compraDaLista.getValorTotal(),
              });
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == this.telaBuscaCompra.getjButtonCarregar()){
           ControllerCompra.codigo = (int) this.telaBuscaCompra.getjTable1().getValueAt(this.telaBuscaCompra.getjTable1().getSelectedRow(),0);
           this.telaBuscaCompra.dispose();
       }
       if(e.getSource() == this.telaBuscaCompra.getjButtonSair()){
           this.telaBuscaCompra.dispose();
       }
    }
    
}

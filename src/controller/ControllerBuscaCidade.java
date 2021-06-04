
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import view.TelaBuscaCidade;
import model.bo.Cidade;
import service.ServiceCidade;

public class ControllerBuscaCidade implements ActionListener{
    
    TelaBuscaCidade telaBuscaCidade;
     
    
    public ControllerBuscaCidade(TelaBuscaCidade telaBuscaCidade){
        this.telaBuscaCidade = telaBuscaCidade;
        
        
        this.telaBuscaCidade.getjButtonCarregar().addActionListener(this);
        this.telaBuscaCidade.getjButtonSair().addActionListener(this);
        
        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaCidade.getjTable1().getModel();
        
        for (Cidade cidadeDaLista : ServiceCidade.Buscar()) {
            tabela.addRow(new Object[]{
                cidadeDaLista.getId(),
                cidadeDaLista.getNome(),
                cidadeDaLista.getStatus()                
            });
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == this.telaBuscaCidade.getjButtonCarregar()){
           
           ControllerCidade.codigo = (int) this.telaBuscaCidade.getjTable1().getValueAt(this.telaBuscaCidade.getjTable1().getSelectedRow(),0);
           this.telaBuscaCidade.dispose();
       }
       if(e.getSource() == this.telaBuscaCidade.getjButtonSair()){
           this.telaBuscaCidade.dispose();
       }
    }
    
    
}

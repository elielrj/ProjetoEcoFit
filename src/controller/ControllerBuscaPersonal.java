
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import view.TelaBuscaPersonal;
import model.bo.Personal;

public class ControllerBuscaPersonal implements ActionListener{
    
    TelaBuscaPersonal telaBuscaPersonal;
    
    public ControllerBuscaPersonal(TelaBuscaPersonal telaBuscaPersonal){
        this.telaBuscaPersonal = telaBuscaPersonal;
        
        this.telaBuscaPersonal.getjButtonCarregar().addActionListener(this);
        this.telaBuscaPersonal.getjButtonSair().addActionListener(this);
        
        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaPersonal.getjTable1().getModel();
        
        for (Personal cidadeDaLista : service.ServicePersonal.Buscar()) {
              tabela.addRow(new Object[]{cidadeDaLista.getId(),cidadeDaLista.getRg(), cidadeDaLista.getCpf(), cidadeDaLista.getDataDeNascimento(), cidadeDaLista.getNome()});
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == this.telaBuscaPersonal.getjButtonCarregar()){
           ControllerPersonal.codigo = (int) this.telaBuscaPersonal.getjTable1().getValueAt(this.telaBuscaPersonal.getjTable1().getSelectedRow(),0);
           this.telaBuscaPersonal.dispose();
       }
       if(e.getSource() == this.telaBuscaPersonal.getjButtonSair()){
           this.telaBuscaPersonal.dispose();
       }
    }
    
}


package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import view.TelaBuscaEndereco;
import model.bo.Endereco;

public class ControllerBuscaEndereco implements ActionListener{
    
    TelaBuscaEndereco telaBuscaEndereco;
     
    
    public ControllerBuscaEndereco(TelaBuscaEndereco telaBuscaEndereco){
        this.telaBuscaEndereco = telaBuscaEndereco;
        
        
        this.telaBuscaEndereco.getjButtonCarregar().addActionListener(this);
        this.telaBuscaEndereco.getjButtonSair().addActionListener(this);
        
        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaEndereco.getjTable1().getModel();
        
        for (Endereco enderecoDaLista : service.ServiceEndereco.Buscar()) {
            tabela.addRow(new Object[]{
                enderecoDaLista.getId(),
                enderecoDaLista.getLogradouro(),
                enderecoDaLista.getNumero(),
                enderecoDaLista.getComplemento(),
                enderecoDaLista.getBairro().getNome(),
                enderecoDaLista.getCidade().getNome(),
                enderecoDaLista.getCep(),
                enderecoDaLista.getStatus()
                    
                });
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == this.telaBuscaEndereco.getjButtonCarregar()){
           
           ControllerEndereco.codigo = (int) this.telaBuscaEndereco.getjTable1().getValueAt(this.telaBuscaEndereco.getjTable1().getSelectedRow(),0);
           this.telaBuscaEndereco.dispose();
       }
       if(e.getSource() == this.telaBuscaEndereco.getjButtonSair()){
           this.telaBuscaEndereco.dispose();
       }
    }
    
    
}

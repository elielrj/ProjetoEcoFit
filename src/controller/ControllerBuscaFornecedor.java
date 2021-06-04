
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import view.TelaBuscaFornecedor;
import model.bo.Fornecedor;

public class ControllerBuscaFornecedor implements ActionListener{
    
    TelaBuscaFornecedor telaBuscaFornecedor;
    
    public ControllerBuscaFornecedor(TelaBuscaFornecedor telaBuscaFornecedor){
        this.telaBuscaFornecedor = telaBuscaFornecedor;
        
        this.telaBuscaFornecedor.getjButtonCarregar().addActionListener(this);
        this.telaBuscaFornecedor.getjButtonSair().addActionListener(this);
        
        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFornecedor.getjTable1().getModel();
        
        for (Fornecedor cidadeDaLista : service.ServiceFornecedor.Buscar()) {
              tabela.addRow(new Object[]{cidadeDaLista.getId(),
                  cidadeDaLista.getRazaoSocial(), 
                  cidadeDaLista.getCnpj(), 
                  cidadeDaLista.getInscricaoEstadual(),
                  cidadeDaLista.getTelefone1(),
                  cidadeDaLista.getTelefone2(),
                  cidadeDaLista.getEmail(),
                  cidadeDaLista.getEndereco().toString(),
                  cidadeDaLista.getComplemento()
              });
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == this.telaBuscaFornecedor.getjButtonCarregar()){
           ControllerFornecedor.codigo = (int) this.telaBuscaFornecedor.getjTable1().getValueAt(this.telaBuscaFornecedor.getjTable1().getSelectedRow(),0);
           this.telaBuscaFornecedor.dispose();
       }
       if(e.getSource() == this.telaBuscaFornecedor.getjButtonSair()){
           this.telaBuscaFornecedor.dispose();
       }
    }
    
}

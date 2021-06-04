
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import view.TelaBuscaProduto;
import model.bo.Produto;

public class ControllerBuscaProduto implements ActionListener{
    
    TelaBuscaProduto telaBuscaProduto;
    
    public ControllerBuscaProduto(TelaBuscaProduto telaBuscaProduto){
        this.telaBuscaProduto = telaBuscaProduto;
        
        this.telaBuscaProduto.getjButtonCarregar().addActionListener(this);
        this.telaBuscaProduto.getjButtonSair().addActionListener(this);
        
        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaProduto.getjTable1().getModel();
        
        for (Produto produtoDaLista : service.ServiceProduto.Buscar()) {
              tabela.addRow(new Object[]{produtoDaLista.getId(),
                    produtoDaLista.getDescricao(),
                    produtoDaLista.getUnidadeDeCompra(),
                    produtoDaLista.getUnidadeDeVenda(),
                    produtoDaLista.getCorrelacaoUnidade(),
                    produtoDaLista.getValor(),
                    produtoDaLista.getQuantidadeDeEstoque(),
                    produtoDaLista.getCodigoDeBarras(),
                    produtoDaLista.getStatus()
              });
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == this.telaBuscaProduto.getjButtonCarregar()){
           ControllerProduto.codigo = (int) this.telaBuscaProduto.getjTable1().getValueAt(this.telaBuscaProduto.getjTable1().getSelectedRow(),0);
           this.telaBuscaProduto.dispose();
       }
       if(e.getSource() == this.telaBuscaProduto.getjButtonSair()){
           this.telaBuscaProduto.dispose();
       }
    }
    
}

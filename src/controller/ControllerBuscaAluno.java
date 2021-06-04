
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import view.TelaBuscaAluno;
import model.bo.PessoaFisica;

public class ControllerBuscaAluno implements ActionListener{
    
    TelaBuscaAluno telaBuscaAluno;
    
    public ControllerBuscaAluno(TelaBuscaAluno telaBuscaAluno){
        this.telaBuscaAluno = telaBuscaAluno;
        
        this.telaBuscaAluno.getjButtonCarregar().addActionListener(this);
        this.telaBuscaAluno.getjButtonSair().addActionListener(this);
        
        //fazer a carga inicial do jtable
        DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaAluno.getjTable1().getModel();
        
        for (PessoaFisica pessoaFisica : service.ServiceAluno.Buscar()) {
              tabela.addRow(new Object[]{pessoaFisica.getId(),
                  pessoaFisica.getRg(), 
                  pessoaFisica.getCpf(), 
                  pessoaFisica.getDataDeNascimento(), 
                  pessoaFisica.getComplemento(),
                  pessoaFisica.getTelefone1(),
                  pessoaFisica.getTelefone2(),
                  pessoaFisica.getEmail(),
                  pessoaFisica.getObservacao(),
                  pessoaFisica.getEndereco().toString()
        });
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == this.telaBuscaAluno.getjButtonCarregar()){
           ControllerAluno.codigo = (int) this.telaBuscaAluno.getjTable1().getValueAt(this.telaBuscaAluno.getjTable1().getSelectedRow(),0);
           this.telaBuscaAluno.dispose();
       }
       if(e.getSource() == this.telaBuscaAluno.getjButtonSair()){
           this.telaBuscaAluno.dispose();
       }
    }
    
}


package controller;

import java.awt.Component;
import java.awt.TextArea;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public final class LimpaEstadoDeComponentes {

    public static void limpa(boolean estadoCompo, Component[] componentes){    
    
        for(Component componente : componentes){
            if(componente instanceof JTextField){
                ((JTextField)componente).setText("");
                componente.setEnabled(estadoCompo);
            }
        
            if(componente instanceof JFormattedTextField){
                ((JFormattedTextField) componente).setText("");
                componente.setEnabled(estadoCompo);
            }
            
            if(componente instanceof JComboBox){
                ((JComboBox) componente).setSelectedItem(0);
                componente.setEnabled(estadoCompo);
            }
 
            
            if((componente instanceof JTextArea)){
                ((JTextArea) componente).setToolTipText("");
                ((JTextArea) componente).setEditable(estadoCompo);
            }

               if((componente instanceof JScrollPane)){
                ((JScrollPane) componente).setToolTipText("");
                ((JScrollPane) componente).setEnabled(estadoCompo);
            }
            
        } 
    }
}

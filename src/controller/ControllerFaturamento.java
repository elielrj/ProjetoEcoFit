package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.bo.ItemDeVenda;
import model.bo.PessoaFisica;
import model.bo.Produto;
import model.bo.Venda;
import view.TelaBuscaPessoaFisica;
import view.TelaBuscaProduto;
import view.TelaFaturamento;

public class ControllerFaturamento implements ActionListener {

    TelaFaturamento telaFaturamento = new TelaFaturamento();
    public static int codigo;

    public ControllerFaturamento(TelaFaturamento telaFaturamento) {

        this.telaFaturamento = telaFaturamento;

        this.telaFaturamento.getjButtonNovo().addActionListener(this);
        this.telaFaturamento.getjButtonBuscar().addActionListener(this);
        this.telaFaturamento.getjButtonCancelar().addActionListener(this);
        this.telaFaturamento.getjButtonGravar().addActionListener(this);
        this.telaFaturamento.getjButtonSair().addActionListener(this);
        this.telaFaturamento.getjButtonProdutoBusca().addActionListener(this);
        
        this.telaFaturamento.getjTextFieldProdutoCodBarras().addActionListener(this);
        this.telaFaturamento.getjButtonProdutoBusca().addActionListener(this);
        this.telaFaturamento.getjButtonAdicionar().addActionListener(this);
        
        this.telaFaturamento.getjTextFieldClienteId().addActionListener(this);
        this.telaFaturamento.getjButtonClienteBuscaId().addActionListener(this);
        
        this.telaFaturamento.getjTextFieldClienteNome().addActionListener(this);
        this.telaFaturamento.getjTextFieldClienteCidade().addActionListener(this);
        this.telaFaturamento.getjTextFieldClienteBairro().addActionListener(this);
        this.telaFaturamento.getjTextFieldClienteEmail().addActionListener(this);
        this.telaFaturamento.getjTextFieldClienteTel1().addActionListener(this);
        this.telaFaturamento.getjTextFieldClienteTel2().addActionListener(this);
        
        this.telaFaturamento.getjFormattedTextFieldFaturamentoData().addActionListener(this);
        this.telaFaturamento.getjFormattedTextFieldFaturamentoHora().addActionListener(this);
        this.telaFaturamento.getjTextFieldFaturamentoUsuario().addActionListener(this);
        this.telaFaturamento.getjComboBoxStatus().addActionListener(this);
        
        
        
        this.telaFaturamento.getjTextFieldProdutoCodBarras().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt){
                if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                    inserirItem();
                } else if (evt.getKeyCode() == KeyEvent.VK_F1){
                    buscaProdutoPorId();
                } else if (evt.getKeyCode() == KeyEvent.VK_F2){
                    novoFaturamento();
                }else if (evt.getKeyCode() == KeyEvent.VK_F3){
                    Ativa(true);
                    LimpaEstadoComponentes(false);
                }else if (evt.getKeyCode() == KeyEvent.VK_F4){
                    //persistir no banco
                }else if (evt.getKeyCode() == KeyEvent.VK_F5){
                    //remover item selecionado
                }
            }
        });

        Ativa(true);
        LimpaEstadoComponentes(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaFaturamento.getjButtonNovo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);            
            codigo = 0;
        } else if (e.getSource() == this.telaFaturamento.getjButtonCancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);
        } else if (e.getSource() == this.telaFaturamento.getjButtonGravar()) {
            //montar objeto a persistir
            Venda venda = new Venda();

            venda.setData(this.telaFaturamento.getjFormattedTextFieldFaturamentoData().getText());
            venda.setHora(this.telaFaturamento.getjFormattedTextFieldFaturamentoHora().getText());
            venda.setStatus(false);

            if (codigo == 0) {
                service.ServiceVenda.Incluir(venda);
            } else {
               // venda.setId(Integer.parseInt(this.telaFaturamento.getjTextFieldIdVenda().getText()));
                service.ServiceVenda.Atualizar(venda);
            }
            Ativa(true);
            LimpaEstadoComponentes(false);
        }
        if (e.getSource() == this.telaFaturamento.getjButtonBuscar()) {

            codigo = 0;
            //TelaBuscaFaturamento telaBuscaVenda = new TelaBuscaFaturamento(null, true);
            //ControllerBuscaVenda controllerBuscaVenda = new ControllerBuscaVenda(telaBuscaVenda);
            //telaBuscaVenda.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);
                Venda venda = new Venda();
                venda = service.ServiceVenda.Buscar(codigo);

                //this.telaFaturamento.getjTextFieldIdVenda().setText(venda.getId() + "");
                this.telaFaturamento.getjFormattedTextFieldFaturamentoData().setText(venda.getData());
                this.telaFaturamento.getjFormattedTextFieldFaturamentoHora().setText(venda.getHora());
                //this.telaFaturamento.getjComboBoxStatus().setSelectedItem(venda.getStatus());

               // this.telaFaturamento.getjTextFieldIdVenda().setEnabled(false);
            }
        }
        if(e.getSource() == this.telaFaturamento.getjButtonProdutoBusca()){
             buscaProdutoPorId();
             
            //TelaBuscaProduto telaBuscaProduto = new TelaBuscaProduto(null,true);
            //ControllerBuscaProduto controllerBuscaProduto = new ControllerBuscaProduto(telaBuscaProduto);
            //telaBuscaProduto.setVisible(true);
            //this.telaFaturamento.getjTextFieldProdutoCodBarras().setText(telaBuscaProduto.getCodProduto()+"");
        }
        
        if(e.getSource() == this.telaFaturamento.getjButtonAdicionar()){
            if(!this.telaFaturamento.getjTextFieldProdutoCodBarras().getText().equals("")){
                inserirItem();
            }else{
                this.telaFaturamento.getjTextFieldProdutoCodBarras().requestFocus();
            }
        }
        
        
        
        if (e.getSource() == this.telaFaturamento.getjButtonSair()) {
            this.telaFaturamento.dispose();
        }
        
        if(e.getSource() == this.telaFaturamento.getjButtonClienteBuscaId()){
            buscaCliente();
        }
    }

    public void Ativa(boolean estadoBotoes) {
        this.telaFaturamento.getjButtonNovo().setEnabled(estadoBotoes);
        this.telaFaturamento.getjButtonCancelar().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButtonGravar().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButtonBuscar().setEnabled(estadoBotoes);
        this.telaFaturamento.getjButtonSair().setEnabled(estadoBotoes);
        
        this.telaFaturamento.getjTextFieldProdutoCodBarras().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButtonProdutoBusca().setEnabled(!estadoBotoes);
        
        this.telaFaturamento.getjButtonAdicionar().setEnabled(!estadoBotoes);
        
        this.telaFaturamento.getjTextFieldClienteId().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButtonClienteBuscaId().setEnabled(!estadoBotoes);
        
        this.telaFaturamento.getjTextFieldClienteNome().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjTextFieldClienteCidade().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjTextFieldClienteBairro().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjTextFieldClienteEmail().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjTextFieldClienteTel1().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjTextFieldClienteTel2().setEnabled(!estadoBotoes);
        
        this.telaFaturamento.getjFormattedTextFieldFaturamentoData().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjFormattedTextFieldFaturamentoHora().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjTextFieldFaturamentoUsuario().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjComboBoxStatus().setEnabled(!estadoBotoes);
        
    }

    public void LimpaEstadoComponentes(boolean estadoCompo) {

        Component[] componentes = this.telaFaturamento.getjTableFaturamentoItens().getComponents(); //verificar
        for (Component componente : componentes) {
            if (componente instanceof JTextField) {
                ((JTextField) componente).setText("");
                componente.setEnabled(estadoCompo);
            }

            if (componente instanceof JFormattedTextField) {
                ((JFormattedTextField) componente).setText("");
                componente.setEnabled(estadoCompo);
            }

            if (componente instanceof JComboBox) {
                ((JComboBox) componente).setSelectedItem(0);
                componente.setEnabled(estadoCompo);
            }

            if ((componente instanceof JTextArea)) {
                ((JTextArea) componente).setToolTipText("");
                ((JTextArea) componente).setEditable(estadoCompo);
            }

            if ((componente instanceof JTextArea)) {
                ((JTextArea) componente).setText("");
                componente.setEnabled(estadoCompo);
            }
            if (componente instanceof JComboBox) {
                ((JComboBox) componente).setSelectedItem(0);
                componente.setEnabled(estadoCompo);
            }

        }
    }

    private void buscaProdutoPorId() {
        
        TelaBuscaProduto telaBuscaProduto = new TelaBuscaProduto(null,true);
        ControllerBuscaProduto controllerBuscaProduto = new ControllerBuscaProduto(telaBuscaProduto);
        telaBuscaProduto.setVisible(true);
        
        this.telaFaturamento.getjTextFieldProdutoCodBarras().setText(telaBuscaProduto.getCodProduto()+"");
    }
   
    
    public void inserirItem(){
        if(this.telaFaturamento.getjComboBoxStatus().getSelectedItem().equals("Faturando")){
            if(this.telaFaturamento.getjTextFieldProdutoCodBarras().getText().length() >= 13){
                Produto produto = service.ServiceProduto.Buscar(Integer.parseInt(this.telaFaturamento.getjTextFieldProdutoCodBarras().getText()));
                ItemDeVenda item = new ItemDeVenda(false,1, produto, produto.getValor());
                this.telaFaturamento.getListaDeItens().add(item);
                
                this.telaFaturamento.getTabela().addRow(new Object[]{
                     this.telaFaturamento.getContador(),
                     item.getId(),
                     item.getProduto().getId(),
                     1,
                     item.getValor(),
                     item.getValor()* 1
               });
                
                this.telaFaturamento.setContador(this.telaFaturamento.getContador()+1);
            }else{
                JOptionPane.showMessageDialog(null, "Cód de Barras inválido");
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Não existe um faturamento em andamento");
        }
    }
    
    public void atualizarValorTotal(){
        int total = 0;
        for(ItemDeVenda item: this.telaFaturamento.getListaDeItens()){
            total += item.getQuantidade() * item.getValor();
        }
        this.telaFaturamento.getjLabelFaturamentoValorTotal().setText(total+"");
    }
    
    public void novoFaturamento(){
        if(this.telaFaturamento.getjComboBoxStatus().getSelectedItem().equals("Faturando")){
            this.telaFaturamento.getjComboBoxStatus().setSelectedItem("Faturando");
        }else{
            JOptionPane.showMessageDialog(null, "Existe um faturamento em andamento!");
        }
    }

    public void buscaCliente(){
        TelaBuscaPessoaFisica telaBuscaPessoaFisica = new TelaBuscaPessoaFisica(null,true);
        ControllerBuscaPessoaFisica controllerBuscaPessoaFisica = new ControllerBuscaPessoaFisica(telaBuscaPessoaFisica);
        telaBuscaPessoaFisica.setVisible(true);
        this.telaFaturamento.getjTextFieldClienteId().setText(telaBuscaPessoaFisica.getCodPessoaFisica()+"");
        PessoaFisica pessoaFisica = service.ServicePessoaFisica.Buscar(telaBuscaPessoaFisica.getCodPessoaFisica());
        this.telaFaturamento.getjTextFieldClienteNome().setText(pessoaFisica.getNome());
        this.telaFaturamento.getjTextFieldClienteCidade().setText(pessoaFisica.getEndereco().getCidade()+"");
        this.telaFaturamento.getjTextFieldClienteBairro().setText(pessoaFisica.getEndereco().getBairro()+"");
        this.telaFaturamento.getjTextFieldClienteEmail().setText(pessoaFisica.getEmail()+"");
        this.telaFaturamento.getjTextFieldClienteTel1().setText(pessoaFisica.getTelefone1()+"");
        this.telaFaturamento.getjTextFieldClienteTel2().setText(pessoaFisica.getTelefone2()+"");
    }
}

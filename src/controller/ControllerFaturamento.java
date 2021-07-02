package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.DAO.FaturamentoDAO;
import model.DAO.ItemDeVendaDAO;
import model.bo.ItemDeVenda;
import model.bo.PessoaFisica;
import model.bo.Produto;
import view.TelaBuscaPessoaFisica;
import view.TelaBuscaProduto;
import view.TelaFaturamento;
import model.bo.Faturamento;
import view.TelaBuscaFaturamento;

public class ControllerFaturamento implements ActionListener {
    
    TelaFaturamento telaFaturamento;
    public static int codigo;
    Faturamento faturamento;
    DefaultTableModel tabela;
    
    public ControllerFaturamento(TelaFaturamento telaFaturamento) {
        
        this.telaFaturamento = telaFaturamento;
        criarNovoFaturamento();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //BUTTUN NEW
        if (e.getSource() == this.telaFaturamento.getjButton_Novo()) {
            novoFaturamento();
            //BUTTUN CANELAR
        } else if (e.getSource() == this.telaFaturamento.getjButton_Cancelar()) {
            cancelarFaturamento();
            //BUTTUN GRAVAR
        } else if (e.getSource() == this.telaFaturamento.getjButton_Gravar()) {
            if (validarCliente()) {
                gravarFaturamento();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um cliente!");
                this.telaFaturamento.getjTextField_ClienteId().requestFocus();
            }
            //BUTTUN BUSCAR
        } else if (e.getSource() == this.telaFaturamento.getjButton_Buscar()) {

            //codigo = 0;
            TelaBuscaFaturamento telaBuscaFaturamento = new TelaBuscaFaturamento(null, true);
            ControllerBuscaFaturamento controllerBuscaFaturamento = new ControllerBuscaFaturamento(telaBuscaFaturamento);
            telaBuscaFaturamento.setVisible(true);
            
            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);
                faturamento = new Faturamento();
                faturamento = service.ServiceFaturamento.Buscar(codigo);
                
                this.telaFaturamento.getjTextField_Faturamento_Id().setText(faturamento.getId() + "");
                this.telaFaturamento.getjFormattedTextField_FaturamentoData().setText(faturamento.getData());
                this.telaFaturamento.getjFormattedTextField_FaturamentoHora().setText(faturamento.getHora() + "");
                this.telaFaturamento.getjFormattedTextField_DataDeVencimento().setText(faturamento.getDataDeVencimento() + "");
                this.telaFaturamento.getjTextArea_Obs().setText(faturamento.getObservacao() + "");
                this.telaFaturamento.getjFormattedTextField_ValorDeDesconto().setText(faturamento.getValorDoDesconto() + "");
                this.telaFaturamento.getjComboBoxStatus().setSelectedItem(faturamento.getStatus());
                this.telaFaturamento.getjTextField_ClienteId().setText(faturamento.getPessoaFisica().getId() + "");
                this.telaFaturamento.getjTextField_FaturamentoUsuario().setText(faturamento.getUserCaixa());
                
                this.telaFaturamento.getjTextField_ProdutoCodBarras().setText("");
                
                atualizarClienteNaTelaFaturamento(faturamento.getPessoaFisica());
                
                ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
                faturamento.setListaDeItens((ArrayList<ItemDeVenda>) itemDeVendaDAO.RetrieveTodosOsItensDeUmaVenda(faturamento.getId()));
                
                this.faturamento.setListaDeItens(faturamento.getListaDeItens());
                
                atualizarTabelaDeItens();
                atualizarTotalDaCompra();
                
            }
            //BUTTUN PESQUISAR PRODUTO
        } else if (e.getSource() == this.telaFaturamento.getjButton_ProdutoPesquisa()) {
            buscaProdutoPorId();
        } else if (e.getSource() == this.telaFaturamento.getjButton_ProdutoAdicionar()) {
            inserirItem();
            //BUTTUN SAIR
        } else if (e.getSource() == this.telaFaturamento.getjButton_Sair()) {
            this.telaFaturamento.dispose();
            //BUTTUN ADD CLIENTE
        } else if (e.getSource() == this.telaFaturamento.getjButton_ClienteBuscaId()) {
            buscaCliente();
        } else if (e.getSource() == this.telaFaturamento.getjButton_ProdutoRemover()) {
            removerItemFaturado();
        }
    }
    
    public void Ativa(boolean estadoBotoes) {
        this.telaFaturamento.getjButton_Novo().setEnabled(estadoBotoes);
        this.telaFaturamento.getjButton_Cancelar().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButton_Gravar().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButton_Buscar().setEnabled(estadoBotoes);
        this.telaFaturamento.getjButton_Sair().setEnabled(estadoBotoes);
        
        this.telaFaturamento.getjTextField_ProdutoCodBarras().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButton_ProdutoPesquisa().setEnabled(!estadoBotoes);
        
        this.telaFaturamento.getjButton_ProdutoAdicionar().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButton_ProdutoRemover().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButton_ClienteBuscaId().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjTable_FaturamentoItens().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjComboBoxStatus().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjTextArea_Obs().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjFormattedTextField_ValorDeDesconto().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjFormattedTextField_DataDeVencimento().setEnabled(!estadoBotoes);
        
    }
    
    public void desativarPorPadrao() {
        this.telaFaturamento.getjTextField_Cliente_Nome().setEnabled(false);
        this.telaFaturamento.getjTextField_Cliente_Cidade().setEnabled(false);
        this.telaFaturamento.getjTextField_Cliente_Bairro().setEnabled(false);
        this.telaFaturamento.getjTextField_Cliente_Email().setEnabled(false);
        this.telaFaturamento.getjTextField_Cliente_Tel1().setEnabled(false);
        this.telaFaturamento.getjTextField_Cliente_Tel2().setEnabled(false);
        this.telaFaturamento.getjTextField_ClienteId().setEnabled(false);
        
        this.telaFaturamento.getjFormattedTextField_FaturamentoData().setEnabled(false);
        this.telaFaturamento.getjFormattedTextField_FaturamentoHora().setEnabled(false);
        this.telaFaturamento.getjTextField_FaturamentoUsuario().setEnabled(false);
        this.telaFaturamento.getjTextField_Faturamento_Id().setEnabled(false);
        
    }
    
    public void LimpaEstadoComponentes(boolean estadoCompo) {
        
        Component[] componentes = this.telaFaturamento.getjTable_FaturamentoItens().getComponents(); //verificar
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
        
        TelaBuscaProduto telaBuscaProduto = new TelaBuscaProduto(null, true);
        ControllerBuscaProduto controllerBuscaProduto = new ControllerBuscaProduto(telaBuscaProduto);
        telaBuscaProduto.setVisible(true);
        
        this.telaFaturamento.getjTextField_ProdutoCodBarras().setText(
                service.ServiceProduto.Buscar(
                        telaBuscaProduto.getCodProduto()
                ).getCodigoDeBarras());
    }
    
    public void inserirItem() {
        if (this.telaFaturamento.getjComboBoxStatus().getSelectedItem().equals("Faturando")) {
            if (validarCodigoBarras()) {
                //CRIA PRODUTO
                Produto produto = service.ServiceProduto.Buscar(
                        Integer.parseInt(
                                faturamento.semMascara(
                                        this.telaFaturamento.getjTextField_ProdutoCodBarras().getText()
                                )
                        )
                );
                //CRIA ITEM DE VENDA
                ItemDeVenda item = new ItemDeVenda(true, 1, produto, produto.getValor()); //ID_VENDA & ID_ITEM_DE_VENDA SÓ APÓS FINALIZAÇÃO                                
                //ADICIONA A LISTA DE ITENS
                this.faturamento.adicionar(item);
                //ATUALIZAR A LISTA DE ITENS VENDIDOS NA TABELA DE FATURAMENTO
                atualizarTabelaDeItens();
                //ATUALIZA TOTAL DA COMPRA
                atualizarTotalDaCompra();
            } else {
                JOptionPane.showMessageDialog(null, "Cód de Barras inválido");
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Não existe um faturamento em andamento");
        }
    }
    
    public void novoFaturamento() {
        Ativa(false);
        LimpaEstadoComponentes(true);
        codigo = 0;
        this.telaFaturamento.getjComboBoxStatus().setSelectedItem("Faturando");
        
    }
    
    private void atualizarClienteNaTelaFaturamento(PessoaFisica pessoaFisica) {        
        this.telaFaturamento.getjTextField_Cliente_Nome().setText(pessoaFisica.getNome());
        this.telaFaturamento.getjTextField_Cliente_Cidade().setText(pessoaFisica.getEndereco().getCidade().toString());
        this.telaFaturamento.getjTextField_Cliente_Bairro().setText(pessoaFisica.getEndereco().getBairro().toString());
        this.telaFaturamento.getjTextField_Cliente_Email().setText(pessoaFisica.getEmail());
        this.telaFaturamento.getjTextField_Cliente_Tel1().setText(pessoaFisica.getTelefone1());
        this.telaFaturamento.getjTextField_Cliente_Tel2().setText(pessoaFisica.getTelefone2());
    }
    
    public void buscaCliente() {
        TelaBuscaPessoaFisica telaBuscaPessoaFisica = new TelaBuscaPessoaFisica(null, true);
        ControllerBuscaPessoaFisica controllerBuscaPessoaFisica = new ControllerBuscaPessoaFisica(telaBuscaPessoaFisica);
        telaBuscaPessoaFisica.setVisible(true);
        
        this.telaFaturamento.getjTextField_ClienteId().setText(telaBuscaPessoaFisica.getCodPessoaFisica() + "");
        PessoaFisica pessoaFisica = service.ServicePessoaFisica.Buscar(telaBuscaPessoaFisica.getCodPessoaFisica());
        /*
        this.telaFaturamento.getjTextField_Cliente_Nome().setText(pessoaFisica.getNome());
        this.telaFaturamento.getjTextField_Cliente_Cidade().setText(pessoaFisica.getEndereco().getCidade() + "");
        this.telaFaturamento.getjTextField_Cliente_Bairro().setText(pessoaFisica.getEndereco().getBairro() + "");
        this.telaFaturamento.getjTextField_Cliente_Email().setText(pessoaFisica.getEmail() + "");
        this.telaFaturamento.getjTextField_Cliente_Tel1().setText(pessoaFisica.getTelefone1() + "");
        this.telaFaturamento.getjTextField_Cliente_Tel2().setText(pessoaFisica.getTelefone2() + "");*/
        atualizarClienteNaTelaFaturamento(pessoaFisica);
    }
    
    private boolean validarCodigoBarras() {
        
        String codigoDeBarras = Faturamento.semMascara(this.telaFaturamento.getjTextField_ProdutoCodBarras().getText());
        
        if (codigoDeBarras.equals("")) { //"             "
            return false;
        } else if (codigoDeBarras.equals("")) {
            return false;
        } else if (codigoDeBarras.length() >= 13) {
            return false;
        } else {
            Produto produto = service.ServiceProduto.Buscar(Integer.parseInt(codigoDeBarras));
            if (produto.getId() == Integer.parseInt(codigoDeBarras)) {
                return true;
            }
            return false;
        }
        
    }
    
    private void atualizarTabelaDeItens() {
        tabela = (DefaultTableModel) this.telaFaturamento.getjTable_FaturamentoItens().getModel();
        tabela.getDataVector().removeAllElements();
        int contador = 0;
        if (faturamento.getListaDeItens().size() >= 1) {
            for (ItemDeVenda itemDeVenda : faturamento.getListaDeItens()) {
                tabela.addRow(new Object[]{
                    ++contador,
                    itemDeVenda.getProduto().getId(),
                    itemDeVenda.getProduto().getDescricao(),
                    itemDeVenda.getQuantidade(),
                    itemDeVenda.getValor(),
                    itemDeVenda.getValor() * itemDeVenda.getQuantidade()
                });
            }
        } else {
            tabela.addRow(new Object[]{});
        }
        this.telaFaturamento.getjTable_FaturamentoItens().setModel(tabela);
    }
    
    private void atualizarTotalDaCompra() {
        this.telaFaturamento.getjLabel_FaturamentoValorTotal().setText(faturamento.valorTotal() + "");
    }
    
    private void gravarFaturamento() {

        //codigo = 0;
        //montar objeto a persistir
        faturamento.setHora(this.telaFaturamento.getjFormattedTextField_FaturamentoHora().getText());
        faturamento.setData(this.telaFaturamento.getjFormattedTextField_FaturamentoData().getText());
        faturamento.setUserCaixa(this.telaFaturamento.getjTextField_FaturamentoUsuario().getText());
        faturamento.setDataDeVencimento(this.telaFaturamento.getjFormattedTextField_DataDeVencimento().getText());
        faturamento.setObservacao(this.telaFaturamento.getjTextArea_Obs().getText());
        faturamento.setValorDoDesconto(Float.parseFloat(this.telaFaturamento.getjFormattedTextField_ValorDeDesconto().getText()));
        faturamento.setValorTotal(Float.parseFloat(this.telaFaturamento.getjLabel_FaturamentoValorTotal().getText()));
        faturamento.setStatus(this.telaFaturamento.getjComboBoxStatus().getSelectedItem().equals("Faturando"));
        //PESSOA FÍSICA
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica = service.ServicePessoaFisica.Buscar(Integer.parseInt(this.telaFaturamento.getjTextField_ClienteId().getText()));
        faturamento.setPessoaFisica(pessoaFisica);
        //VENDA OK

        if (codigo == 0) {
            //Primeiro inluir a venda
            service.ServiceFaturamento.Incluir(faturamento);
            //Resgatar o Id da Venda
            FaturamentoDAO faturamentoDAO = new FaturamentoDAO();
            int idVenda = faturamentoDAO.buscarId(faturamento.getValorTotal(), faturamento.getPessoaFisica().getId(), faturamento.getData());
            //Terceito: incluir os itens com o Id_da_Venda nos ITENS DE VENDA
            ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
            for (ItemDeVenda itemDeVenda : faturamento.getListaDeItens()) {
                itemDeVenda.setVendaId(idVenda);
                itemDeVendaDAO.Create(itemDeVenda);
            }
            
        } else {
            //Setar ID_do faturamento
            faturamento.setId(Integer.parseInt(this.telaFaturamento.getjTextField_Faturamento_Id().getText()));
            //Atualizar
            service.ServiceFaturamento.Atualizar(faturamento);
            //setar id_venda em cada item de venda            
            ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
            
            for (ItemDeVenda itemDeVenda : faturamento.getListaDeItens()) {
                itemDeVenda.setVendaId(faturamento.getId());
            }
            
            List<ItemDeVenda> listaDeItensAnterior = itemDeVendaDAO.RetrieveTodosOsItensDeUmaVenda(Integer.parseInt(this.telaFaturamento.getjTextField_Faturamento_Id().getText()));
            
            List<ItemDeVenda> listaDeItensAtual = faturamento.getListaDeItens();
            List<ItemDeVenda> listaDeItensAtual_menos = faturamento.getListaDeItens();
            
            List<ItemDeVenda> listaDeItensAnterior_menos = itemDeVendaDAO.RetrieveTodosOsItensDeUmaVenda(Integer.parseInt(this.telaFaturamento.getjTextField_Faturamento_Id().getText()));
            
            for (ItemDeVenda anterior : listaDeItensAnterior) {
                for (ItemDeVenda atual : listaDeItensAtual) {
                    if (atual.getId() == anterior.getId()) {
                        itemDeVendaDAO.Update(atual);
                        //listaDeItensAnterior_menos.remove(atual);
                        //listaDeItensAtual_menos.remove(atual);
                    }
                }
            }
            
            for (ItemDeVenda atual : listaDeItensAtual_menos) {
                /*
                if(atual.getId() >= 1){
                    JOptionPane.showMessageDialog(null, "Erro");
                    //continue;
                    
                }else{*/
                itemDeVendaDAO.Create(atual);
                //listaDeItensAtual.remove(atual);
                //}
            }
            
            for (ItemDeVenda anterior : listaDeItensAnterior_menos) {
                itemDeVendaDAO.Delete(anterior);
            }
            
        }
        
        Ativa(true);
        LimpaEstadoComponentes(false);
        faturamento.removerTudo();
        
        atualizarTabelaDeItens();
        atualizarTotalDaCompra();
        
    }
    
    private void cancelarFaturamento() {
        faturamento.removerTudo();
        atualizarTabelaDeItens();
        atualizarTotalDaCompra();
        Ativa(true);
        LimpaEstadoComponentes(false);
        
        this.telaFaturamento.getjTextField_Cliente_Nome().setText("");
        this.telaFaturamento.getjTextField_Cliente_Cidade().setText("");
        this.telaFaturamento.getjTextField_Cliente_Bairro().setText("");
        this.telaFaturamento.getjTextField_Cliente_Email().setText("");
        this.telaFaturamento.getjTextField_Cliente_Tel1().setText("");
        this.telaFaturamento.getjTextField_Cliente_Tel2().setText("");
        this.telaFaturamento.getjTextArea_Obs().setText("");
        this.telaFaturamento.getjFormattedTextField_ValorDeDesconto().setText("");
        this.telaFaturamento.getjFormattedTextField_DataDeVencimento().setText("");
        this.telaFaturamento.getjTextField_ProdutoCodBarras().setText("");
    }
    
    private void removerItemFaturado() {
        int idDaLinhaSelecionada;
        
        if (this.telaFaturamento.getjTable_FaturamentoItens().getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Não selecione uma linha da tabela de itens!");
            this.telaFaturamento.getjTable_FaturamentoItens().requestFocus();
        } else {
            
            idDaLinhaSelecionada = (int) this.telaFaturamento.getjTable_FaturamentoItens().getValueAt(
                    this.telaFaturamento.getjTable_FaturamentoItens().getSelectedRow(), 1);
            if (faturamento.existeItemNaLista(idDaLinhaSelecionada)) {
                faturamento.removerItemDaLista(idDaLinhaSelecionada);
                atualizarTabelaDeItens();
                atualizarTotalDaCompra();
                idDaLinhaSelecionada = 0;
            } else {
                JOptionPane.showMessageDialog(null, "Não selecione uma linha da tabela de itens!");
            }
            
        }
    }
    
    private boolean validarCliente() {
        if (this.telaFaturamento.getjTextField_ClienteId().getText() != "") {
            return true;
        }
        return false;
    }
    
    private void criarNovoFaturamento() {
        try {
            this.faturamento = new Faturamento();
            //DefaultTableModel tabela = (DefaultTableModel) this.telaFaturamento.getjTable_FaturamentoItens().getModel();
            //CREATE  DATE/HOUR/USER
            this.telaFaturamento.getjFormattedTextField_FaturamentoHora().setText(this.faturamento.getHora());
            this.telaFaturamento.getjFormattedTextField_FaturamentoData().setText(this.faturamento.getData());
            this.telaFaturamento.getjTextField_FaturamentoUsuario().setText(this.faturamento.getUserCaixa());
            this.telaFaturamento.getjFormattedTextField_DataDeVencimento().setText(this.faturamento.getDataDeVencimento());
            //LISTENER BUTTON
            this.telaFaturamento.getjButton_Novo().addActionListener(this);
            this.telaFaturamento.getjButton_Buscar().addActionListener(this);
            this.telaFaturamento.getjButton_Cancelar().addActionListener(this);
            this.telaFaturamento.getjButton_Gravar().addActionListener(this);
            this.telaFaturamento.getjButton_Sair().addActionListener(this);
            this.telaFaturamento.getjButton_ProdutoPesquisa().addActionListener(this);
            this.telaFaturamento.getjButton_ProdutoAdicionar().addActionListener(this);
            this.telaFaturamento.getjButton_ClienteBuscaId().addActionListener(this);
            this.telaFaturamento.getjButton_ProdutoRemover().addActionListener(this);
            //TEXT
            this.telaFaturamento.getjFormattedTextField_ValorDeDesconto().setText("0.00");
            //LISTENER KEY ENTER
            this.telaFaturamento.getjTextField_ProdutoCodBarras().addActionListener(this);
            this.telaFaturamento.getjTextField_ClienteId().addActionListener(this);
            
            this.telaFaturamento.getjTextField_ProdutoCodBarras().addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        inserirItem();
                    } else if (evt.getKeyCode() == KeyEvent.VK_F1) {
                        buscaProdutoPorId();
                    } else if (evt.getKeyCode() == KeyEvent.VK_F2) {
                        novoFaturamento();
                    } else if (evt.getKeyCode() == KeyEvent.VK_F3) {
                        cancelarFaturamento();
                    } else if (evt.getKeyCode() == KeyEvent.VK_F4) {
                        //persistir no banco
                        gravarFaturamento();
                    } else if (evt.getKeyCode() == KeyEvent.VK_F5) {
                        //remover item selecionado
                        removerItemFaturado();
                    }
                }
                
            });
            
            Ativa(true);
            desativarPorPadrao();
            LimpaEstadoComponentes(false);            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na criação de um novo ControllerFaturamento->criarNovoFaturamento(): void");
        }
    }
}

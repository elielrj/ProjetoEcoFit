package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.bo.Estoque;
import model.bo.ItemDeCompra;
import model.bo.Produto;
import view.busca.TelaBuscaFornecedor;
import view.busca.TelaBuscaProduto;
import model.bo.Compra;
import model.bo.ContaAPagar;
import view.busca.TelaBuscaCompra;
import view.busca.TelaBuscaFornecedor;
import view.cadastro.TelaCadastroCompra;

public class ControllerCompra implements ActionListener {

    TelaCadastroCompra telaCadastroCompra;
    public static int codigo;
    private Compra compra;

    public ControllerCompra(TelaCadastroCompra telaCadastroCompra) {

        this.telaCadastroCompra = telaCadastroCompra;
        this.compra = new Compra.CompraBuilder().createCompra();

        //BOTÕES
        this.telaCadastroCompra.getjButton_Novo().addActionListener(this);
        this.telaCadastroCompra.getjButton_Buscar().addActionListener(this);
        this.telaCadastroCompra.getjButton_Cancelar().addActionListener(this);
        this.telaCadastroCompra.getjButton_Gravar().addActionListener(this);
        this.telaCadastroCompra.getjButton_Sair().addActionListener(this);
        this.telaCadastroCompra.getjButton_ProdutoPesquisa().addActionListener(this);
        this.telaCadastroCompra.getjButton_ProdutoAdicionar().addActionListener(this);
        this.telaCadastroCompra.getjButton_ClienteBuscaId().addActionListener(this);
        this.telaCadastroCompra.getjButton_ProdutoRemover().addActionListener(this);
        //KEY   
        this.telaCadastroCompra.getjPanelDados().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (existeFaturamentoEmAndamento()) {
                        if (validarCodigoBarras()) {
                            inserirItem();
                        } else {
                            JOptionPane.showMessageDialog(null, "Cód de Barras inválido");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Não existe um faturamento em andamento");
                    }
                } else if (evt.getKeyCode() == KeyEvent.VK_F1) {
                    buscaCodigoDeBarrasDoProduto();
                } else if (evt.getKeyCode() == KeyEvent.VK_F2) {
                    novoFaturamento();
                } else if (evt.getKeyCode() == KeyEvent.VK_F3) {
                    cancelarFaturamento();
                } else if (evt.getKeyCode() == KeyEvent.VK_F4) {
                    //persistir no banco
                    gravarFaturamento();
                } else if (evt.getKeyCode() == KeyEvent.VK_F5) {
                    //remover item selecionado
                    if (existeFaturamentoEmAndamento()) {
                        removerItemFaturado();
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione 1 item!");
                    }
                }
            }

        });

        Ativa(true);
        desativarPorPadrao();
        LimpaEstadoComponentes(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 1 - NOVO
        if (e.getSource() == this.telaCadastroCompra.getjButton_Novo()) {
            novoFaturamento();
            // 2 - CANCELAR
        } else if (e.getSource() == this.telaCadastroCompra.getjButton_Cancelar()) {
            cancelarFaturamento();
            // 3- GRAVAR
        } else if (e.getSource() == this.telaCadastroCompra.getjButton_Gravar()) {
            if (validarCliente()) {
                gravarFaturamento();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um fornecedor!");
                this.telaCadastroCompra.getjTextField_ClienteId().requestFocus();
            }
            // 4- BUSCAR
        } else if (e.getSource() == this.telaCadastroCompra.getjButton_Buscar()) {

            //codigo = 0;
            TelaBuscaCompra TelaBuscaCompra = new TelaBuscaCompra(null, true);
            ControllerCompraBusca controllerCompraBusca = new ControllerCompraBusca(TelaBuscaCompra);
            TelaBuscaCompra.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);

                compra = service.ServiceCompra.Buscar(codigo);

                this.telaCadastroCompra.getjTextField_Faturamento_Id().setText(compra.getId() + "");//1
                this.telaCadastroCompra.getjFormattedTextField_FaturamentoData().setText(compra.getData());//2
                this.telaCadastroCompra.getjFormattedTextField_FaturamentoHora().setText(compra.getHora());//3
                this.telaCadastroCompra.getjTextField_FaturamentoUsuario().setText(compra.getUserCaixa());//4
                this.telaCadastroCompra.getjFormattedTextField_DataDeVencimento().setText(compra.getDataDeVencimento());//5
                this.telaCadastroCompra.getjTextArea_Obs().setText(compra.getObservacao());//6
                this.telaCadastroCompra.getjFormattedTextField_ValorDeDesconto().setText(compra.getValorDeDesconto()+ "");//7
                this.telaCadastroCompra.getjLabel_FaturamentoValorTotal().setText(compra.getValorTotal() + "");//8
                this.telaCadastroCompra.getjComboBoxStatus().setSelectedItem(compra.getStatus());//9
                //FORNECEDOR
                this.telaCadastroCompra.getjTextField_ClienteId().setText(compra.getFornecedor().getId() + "");//10 - 1
                this.telaCadastroCompra.getjTextField_Cliente_Nome().setText(compra.getFornecedor().getRazaoSocial());//10 - 2
                // Não necessário: 3,4,5
                this.telaCadastroCompra.getjTextField_Cliente_Cidade().setText(compra.getFornecedor().getEndereco().getBairro().getCidade().getNome());//10 - 6?
                this.telaCadastroCompra.getjTextField_Cliente_Bairro().setText(compra.getFornecedor().getEndereco().getBairro().getNome());//10 - 6?
                // Não necessário 7
                this.telaCadastroCompra.getjTextField_Cliente_Tel1().setText(compra.getFornecedor().getTelefone1());//10 -8 
                this.telaCadastroCompra.getjTextField_Cliente_Tel2().setText(compra.getFornecedor().getTelefone2());//10 - 9

                this.telaCadastroCompra.getjTextField_Cliente_Email().setText(compra.getFornecedor().getEmail());//10 - 10                
//
                compra.setItensDeCompra(service.ServiceItemDeCompra.BuscarListaDeUmaCompra(compra.getId())); //11
                atualizarTabelaDeItens();
            }
            // 5- PESQUISAR PRODUTO
        } else if (e.getSource() == this.telaCadastroCompra.getjButton_ProdutoPesquisa()) {
            buscaCodigoDeBarrasDoProduto();
            // 6 - ADICIONAR PRODUTO
        } else if (e.getSource() == this.telaCadastroCompra.getjButton_ProdutoAdicionar()) {
            if (existeFaturamentoEmAndamento()) {
                if (validarCodigoBarras()) {
                    inserirItem();
                } else {
                    JOptionPane.showMessageDialog(null, "Cód de Barras inválido");
                    this.telaCadastroCompra.getjFormattedTextField_ProdutoCodBarras().requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não existe um faturamento em andamento");
                this.telaCadastroCompra.getjComboBoxStatus().requestFocus();
            }
            // 8 - SAIR
        } else if (e.getSource() == this.telaCadastroCompra.getjButton_Sair()) {
            this.telaCadastroCompra.dispose();
            // 7 - PESQUISAR CLIENTE
        } else if (e.getSource() == this.telaCadastroCompra.getjButton_ClienteBuscaId()) {
            if (validarCliente()) {

                
                    buscaClienteFornecedor();
                
            } else {
                JOptionPane.showMessageDialog(null, "Busque um cliente válido!");
            }
            // 6 - REMOVER PRODUTO
        } else if (e.getSource() == this.telaCadastroCompra.getjButton_ProdutoRemover()) {

            if (existeFaturamentoEmAndamento()) {
                removerItemFaturado();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione 1 item!");
                this.telaCadastroCompra.getjTable_FaturamentoItens().requestFocus();
            }
        }
    }

    public void Ativa(boolean estadoBotoes) {
        this.telaCadastroCompra.getjButton_Novo().setEnabled(estadoBotoes);
        this.telaCadastroCompra.getjButton_Cancelar().setEnabled(!estadoBotoes);
        this.telaCadastroCompra.getjButton_Gravar().setEnabled(!estadoBotoes);
        this.telaCadastroCompra.getjButton_Buscar().setEnabled(estadoBotoes);
        this.telaCadastroCompra.getjButton_Sair().setEnabled(estadoBotoes);

        this.telaCadastroCompra.getjFormattedTextField_ProdutoCodBarras().setEnabled(!estadoBotoes);
        this.telaCadastroCompra.getjButton_ProdutoPesquisa().setEnabled(!estadoBotoes);

        this.telaCadastroCompra.getjButton_ProdutoAdicionar().setEnabled(!estadoBotoes);
        this.telaCadastroCompra.getjButton_ProdutoRemover().setEnabled(!estadoBotoes);
        this.telaCadastroCompra.getjButton_ClienteBuscaId().setEnabled(!estadoBotoes);
        this.telaCadastroCompra.getjTable_FaturamentoItens().setEnabled(!estadoBotoes);
        this.telaCadastroCompra.getjComboBoxStatus().setEnabled(!estadoBotoes);
        this.telaCadastroCompra.getjTextArea_Obs().setEnabled(!estadoBotoes);
        this.telaCadastroCompra.getjFormattedTextField_ValorDeDesconto().setEnabled(!estadoBotoes);
        this.telaCadastroCompra.getjFormattedTextField_DataDeVencimento().setEnabled(!estadoBotoes);
        
    }

    public void desativarPorPadrao() {
        this.telaCadastroCompra.getjTextField_Cliente_Nome().setEnabled(false);
        this.telaCadastroCompra.getjTextField_Cliente_Cidade().setEnabled(false);
        this.telaCadastroCompra.getjTextField_Cliente_Bairro().setEnabled(false);
        this.telaCadastroCompra.getjTextField_Cliente_Email().setEnabled(false);
        this.telaCadastroCompra.getjTextField_Cliente_Tel1().setEnabled(false);
        this.telaCadastroCompra.getjTextField_Cliente_Tel2().setEnabled(false);
        this.telaCadastroCompra.getjTextField_ClienteId().setEnabled(false);

        this.telaCadastroCompra.getjFormattedTextField_FaturamentoData().setEnabled(false);
        this.telaCadastroCompra.getjFormattedTextField_FaturamentoHora().setEnabled(false);
        this.telaCadastroCompra.getjTextField_FaturamentoUsuario().setEnabled(false);
        this.telaCadastroCompra.getjTextField_Faturamento_Id().setEnabled(false);

    }

    public void LimpaEstadoComponentes(boolean estadoCompo) {

        Component[] componentes = this.telaCadastroCompra.getjTable_FaturamentoItens().getComponents(); //verificar
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

    private void buscaCodigoDeBarrasDoProduto() {

        TelaBuscaProduto telaBuscaProduto = new TelaBuscaProduto(null, true);
        ControllerProdutoBusca controllerBuscaProduto = new ControllerProdutoBusca(telaBuscaProduto);
        telaBuscaProduto.setVisible(true);

        this.telaCadastroCompra.getjFormattedTextField_ProdutoCodBarras().setText(
                service.ServiceProduto.Buscar(
                        telaBuscaProduto.getCodProduto()
                ).getCodigoDeBarras()
        );
    }

    public void inserirItem() {
        //1º Cria e busca produto pelo Cod Barras
        Produto produto = service.ServiceProduto.Buscar(this.telaCadastroCompra.getjFormattedTextField_ProdutoCodBarras().getText());
        //2º Adicionar produto e qtd; atribuindo a responsabilidade a compra de criar ItemDeCompra
        compra.adicionarItem(produto);
        //3º atualizar tabela da tela
        atualizarTabelaDeItens();
    }

    public void novoFaturamento() {
        Ativa(false);
        LimpaEstadoComponentes(true);
        codigo = 0;
        this.telaCadastroCompra.getjComboBoxStatus().setSelectedItem("Faturando");
        this.telaCadastroCompra.getjTextField_FaturamentoUsuario().setText(compra.getUserCaixa());
        this.telaCadastroCompra.getjFormattedTextField_FaturamentoData().setText(compra.getData());
        this.telaCadastroCompra.getjFormattedTextField_FaturamentoHora().setText(compra.getHora());
        this.telaCadastroCompra.getjFormattedTextField_DataDeVencimento().setText(compra.getDataDeVencimento());
        this.telaCadastroCompra.getjFormattedTextField_ValorDeDesconto().setText(compra.getValorDeDesconto()+ "");
    }



    private boolean validarCodigoBarras() {
        /*
        String codigoDeBarras = semMascara(this.telaCadastroCompra.getjFormattedTextField_ProdutoCodBarras().getText());

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
        }*/

        return service.ServiceProduto.codigoDeBarrasValido(this.telaCadastroCompra.getjFormattedTextField_ProdutoCodBarras().getText());

    }

    private void atualizarTabelaDeItens() {
        DefaultTableModel tabela = (DefaultTableModel) this.telaCadastroCompra.getjTable_FaturamentoItens().getModel();
        tabela.getDataVector().removeAllElements();
        int contador = 0;
        if (compra.quantidadeDeItensNaLista() > 0) {
            for (ItemDeCompra itemDeCompra : compra.getItensDeCompra()) {
                tabela.addRow(new Object[]{
                    ++contador,
                    itemDeCompra.getProduto().getId(),
                    itemDeCompra.getProduto().getDescricao(),
                    itemDeCompra.getQuantidade(),
                    itemDeCompra.getProduto().getValor(),
                    itemDeCompra.getSubTotal()
                });
            }
        } else {
            tabela.addRow(new Object[]{});
        }
        this.telaCadastroCompra.getjTable_FaturamentoItens().setModel(tabela);
        this.telaCadastroCompra.getjLabel_FaturamentoValorTotal().setText(compra.calcularValorTotal() + "");
    }

    private void gravarFaturamento() {

        compra.setData(this.telaCadastroCompra.getjFormattedTextField_FaturamentoData().getText());//2
        compra.setHora(this.telaCadastroCompra.getjFormattedTextField_FaturamentoHora().getText());//3

        compra.setUserCaixa(this.telaCadastroCompra.getjTextField_FaturamentoUsuario().getText());//4
        compra.setDataDeVencimento(this.telaCadastroCompra.getjFormattedTextField_DataDeVencimento().getText());//5
        compra.setObservacao(this.telaCadastroCompra.getjTextArea_Obs().getText());//6
        compra.setValorDeDesconto(
                Float.parseFloat(
                        semMascara(this.telaCadastroCompra.getjFormattedTextField_ValorDeDesconto().getText())
                )
        );//7
        compra.setValorTotal(Float.parseFloat(this.telaCadastroCompra.getjLabel_FaturamentoValorTotal().getText()));//8
        compra.setStatus(this.telaCadastroCompra.getjComboBoxStatus().getSelectedItem().equals("Faturando"));//9
        //ATRIBUIÇÃO DA PESSOA FÍSICA É FEITA NA BUSCA - 10

        //VENDA OK
        if (codigo == 0) {
            //1º incluir a compra
            service.ServiceCompra.Incluir(compra);
            //Resgatar o Id da Compra
            compra.setId(service.ServiceCompra.Buscar(compra));
            //2º incluir os itens c/ idCompra na tabela de itens de compra no banco

            for (ItemDeCompra itemDeCompra : compra.getItensDeCompra()) {
                itemDeCompra.setCompraId(compra.getId());

                //ESTOQUE: 1º busca estoque pelo produtoId
                Estoque estoque = service.ServiceEstoque.BuscarEstoquePorIdDoProduto(itemDeCompra.getProduto().getId());
                //ESTOQUE: 2º setar a nova qtd no estoque
                estoque.setQuantidade(estoque.getQuantidade() + itemDeCompra.getQuantidade());
                service.ServiceEstoque.Atualizar(estoque);
                //VENDA: finalmente inluir-la
                service.ServiceItemDeCompra.Incluir(itemDeCompra);

            }
            
            //3º criar conta a receber
            ContaAPagar contaAPagar = new ContaAPagar.ContaAPagarBuilder()
                    .setCompraId(compra.getId())
                    .setValor(compra.getValorTotal())
                    .setStatus(false)
                    .createContaAPagar();
            service.ServiceContaAPagar.Incluir(contaAPagar);
            

            //DEBITAR NO ESTOQUE!!!!!!!!!!
        } else {
            //1º atualizar a compra
            service.ServiceCompra.Atualizar(compra);

            //2º deletar itens anterior no banco
            //buscar antes de deletar!
            List<ItemDeCompra> itensDeCompra = service.ServiceItemDeCompra.BuscarListaDeUmaCompra(compra.getId());

            for (ItemDeCompra itemDeCompra : itensDeCompra) {
                Estoque estoque = service.ServiceEstoque.BuscarEstoquePorIdDoProduto(itemDeCompra.getProduto().getId());
                estoque.setQuantidade(
                        estoque.getQuantidade() - itemDeCompra.getQuantidade()
                );
                service.ServiceEstoque.Atualizar(estoque);
                service.ServiceItemDeCompra.Deletar(itemDeCompra);
            }

            //3ºatualizar os itens c/ idCompra e incluir na tabela de itens de compra no banco          
            for (ItemDeCompra item : compra.getItensDeCompra()) {
                //Setar o IDCompra no item
                item.setCompraId(compra.getId());
                try {
                    //buscar estoque, depois debitar e atualizar
                    Estoque estoque = service.ServiceEstoque.Buscar(item.getProduto().getId());
                    estoque.setQuantidade(estoque.getQuantidade() + item.getQuantidade());
                    service.ServiceEstoque.Atualizar(estoque);
                    //Incluir item
                    service.ServiceItemDeCompra.Incluir(item);                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Estoque insuficiente!");
                    throw new RuntimeException(" \nCLASSE: BairroDAO->Retrive->bairroDAO\nMENSAGEM:"
                            + ex.getMessage() + "\nLOCALIZADO:"
                            + ex.getLocalizedMessage()
                    ); 
                } 
                //4º atualizar conta a receber (BUSCA, ATUALIZA VALOR, E ATUALIZA)
            ContaAPagar contaAPagar = service.ServiceContaAPagar.BuscarIdDaContaAReceberPeloIdDaCompra(compra.getId());
            contaAPagar.setValor(compra.getValorTotal());
            service.ServiceContaAPagar.Atualizar(contaAPagar);
            }
        }

        Ativa(true);
        LimpaEstadoComponentes(false);

    }

    private boolean itemDaTabelaEstaSelecionada() {
        return this.telaCadastroCompra.getjTable_FaturamentoItens().getSelectionModel().isSelectionEmpty();
    }

    private void removerItemFaturado() {

       // int numeroDaLinhasSelecionada = this.telaCadastroCompra.getjTable_FaturamentoItens().getSelectionModel().getSelectionMode();

        int idDoProdutoSelecionado = (int) this.telaCadastroCompra.getjTable_FaturamentoItens().getValueAt(
                this.telaCadastroCompra.getjTable_FaturamentoItens().getSelectedRow(), 1);
        Produto produto = service.ServiceProduto.Buscar(idDoProdutoSelecionado);
        compra.removerItemDaLista(produto);
        atualizarTabelaDeItens();

    }

    private boolean validarCliente() {
        if (this.telaCadastroCompra.getjTextField_ClienteId().getText() != "") {
            return true;
        }
        return false;
    }

    private void buscaClienteFornecedor() {

        TelaBuscaFornecedor telaBuscaFornecedor = new TelaBuscaFornecedor(null, true);
        ControllerFornecedorBusca controllerFornecedorBusca = new ControllerFornecedorBusca(telaBuscaFornecedor);
        telaBuscaFornecedor.setVisible(true);

        compra.setFornecedor(
                service.ServiceFornecedor.Buscar(
                        telaBuscaFornecedor.getIdDoFornecedor())
        );
        this.telaCadastroCompra.getjTextField_ClienteId().setText(telaBuscaFornecedor.getIdDoFornecedor()+ "");

        //FORNECEDOR
        this.telaCadastroCompra.getjTextField_ClienteId().setText(compra.getFornecedor().getId() + "");//10 - 1
        this.telaCadastroCompra.getjTextField_Cliente_Nome().setText(compra.getFornecedor().getRazaoSocial());//10 - 2
        // Não necessário: 3,4,5
        this.telaCadastroCompra.getjTextField_Cliente_Cidade().setText(compra.getFornecedor().getEndereco().getBairro().getCidade().getNome());//10 - 6?
        this.telaCadastroCompra.getjTextField_Cliente_Bairro().setText(compra.getFornecedor().getEndereco().getBairro().getNome());//10 - 6?
        // Não necessário 7
        this.telaCadastroCompra.getjTextField_Cliente_Tel1().setText(compra.getFornecedor().getTelefone1());//10 -8 
        this.telaCadastroCompra.getjTextField_Cliente_Tel2().setText(compra.getFornecedor().getTelefone2());//10 - 9
        this.telaCadastroCompra.getjTextField_Cliente_Email().setText(compra.getFornecedor().getEmail());//10 - 10 

    }

    private static String semMascara(String dado) {
        //dado = dado.replaceAll("\\.", "");
        dado = dado.replaceAll("\\,", ".");
        return dado;
    }

    private void cancelarFaturamento() {
        compra.removerTodosOsItensDaLista();
        atualizarTabelaDeItens();
        Ativa(true);
        LimpaEstadoComponentes(false);
    }

    private boolean existeFaturamentoEmAndamento() {
        if (this.telaCadastroCompra.getjComboBoxStatus().getSelectedItem().equals("Faturando")) {
            return true;
        }
        return false;
    }

    private void dataHora() {

        // data/hora atual
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        compra.setData(formatterData.format(agora));

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        compra.setHora(formatterHora.format(agora));

    }
}

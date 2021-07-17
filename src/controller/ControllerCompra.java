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
import model.bo.ItemDeVenda;
import model.bo.Produto;
import view.busca.TelaBuscaPessoaFisica;
import view.busca.TelaBuscaProduto;
import model.bo.Venda;
import view.busca.TelaBuscaVenda;
import view.cadastro.TelaCadastroCompra;

public class ControllerCompra implements ActionListener {

    TelaCadastroCompra telaCadastroCompra;
    public static int codigo;
    private Venda venda;

    public ControllerCompra(TelaCadastroCompra telaCadastroCompra) {

        this.telaCadastroCompra = telaCadastroCompra;
        this.venda = new Venda.VendaBuilder().createVenda();

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
                JOptionPane.showMessageDialog(null, "Selecione um cliente!");
                this.telaCadastroCompra.getjTextField_ClienteId().requestFocus();
            }
            // 4- BUSCAR
        } else if (e.getSource() == this.telaCadastroCompra.getjButton_Buscar()) {

            //codigo = 0;
            TelaBuscaVenda TelaBuscaVenda = new TelaBuscaVenda(null, true);
            ControllerVendaBusca controllerVendaBusca = new ControllerVendaBusca(TelaBuscaVenda);
            TelaBuscaVenda.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);

                venda = service.ServiceVenda.Buscar(codigo);

                this.telaCadastroCompra.getjTextField_Faturamento_Id().setText(venda.getId() + "");//1
                this.telaCadastroCompra.getjFormattedTextField_FaturamentoData().setText(venda.getData());//2
                this.telaCadastroCompra.getjFormattedTextField_FaturamentoHora().setText(venda.getHora());//3
                this.telaCadastroCompra.getjTextField_FaturamentoUsuario().setText(venda.getUserCaixa());//4
                this.telaCadastroCompra.getjFormattedTextField_DataDeVencimento().setText(venda.getDataDeVencimento());//5
                this.telaCadastroCompra.getjTextArea_Obs().setText(venda.getObservacao());//6
                this.telaCadastroCompra.getjFormattedTextField_ValorDeDesconto().setText(venda.getValorDoDesconto() + "");//7
                this.telaCadastroCompra.getjLabel_FaturamentoValorTotal().setText(venda.getValorTotal() + "");//8
                this.telaCadastroCompra.getjComboBoxStatus().setSelectedItem(venda.getStatus());//9
                //CLIENTE - PESSOA FÍSICA
                this.telaCadastroCompra.getjTextField_ClienteId().setText(venda.getPessoaFisica().getId() + "");//10 - 1
                this.telaCadastroCompra.getjTextField_Cliente_Nome().setText(venda.getPessoaFisica().getNome());//10 - 2
                // Não necessário: 3,4,5
                this.telaCadastroCompra.getjTextField_Cliente_Cidade().setText(venda.getPessoaFisica().getEndereco().getBairro().getCidade().getNome());//10 - 6?
                this.telaCadastroCompra.getjTextField_Cliente_Bairro().setText(venda.getPessoaFisica().getEndereco().getBairro().getNome());//10 - 6?
                // Não necessário 7
                this.telaCadastroCompra.getjTextField_Cliente_Tel1().setText(venda.getPessoaFisica().getTelefone1());//10 -8 
                this.telaCadastroCompra.getjTextField_Cliente_Tel2().setText(venda.getPessoaFisica().getTelefone2());//10 - 9

                this.telaCadastroCompra.getjTextField_Cliente_Email().setText(venda.getPessoaFisica().getEmail());//10 - 10                
//
                venda.setItensDeVenda(service.ServiceItemDeVenda.BuscarListaDeUmaVenda(venda.getId())); //11
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
        //2º Adicionar produto e qtd; atribuindo a responsabilidade a venda de criar ItemDeVenda
        venda.adicionarItem(produto);
        //3º atualizar tabela da tela
        atualizarTabelaDeItens();
    }

    public void novoFaturamento() {
        Ativa(false);
        LimpaEstadoComponentes(true);
        codigo = 0;
        this.telaCadastroCompra.getjComboBoxStatus().setSelectedItem("Faturando");
        this.telaCadastroCompra.getjTextField_FaturamentoUsuario().setText(venda.getUserCaixa());
        this.telaCadastroCompra.getjFormattedTextField_FaturamentoData().setText(venda.getData());
        this.telaCadastroCompra.getjFormattedTextField_FaturamentoHora().setText(venda.getHora());
        this.telaCadastroCompra.getjFormattedTextField_DataDeVencimento().setText(venda.getDataDeVencimento());
        this.telaCadastroCompra.getjFormattedTextField_ValorDeDesconto().setText(venda.getValorDoDesconto() + "");
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
        if (venda.quantidadeDeItensNaLista() > 0) {
            for (ItemDeVenda itemDeVenda : venda.getItensDeVenda()) {
                tabela.addRow(new Object[]{
                    ++contador,
                    itemDeVenda.getProduto().getId(),
                    itemDeVenda.getProduto().getDescricao(),
                    itemDeVenda.getQuantidade(),
                    itemDeVenda.getProduto().getValor(),
                    itemDeVenda.getSubTotal()
                });
            }
        } else {
            tabela.addRow(new Object[]{});
        }
        this.telaCadastroCompra.getjTable_FaturamentoItens().setModel(tabela);
        this.telaCadastroCompra.getjLabel_FaturamentoValorTotal().setText(venda.calcularValorTotal() + "");
    }

    private void gravarFaturamento() {

        venda.setData(this.telaCadastroCompra.getjFormattedTextField_FaturamentoData().getText());//2
        venda.setHora(this.telaCadastroCompra.getjFormattedTextField_FaturamentoHora().getText());//3

        venda.setUserCaixa(this.telaCadastroCompra.getjTextField_FaturamentoUsuario().getText());//4
        venda.setDataDeVencimento(this.telaCadastroCompra.getjFormattedTextField_DataDeVencimento().getText());//5
        venda.setObservacao(this.telaCadastroCompra.getjTextArea_Obs().getText());//6
        venda.setValorDoDesconto(
                Float.parseFloat(
                        semMascara(this.telaCadastroCompra.getjFormattedTextField_ValorDeDesconto().getText())
                )
        );//7
        venda.setValorTotal(Float.parseFloat(this.telaCadastroCompra.getjLabel_FaturamentoValorTotal().getText()));//8
        venda.setStatus(this.telaCadastroCompra.getjComboBoxStatus().getSelectedItem().equals("Faturando"));//9
        //ATRIBUIÇÃO DA PESSOA FÍSICA É FEITA NA BUSCA - 10

        //VENDA OK
        if (codigo == 0) {
            //1º incluir a venda
            service.ServiceVenda.Incluir(venda);
            //Resgatar o Id da Venda
            venda.setId(service.ServiceVenda.Buscar(venda));
            //2º incluir os itens c/ idVenda na tabela de itens de venda no banco

            for (ItemDeVenda itemDeVenda : venda.getItensDeVenda()) {
                itemDeVenda.setVendaId(venda.getId());

                //ESTOQUE: 1º busca estoque pelo produtoId
                Estoque estoque = service.ServiceEstoque.BuscarEstoquePorIdDoProduto(itemDeVenda.getProduto().getId());
                //ESTOQUE: 2º setar a nova qtd no estoque
                estoque.setQuantidade(estoque.getQuantidade() - itemDeVenda.getQuantidade());
                service.ServiceEstoque.Atualizar(estoque);
                //VENDA: finalmente inluir-la
                service.ServiceItemDeVenda.Incluir(itemDeVenda);

            }

            //DEBITAR NO ESTOQUE!!!!!!!!!!
        } else {
            //1º atualizar a venda
            service.ServiceVenda.Atualizar(venda);

            //2º deletar itens anterior no banco
            //buscar antes de deletar!
            List<ItemDeVenda> itensDeVenda = service.ServiceItemDeVenda.BuscarListaDeUmaVenda(venda.getId());

            for (ItemDeVenda itemDeVenda : itensDeVenda) {
                Estoque estoque = service.ServiceEstoque.BuscarEstoquePorIdDoProduto(itemDeVenda.getProduto().getId());
                estoque.setQuantidade(
                        estoque.getQuantidade() + itemDeVenda.getQuantidade()
                );
                service.ServiceEstoque.Atualizar(estoque);
                service.ServiceItemDeVenda.Deletar(itemDeVenda);
            }

            //3ºatualizar os itens c/ idVenda e incluir na tabela de itens de venda no banco          
            for (ItemDeVenda item : venda.getItensDeVenda()) {
                //Setar o IDVenda no item
                item.setVendaId(venda.getId());
                try {
                    //buscar estoque, depois debitar e atualizar
                    Estoque estoque = service.ServiceEstoque.Buscar(item.getProduto().getId());
                    estoque.setQuantidade(estoque.getQuantidade() - item.getQuantidade());
                    service.ServiceEstoque.Atualizar(estoque);
                    //Incluir item
                    service.ServiceItemDeVenda.Incluir(item);                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Estoque insuficiente!");
                    throw new RuntimeException(" \nCLASSE: BairroDAO->Retrive->bairroDAO\nMENSAGEM:"
                            + ex.getMessage() + "\nLOCALIZADO:"
                            + ex.getLocalizedMessage()
                    ); 
                } 
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
        venda.removerItemDaLista(produto);
        atualizarTabelaDeItens();

    }

    private boolean validarCliente() {
        if (this.telaCadastroCompra.getjTextField_ClienteId().getText() != "") {
            return true;
        }
        return false;
    }

    private void buscaClienteFornecedor() {

        TelaBuscaPessoaFisica telaBuscaPessoaFisica = new TelaBuscaPessoaFisica(null, true);
        ControllerPessoaFisicaPersonalBusca controllerBuscaPessoaFisicaPersonalBusca = new ControllerPessoaFisicaPersonalBusca(telaBuscaPessoaFisica);
        telaBuscaPessoaFisica.setVisible(true);

        venda.setPessoaFisica(
                service.ServicePessoaFisica.Buscar(
                        telaBuscaPessoaFisica.getIdDaPessoaFisica())
        );
        this.telaCadastroCompra.getjTextField_ClienteId().setText(telaBuscaPessoaFisica.getIdDaPessoaFisica() + "");

        //CLIENTE - PESSOA FÍSICA
        this.telaCadastroCompra.getjTextField_ClienteId().setText(venda.getPessoaFisica().getId() + "");//10 - 1
        this.telaCadastroCompra.getjTextField_Cliente_Nome().setText(venda.getPessoaFisica().getNome());//10 - 2
        // Não necessário: 3,4,5
        this.telaCadastroCompra.getjTextField_Cliente_Cidade().setText(venda.getPessoaFisica().getEndereco().getBairro().getCidade().getNome());//10 - 6?
        this.telaCadastroCompra.getjTextField_Cliente_Bairro().setText(venda.getPessoaFisica().getEndereco().getBairro().getNome());//10 - 6?
        // Não necessário 7
        this.telaCadastroCompra.getjTextField_Cliente_Tel1().setText(venda.getPessoaFisica().getTelefone1());//10 -8 
        this.telaCadastroCompra.getjTextField_Cliente_Tel2().setText(venda.getPessoaFisica().getTelefone2());//10 - 9
        this.telaCadastroCompra.getjTextField_Cliente_Email().setText(venda.getPessoaFisica().getEmail());//10 - 10 

    }

    private static String semMascara(String dado) {
        //dado = dado.replaceAll("\\.", "");
        dado = dado.replaceAll("\\,", ".");
        return dado;
    }

    private void cancelarFaturamento() {
        venda.removerTodosOsItensDaLista();
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
        venda.setData(formatterData.format(agora));

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        venda.setHora(formatterHora.format(agora));

    }
}

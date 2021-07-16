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
import javax.swing.table.DefaultTableModel;
import model.bo.Estoque;
import model.bo.ItemDeVenda;
import model.bo.Produto;
import view.busca.TelaBuscaPessoaFisica;
import view.busca.TelaBuscaProduto;
import view.TelaFaturamento;
import model.bo.Venda;
import view.busca.TelaBuscaVenda;

public class ControllerVenda implements ActionListener {

    TelaFaturamento telaFaturamento;
    public static int codigo;
    private Venda venda;

    public ControllerVenda(TelaFaturamento telaFaturamento) {

        this.telaFaturamento = telaFaturamento;
        this.venda = new Venda.VendaBuilder().createVenda();

        //BOTÕES
        this.telaFaturamento.getjButton_Novo().addActionListener(this);
        this.telaFaturamento.getjButton_Buscar().addActionListener(this);
        this.telaFaturamento.getjButton_Cancelar().addActionListener(this);
        this.telaFaturamento.getjButton_Gravar().addActionListener(this);
        this.telaFaturamento.getjButton_Sair().addActionListener(this);
        this.telaFaturamento.getjButton_ProdutoPesquisa().addActionListener(this);
        this.telaFaturamento.getjButton_ProdutoAdicionar().addActionListener(this);
        this.telaFaturamento.getjButton_ClienteBuscaId().addActionListener(this);
        this.telaFaturamento.getjButton_ProdutoRemover().addActionListener(this);
        //KEY   
        this.telaFaturamento.getjPanelDados().addKeyListener(new java.awt.event.KeyAdapter() {
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
        if (e.getSource() == this.telaFaturamento.getjButton_Novo()) {
            novoFaturamento();
            // 2 - CANCELAR
        } else if (e.getSource() == this.telaFaturamento.getjButton_Cancelar()) {
            cancelarFaturamento();
            // 3- GRAVAR
        } else if (e.getSource() == this.telaFaturamento.getjButton_Gravar()) {
            if (validarCliente()) {
                gravarFaturamento();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um cliente!");
                this.telaFaturamento.getjTextField_ClienteId().requestFocus();
            }
            // 4- BUSCAR
        } else if (e.getSource() == this.telaFaturamento.getjButton_Buscar()) {

            //codigo = 0;
            TelaBuscaVenda TelaBuscaVenda = new TelaBuscaVenda(null, true);
            ControllerVendaBusca controllerBuscaFaturamento = new ControllerVendaBusca(TelaBuscaVenda);
            TelaBuscaVenda.setVisible(true);

            if (codigo != 0) {
                Ativa(false);
                LimpaEstadoComponentes(true);

                venda = service.ServiceVenda.Buscar(codigo);

                this.telaFaturamento.getjTextField_Faturamento_Id().setText(venda.getId() + "");//1
                this.telaFaturamento.getjFormattedTextField_FaturamentoData().setText(venda.getData());//2
                this.telaFaturamento.getjFormattedTextField_FaturamentoHora().setText(venda.getHora());//3
                this.telaFaturamento.getjTextField_FaturamentoUsuario().setText(venda.getUserCaixa());//4
                this.telaFaturamento.getjFormattedTextField_DataDeVencimento().setText(venda.getDataDeVencimento());//5
                this.telaFaturamento.getjTextArea_Obs().setText(venda.getObservacao());//6
                this.telaFaturamento.getjFormattedTextField_ValorDeDesconto().setText(venda.getValorDoDesconto() + "");//7
                this.telaFaturamento.getjLabel_FaturamentoValorTotal().setText(venda.getValorTotal() + "");//8
                this.telaFaturamento.getjComboBoxStatus().setSelectedItem(venda.getStatus());//9
                //CLIENTE - PESSOA FÍSICA
                this.telaFaturamento.getjTextField_ClienteId().setText(venda.getPessoaFisica().getId() + "");//10 - 1
                this.telaFaturamento.getjTextField_Cliente_Nome().setText(venda.getPessoaFisica().getNome());//10 - 2
                // Não necessário: 3,4,5
                this.telaFaturamento.getjTextField_Cliente_Cidade().setText(venda.getPessoaFisica().getEndereco().getBairro().getCidade().getNome());//10 - 6?
                this.telaFaturamento.getjTextField_Cliente_Bairro().setText(venda.getPessoaFisica().getEndereco().getBairro().getNome());//10 - 6?
                // Não necessário 7
                this.telaFaturamento.getjTextField_Cliente_Tel1().setText(venda.getPessoaFisica().getTelefone1());//10 -8 
                this.telaFaturamento.getjTextField_Cliente_Tel2().setText(venda.getPessoaFisica().getTelefone2());//10 - 9
                this.telaFaturamento.getjComboBox_alunoOuPersonal().setSelectedItem(venda.getPessoaFisica().getTipo());
                this.telaFaturamento.getjTextField_Cliente_Email().setText(venda.getPessoaFisica().getEmail());//10 - 10                
//
                venda.setItensDeVenda(service.ServiceItemDeVenda.BuscarListaDeUmaVenda(venda.getId())); //11
                atualizarTabelaDeItens();
            }
            // 5- PESQUISAR PRODUTO
        } else if (e.getSource() == this.telaFaturamento.getjButton_ProdutoPesquisa()) {
            buscaCodigoDeBarrasDoProduto();
            // 6 - ADICIONAR PRODUTO
        } else if (e.getSource() == this.telaFaturamento.getjButton_ProdutoAdicionar()) {
            if (existeFaturamentoEmAndamento()) {
                if (validarCodigoBarras()) {
                    inserirItem();
                } else {
                    JOptionPane.showMessageDialog(null, "Cód de Barras inválido");
                    this.telaFaturamento.getjFormattedTextField_ProdutoCodBarras().requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não existe um faturamento em andamento");
                this.telaFaturamento.getjComboBoxStatus().requestFocus();
            }
            // 8 - SAIR
        } else if (e.getSource() == this.telaFaturamento.getjButton_Sair()) {
            this.telaFaturamento.dispose();
            // 7 - PESQUISAR CLIENTE
        } else if (e.getSource() == this.telaFaturamento.getjButton_ClienteBuscaId()) {
            if (validarCliente()) {

                if (this.telaFaturamento.getjComboBox_alunoOuPersonal().getSelectedItem().equals("Aluno")) {
                    buscaClienteAluno();
                } else if (this.telaFaturamento.getjComboBox_alunoOuPersonal().getSelectedItem().equals("Personal")) {
                    buscaClientePersonal();
                } else {
                    JOptionPane.showMessageDialog(null, "Escolha Aluno ou Personal");
                    this.telaFaturamento.getjComboBox_alunoOuPersonal().requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Busque um cliente válido!");
            }
            // 6 - REMOVER PRODUTO
        } else if (e.getSource() == this.telaFaturamento.getjButton_ProdutoRemover()) {

            if (existeFaturamentoEmAndamento()) {
                removerItemFaturado();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione 1 item!");
                this.telaFaturamento.getjTable_FaturamentoItens().requestFocus();
            }
        }
    }

    public void Ativa(boolean estadoBotoes) {
        this.telaFaturamento.getjButton_Novo().setEnabled(estadoBotoes);
        this.telaFaturamento.getjButton_Cancelar().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButton_Gravar().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButton_Buscar().setEnabled(estadoBotoes);
        this.telaFaturamento.getjButton_Sair().setEnabled(estadoBotoes);

        this.telaFaturamento.getjFormattedTextField_ProdutoCodBarras().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButton_ProdutoPesquisa().setEnabled(!estadoBotoes);

        this.telaFaturamento.getjButton_ProdutoAdicionar().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButton_ProdutoRemover().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButton_ClienteBuscaId().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjTable_FaturamentoItens().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjComboBoxStatus().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjTextArea_Obs().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjFormattedTextField_ValorDeDesconto().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjFormattedTextField_DataDeVencimento().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjComboBox_alunoOuPersonal().setEnabled(!estadoBotoes);
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

    private void buscaCodigoDeBarrasDoProduto() {

        TelaBuscaProduto telaBuscaProduto = new TelaBuscaProduto(null, true);
        ControllerProdutoBusca controllerBuscaProduto = new ControllerProdutoBusca(telaBuscaProduto);
        telaBuscaProduto.setVisible(true);

        this.telaFaturamento.getjFormattedTextField_ProdutoCodBarras().setText(
                service.ServiceProduto.Buscar(
                        telaBuscaProduto.getCodProduto()
                ).getCodigoDeBarras()
        );
    }

    public void inserirItem() {
        //1º Cria e busca produto pelo Cod Barras
        Produto produto = service.ServiceProduto.Buscar(this.telaFaturamento.getjFormattedTextField_ProdutoCodBarras().getText());
        //2º Adicionar produto e qtd; atribuindo a responsabilidade a venda de criar ItemDeVenda
        venda.adicionarItem(produto);
        //3º atualizar tabela da tela
        atualizarTabelaDeItens();
    }

    public void novoFaturamento() {
        Ativa(false);
        LimpaEstadoComponentes(true);
        codigo = 0;
        this.telaFaturamento.getjComboBoxStatus().setSelectedItem("Faturando");
    }

    public void buscaClienteAluno() {
        TelaBuscaPessoaFisica telaBuscaPessoaFisica = new TelaBuscaPessoaFisica(null, true);
        ControllerPessoaFisicaAlunoBusca controllerBuscaPessoaFisicaAlunoBusca = new ControllerPessoaFisicaAlunoBusca(telaBuscaPessoaFisica);
        telaBuscaPessoaFisica.setVisible(true);

        venda.setPessoaFisica(
                service.ServicePessoaFisica.Buscar(
                        telaBuscaPessoaFisica.getIdDaPessoaFisica())
        );
        this.telaFaturamento.getjTextField_ClienteId().setText(telaBuscaPessoaFisica.getIdDaPessoaFisica() + "");

        //CLIENTE - PESSOA FÍSICA
        this.telaFaturamento.getjTextField_ClienteId().setText(venda.getPessoaFisica().getId() + "");//10 - 1
        this.telaFaturamento.getjTextField_Cliente_Nome().setText(venda.getPessoaFisica().getNome());//10 - 2
        // Não necessário: 3,4,5
        this.telaFaturamento.getjTextField_Cliente_Cidade().setText(venda.getPessoaFisica().getEndereco().getBairro().getCidade().getNome());//10 - 6?
        this.telaFaturamento.getjTextField_Cliente_Bairro().setText(venda.getPessoaFisica().getEndereco().getBairro().getNome());//10 - 6?
        // Não necessário 7
        this.telaFaturamento.getjTextField_Cliente_Tel1().setText(venda.getPessoaFisica().getTelefone1());//10 -8 
        this.telaFaturamento.getjTextField_Cliente_Tel2().setText(venda.getPessoaFisica().getTelefone2());//10 - 9
        this.telaFaturamento.getjComboBox_alunoOuPersonal().setSelectedItem(venda.getPessoaFisica().getTipo());
        this.telaFaturamento.getjTextField_Cliente_Email().setText(venda.getPessoaFisica().getEmail());//10 - 10 

    }

    private boolean validarCodigoBarras() {
        /*
        String codigoDeBarras = semMascara(this.telaFaturamento.getjFormattedTextField_ProdutoCodBarras().getText());

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

        return service.ServiceProduto.codigoDeBarrasValido(this.telaFaturamento.getjFormattedTextField_ProdutoCodBarras().getText());

    }

    private void atualizarTabelaDeItens() {
        DefaultTableModel tabela = (DefaultTableModel) this.telaFaturamento.getjTable_FaturamentoItens().getModel();
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
        this.telaFaturamento.getjTable_FaturamentoItens().setModel(tabela);
        this.telaFaturamento.getjLabel_FaturamentoValorTotal().setText(venda.calcularValorTotal()+ "");
    }

    private void gravarFaturamento() {

        venda.setData(this.telaFaturamento.getjFormattedTextField_FaturamentoData().getText());//2
        venda.setHora(this.telaFaturamento.getjFormattedTextField_FaturamentoHora().getText());//3

        venda.setUserCaixa(this.telaFaturamento.getjTextField_FaturamentoUsuario().getText());//4
        venda.setDataDeVencimento(this.telaFaturamento.getjFormattedTextField_DataDeVencimento().getText());//5
        venda.setObservacao(this.telaFaturamento.getjTextArea_Obs().getText());//6
        venda.setValorDoDesconto(Float.parseFloat(this.telaFaturamento.getjFormattedTextField_ValorDeDesconto().getText()));//7
        venda.setValorTotal(Float.parseFloat(this.telaFaturamento.getjLabel_FaturamentoValorTotal().getText()));//8
        venda.setStatus(this.telaFaturamento.getjComboBoxStatus().getSelectedItem().equals("Faturando"));//9
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
            ItemDeVenda itemDeVenda = service.ServiceItemDeVenda.Buscar(venda.getId());

            Estoque estoque = service.ServiceEstoque.BuscarEstoquePorIdDoProduto(itemDeVenda.getProduto().getId());
            estoque.setQuantidade(
                    estoque.getQuantidade() + itemDeVenda.getQuantidade()
            );
            service.ServiceEstoque.Atualizar(estoque);

            service.ServiceItemDeVenda.Deletar(venda.getId());
            //3ºatualizar os itens c/ idVenda e incluir na tabela de itens de venda no banco          

            for (ItemDeVenda item : venda.getItensDeVenda()) {
                item.setVendaId(venda.getId());
                service.ServiceItemDeVenda.Incluir(item);
            }
        }

        Ativa(true);
        LimpaEstadoComponentes(false);

    }

    private boolean itemDaTabelaEstaSelecionada() {
        return this.telaFaturamento.getjTable_FaturamentoItens().getSelectionModel().isSelectionEmpty();
    }

    private void removerItemFaturado() {

        int numeroDaLinhasSelecionada = this.telaFaturamento.getjTable_FaturamentoItens().getSelectionModel().getSelectionMode();

        //idDaLinhaSelecionada = (int) this.telaFaturamento.getjTable_FaturamentoItens().getValueAt(
        //        this.telaFaturamento.getjTable_FaturamentoItens().getSelectedRow(), 1);
        venda.removerItemDaLista(numeroDaLinhasSelecionada);
        atualizarTabelaDeItens();

    }

    private boolean validarCliente() {
        if (this.telaFaturamento.getjTextField_ClienteId().getText() != "") {
            return true;
        }
        return false;
    }

    private void buscaClientePersonal() {

        TelaBuscaPessoaFisica telaBuscaPessoaFisica = new TelaBuscaPessoaFisica(null, true);
        ControllerPessoaFisicaPersonalBusca controllerBuscaPessoaFisicaPersonalBusca = new ControllerPessoaFisicaPersonalBusca(telaBuscaPessoaFisica);
        telaBuscaPessoaFisica.setVisible(true);

        venda.setPessoaFisica(
                service.ServicePessoaFisica.Buscar(
                        telaBuscaPessoaFisica.getIdDaPessoaFisica())
        );
        this.telaFaturamento.getjTextField_ClienteId().setText(telaBuscaPessoaFisica.getIdDaPessoaFisica() + "");

        //CLIENTE - PESSOA FÍSICA
        this.telaFaturamento.getjTextField_ClienteId().setText(venda.getPessoaFisica().getId() + "");//10 - 1
        this.telaFaturamento.getjTextField_Cliente_Nome().setText(venda.getPessoaFisica().getNome());//10 - 2
        // Não necessário: 3,4,5
        this.telaFaturamento.getjTextField_Cliente_Cidade().setText(venda.getPessoaFisica().getEndereco().getBairro().getCidade().getNome());//10 - 6?
        this.telaFaturamento.getjTextField_Cliente_Bairro().setText(venda.getPessoaFisica().getEndereco().getBairro().getNome());//10 - 6?
        // Não necessário 7
        this.telaFaturamento.getjTextField_Cliente_Tel1().setText(venda.getPessoaFisica().getTelefone1());//10 -8 
        this.telaFaturamento.getjTextField_Cliente_Tel2().setText(venda.getPessoaFisica().getTelefone2());//10 - 9
        this.telaFaturamento.getjComboBox_alunoOuPersonal().setSelectedItem(venda.getPessoaFisica().getTipo());
        this.telaFaturamento.getjTextField_Cliente_Email().setText(venda.getPessoaFisica().getEmail());//10 - 10 

    }

    private static String semMascara(String dado) {
        dado = dado.replaceAll("\\.", "");
        return dado;
    }

    private void cancelarFaturamento() {
        venda.removerTodosOsItensDaLista();
        atualizarTabelaDeItens();
        Ativa(true);
        LimpaEstadoComponentes(false);
    }

    private boolean existeFaturamentoEmAndamento() {
        if (this.telaFaturamento.getjComboBoxStatus().getSelectedItem().equals("Faturando")) {
            return true;
        }
        return false;
    }
}


package view;

import view.cadastro.*;
import view.busca.*;
import controller.*;

public class MenuPrincipal extends javax.swing.JFrame {
    public MenuPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jLabelTitulo1 = new javax.swing.JLabel();
        jLabelTitulo2 = new javax.swing.JLabel();
        jLabelImagem = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCadastros = new javax.swing.JMenu();
        jMenuItemBairros = new javax.swing.JMenuItem();
        jMenuItemCidades = new javax.swing.JMenuItem();
        jMenuItemEnderecos = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemAlunos = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem24 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuMovimentos = new javax.swing.JMenu();
        jMenuItemVendas = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuRelatorios = new javax.swing.JMenu();
        jMenuItemRelatorioBairros = new javax.swing.JMenuItem();
        jMenuItem12RelatorioCidades = new javax.swing.JMenuItem();
        jMenuItemRelatorioCeps = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItemRelatorioAlunos = new javax.swing.JMenuItem();
        jMenuItemRelatorioFornecedoreres = new javax.swing.JMenuItem();
        jMenuItemRelatorioPersonal = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItemRelatorioProdutos = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuCotas = new javax.swing.JMenu();
        jMenuItemContasAReceber = new javax.swing.JMenuItem();
        jMenuItemContasAPagar = new javax.swing.JMenuItem();
        jMenuItemRelatorioCompras = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenuItem8.setText("jMenuItem8");

        jMenuItem15.setText("jMenuItem15");

        jMenuItem23.setText("jMenuItem23");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabelTitulo1.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabelTitulo1.setText("ESPAÇO");

        jLabelTitulo2.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabelTitulo2.setText("ECO FIT");

        jLabelImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/academia.jpg"))); // NOI18N

        jMenuCadastros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo.png"))); // NOI18N
        jMenuCadastros.setText("Cadastros");

        jMenuItemBairros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bairro.png"))); // NOI18N
        jMenuItemBairros.setText("Bairros");
        jMenuItemBairros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBairrosActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemBairros);

        jMenuItemCidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cidade.png"))); // NOI18N
        jMenuItemCidades.setText("Cidades");
        jMenuItemCidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCidadesActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemCidades);

        jMenuItemEnderecos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cep.png"))); // NOI18N
        jMenuItemEnderecos.setText("Endereços");
        jMenuItemEnderecos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEnderecosActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemEnderecos);
        jMenuCadastros.add(jSeparator1);

        jMenuItemAlunos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/aluno.png"))); // NOI18N
        jMenuItemAlunos.setText("Aluno e Personal");
        jMenuItemAlunos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAlunosActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemAlunos);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fornecedor.png"))); // NOI18N
        jMenuItem5.setText("Fornecedores");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItem5);
        jMenuCadastros.add(jSeparator8);

        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produto.png"))); // NOI18N
        jMenuItem24.setText("Produtos");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItem24);
        jMenuCadastros.add(jSeparator2);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sair.png"))); // NOI18N
        jMenuItem7.setText("Sair");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItem7);

        jMenuBar1.add(jMenuCadastros);

        jMenuMovimentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Notes.png"))); // NOI18N
        jMenuMovimentos.setText("Movimentos");

        jMenuItemVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas.png"))); // NOI18N
        jMenuItemVendas.setText("Vendas");
        jMenuItemVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVendasActionPerformed(evt);
            }
        });
        jMenuMovimentos.add(jMenuItemVendas);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/OK.png"))); // NOI18N
        jMenuItem6.setText("Receber");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenuMovimentos.add(jMenuItem6);

        jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mensalidade.png"))); // NOI18N
        jMenuItem22.setText("Gerador Mensalidades");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenuMovimentos.add(jMenuItem22);
        jMenuMovimentos.add(jSeparator6);

        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/compras.png"))); // NOI18N
        jMenuItem21.setText("Compras");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenuMovimentos.add(jMenuItem21);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/No.png"))); // NOI18N
        jMenuItem4.setText("Pagar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenuMovimentos.add(jMenuItem4);
        jMenuMovimentos.add(jSeparator7);

        jMenuBar1.add(jMenuMovimentos);

        jMenuRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mensalidade.png"))); // NOI18N
        jMenuRelatorios.setText("Relatórios");

        jMenuItemRelatorioBairros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bairro.png"))); // NOI18N
        jMenuItemRelatorioBairros.setText("Bairros");
        jMenuItemRelatorioBairros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelatorioBairrosActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelatorioBairros);

        jMenuItem12RelatorioCidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cidade.png"))); // NOI18N
        jMenuItem12RelatorioCidades.setText("Cidades");
        jMenuItem12RelatorioCidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12RelatorioCidadesActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItem12RelatorioCidades);

        jMenuItemRelatorioCeps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cep.png"))); // NOI18N
        jMenuItemRelatorioCeps.setText("Endereços");
        jMenuItemRelatorioCeps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelatorioCepsActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelatorioCeps);
        jMenuRelatorios.add(jSeparator3);

        jMenuItemRelatorioAlunos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/aluno.png"))); // NOI18N
        jMenuItemRelatorioAlunos.setText("Alunos");
        jMenuItemRelatorioAlunos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelatorioAlunosActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelatorioAlunos);

        jMenuItemRelatorioFornecedoreres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fornecedor.png"))); // NOI18N
        jMenuItemRelatorioFornecedoreres.setText("Fornecedores");
        jMenuItemRelatorioFornecedoreres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelatorioFornecedoreresActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelatorioFornecedoreres);

        jMenuItemRelatorioPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pessoal.png"))); // NOI18N
        jMenuItemRelatorioPersonal.setText("Personal");
        jMenuItemRelatorioPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelatorioPersonalActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelatorioPersonal);
        jMenuRelatorios.add(jSeparator5);

        jMenuItemRelatorioProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produto.png"))); // NOI18N
        jMenuItemRelatorioProdutos.setText("Produtos");
        jMenuItemRelatorioProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelatorioProdutosActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelatorioProdutos);
        jMenuRelatorios.add(jSeparator4);

        jMenuCotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/contas.png"))); // NOI18N
        jMenuCotas.setText("Contas");

        jMenuItemContasAReceber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/contas.png"))); // NOI18N
        jMenuItemContasAReceber.setText("Contas à Receber");
        jMenuItemContasAReceber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContasAReceberActionPerformed(evt);
            }
        });
        jMenuCotas.add(jMenuItemContasAReceber);

        jMenuItemContasAPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/contas.png"))); // NOI18N
        jMenuItemContasAPagar.setText("Contas à Pagar");
        jMenuItemContasAPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContasAPagarActionPerformed(evt);
            }
        });
        jMenuCotas.add(jMenuItemContasAPagar);

        jMenuRelatorios.add(jMenuCotas);

        jMenuItemRelatorioCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/compras.png"))); // NOI18N
        jMenuItemRelatorioCompras.setText("Compras");
        jMenuItemRelatorioCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelatorioComprasActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelatorioCompras);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vendas.png"))); // NOI18N
        jMenuItem1.setText("Vendas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItem1);

        jMenuBar1.add(jMenuRelatorios);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addComponent(jLabelImagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabelTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(237, Short.MAX_VALUE)
                .addComponent(jLabelImagem)
                .addGap(143, 143, 143))
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jLabelTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelTitulo2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVendasActionPerformed
        TelaCadastroVenda telaCadastroVenda = new TelaCadastroVenda();
        ControllerVenda controllerVenda = new ControllerVenda(telaCadastroVenda);
        telaCadastroVenda.setVisible(true);
    }//GEN-LAST:event_jMenuItemVendasActionPerformed

    private void jMenuItemCidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCidadesActionPerformed
        TelaCadastroCidade telaCadastroCidade = new TelaCadastroCidade();
        ControllerCidade controllerCidade = new ControllerCidade(telaCadastroCidade);
        telaCadastroCidade.setVisible(true);
    }//GEN-LAST:event_jMenuItemCidadesActionPerformed

    private void jMenuItemBairrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBairrosActionPerformed
        TelaCadastroBairro telaCadastroBairro = new TelaCadastroBairro();
        ControllerBairro controllerBairro = new ControllerBairro(telaCadastroBairro);
        telaCadastroBairro.setVisible(true);
    }//GEN-LAST:event_jMenuItemBairrosActionPerformed

    private void jMenuItemAlunosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAlunosActionPerformed
        TelaCadastroPessoaFisica telaCadastroPessoaFisica = new TelaCadastroPessoaFisica();
        ControllerPessoaFisica controllerPessoaFisica = new ControllerPessoaFisica(telaCadastroPessoaFisica);
        telaCadastroPessoaFisica.setVisible(true);
    }//GEN-LAST:event_jMenuItemAlunosActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        TelaCadastroFornecedor telaCadastroFornecedor = new TelaCadastroFornecedor();
        ControllerFornecedor controllerFornecedor = new ControllerFornecedor(telaCadastroFornecedor);
        telaCadastroFornecedor.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        TelaCadastroProduto telaCadastroProduto = new TelaCadastroProduto();
        ControllerProduto controllerProduto = new ControllerProduto(telaCadastroProduto);
        telaCadastroProduto.setVisible(true);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItemRelatorioBairrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelatorioBairrosActionPerformed
        TelaBuscaBairro telaBuscaBairro = new TelaBuscaBairro();
        ControllerBairroBusca controllerBuscaBairroRelatorio = new ControllerBairroBusca(telaBuscaBairro);
        telaBuscaBairro.setVisible(true);

    }//GEN-LAST:event_jMenuItemRelatorioBairrosActionPerformed

    private void jMenuItem12RelatorioCidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12RelatorioCidadesActionPerformed
        TelaBuscaCidade telaBuscaCidade = new TelaBuscaCidade();
        ControllerCidadeBusca controllerBuscaCidade = new ControllerCidadeBusca(telaBuscaCidade);
        telaBuscaCidade.setVisible(true);
    }//GEN-LAST:event_jMenuItem12RelatorioCidadesActionPerformed

    private void jMenuItemRelatorioCepsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelatorioCepsActionPerformed
        TelaBuscaEndereco telaBuscaEndereco = new TelaBuscaEndereco();
        ControllerEnderecoBusca controllerBuscaEndereco = new ControllerEnderecoBusca(telaBuscaEndereco);
        telaBuscaEndereco.setVisible(true);

    }//GEN-LAST:event_jMenuItemRelatorioCepsActionPerformed

    private void jMenuItemRelatorioAlunosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelatorioAlunosActionPerformed
        TelaBuscaPessoaFisica telaBuscaPessoaFisica = new TelaBuscaPessoaFisica();
        ControllerPessoaFisicaAlunoBusca controllerBuscaPessoaFisicaAluno = new ControllerPessoaFisicaAlunoBusca(telaBuscaPessoaFisica);
        telaBuscaPessoaFisica.setVisible(true);
    }//GEN-LAST:event_jMenuItemRelatorioAlunosActionPerformed

    private void jMenuItemRelatorioFornecedoreresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelatorioFornecedoreresActionPerformed
        TelaBuscaFornecedor telaBuscaFornecedor = new TelaBuscaFornecedor();
        ControllerFornecedorBusca controllerBuscaFornecedor = new ControllerFornecedorBusca(telaBuscaFornecedor);
        telaBuscaFornecedor.setVisible(true);
    }//GEN-LAST:event_jMenuItemRelatorioFornecedoreresActionPerformed

    private void jMenuItemRelatorioPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelatorioPersonalActionPerformed
        TelaBuscaPessoaFisica telaBuscaPessoaFisica = new TelaBuscaPessoaFisica();
        ControllerPessoaFisicaPersonalBusca controllerBuscaPessoaFisicaPersonal = new ControllerPessoaFisicaPersonalBusca(telaBuscaPessoaFisica);
        telaBuscaPessoaFisica.setVisible(true);
    }//GEN-LAST:event_jMenuItemRelatorioPersonalActionPerformed

    private void jMenuItemRelatorioProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelatorioProdutosActionPerformed

        TelaBuscaProduto telaBuscaProduto = new TelaBuscaProduto();
        ControllerProdutoBusca controllerBuscaProduto = new ControllerProdutoBusca(telaBuscaProduto);
        telaBuscaProduto.setVisible(true);
    }//GEN-LAST:event_jMenuItemRelatorioProdutosActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItemRelatorioComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelatorioComprasActionPerformed
        TelaBuscaCompra telaBuscaCompra = new TelaBuscaCompra();
        ControllerCompraBusca controllerCompraBusca = new ControllerCompraBusca(telaBuscaCompra);
        telaBuscaCompra.setVisible(true);
    }//GEN-LAST:event_jMenuItemRelatorioComprasActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        TelaCadastroCompra telaCadastroCompra = new TelaCadastroCompra();
        ControllerCompra controllerCompra = new ControllerCompra(telaCadastroCompra);
        telaCadastroCompra.setVisible(true);        
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItemContasAPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContasAPagarActionPerformed
       TelaBuscaContaAPagar telaBuscaContaAPagar = new TelaBuscaContaAPagar(null, true);
        ControllerContasAPagarBusca controllerContasAPagarBusca = new ControllerContasAPagarBusca(telaBuscaContaAPagar);
        telaBuscaContaAPagar.setVisible(true);
    }//GEN-LAST:event_jMenuItemContasAPagarActionPerformed

    private void jMenuItemContasAReceberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContasAReceberActionPerformed
        TelaBuscaContaAReceber telaBuscaContaAReceber = new TelaBuscaContaAReceber(null, true);
        ControllerContasAReceberBusca controllerContasAReceberBusca = new ControllerContasAReceberBusca(telaBuscaContaAReceber);
        telaBuscaContaAReceber.setVisible(true);  
        
    }//GEN-LAST:event_jMenuItemContasAReceberActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        //TelaBuscaVenda telaBuscaVenda = new TelaBuscaVenda();
        //ControllerBuscaVenda controllerBuscaVenda = new ControllerBuscaVenda(telaBuscaVenda);
        //telaBuscaVenda.setVisible(true);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItemEnderecosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEnderecosActionPerformed
        TelaCadastroEndereco telaCadastroEndereco = new TelaCadastroEndereco();
        ControllerEndereco controllerEndereco = new ControllerEndereco(telaCadastroEndereco);
        telaCadastroEndereco.setVisible(true);
    }//GEN-LAST:event_jMenuItemEnderecosActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        TelaBuscaVenda telaBuscaVenda = new TelaBuscaVenda();
        ControllerVendaBusca controllerVendaBusca = new controller.ControllerVendaBusca(telaBuscaVenda);
        telaBuscaVenda.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        TelaCadastroReceber telaCadastroReceber = new TelaCadastroReceber();
        ControllerRecebebimento controllerReceber = new ControllerRecebebimento(telaCadastroReceber);
        telaCadastroReceber.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        TelaCadastroPagamento telaCadastroPagar = new TelaCadastroPagamento();
        ControllerPagamentos controllerPagar = new ControllerPagamentos(telaCadastroPagar);
        telaCadastroPagar.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabelImagem;
    private javax.swing.JLabel jLabelTitulo1;
    private javax.swing.JLabel jLabelTitulo2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCadastros;
    private javax.swing.JMenu jMenuCotas;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem12RelatorioCidades;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItemAlunos;
    private javax.swing.JMenuItem jMenuItemBairros;
    private javax.swing.JMenuItem jMenuItemCidades;
    private javax.swing.JMenuItem jMenuItemContasAPagar;
    private javax.swing.JMenuItem jMenuItemContasAReceber;
    private javax.swing.JMenuItem jMenuItemEnderecos;
    private javax.swing.JMenuItem jMenuItemRelatorioAlunos;
    private javax.swing.JMenuItem jMenuItemRelatorioBairros;
    private javax.swing.JMenuItem jMenuItemRelatorioCeps;
    private javax.swing.JMenuItem jMenuItemRelatorioCompras;
    private javax.swing.JMenuItem jMenuItemRelatorioFornecedoreres;
    private javax.swing.JMenuItem jMenuItemRelatorioPersonal;
    private javax.swing.JMenuItem jMenuItemRelatorioProdutos;
    private javax.swing.JMenuItem jMenuItemVendas;
    private javax.swing.JMenu jMenuMovimentos;
    private javax.swing.JMenu jMenuRelatorios;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    // End of variables declaration//GEN-END:variables

}

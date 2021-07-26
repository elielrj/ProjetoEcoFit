package view.cadastro;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TelaCadastroVenda extends javax.swing.JFrame {

    public TelaCadastroVenda() {
        initComponents();

        getjComboBoxStatus().addItem("Faturando");
        getjComboBoxStatus().addItem("Faturado");

        jComboBox_alunoOuPersonal.addItem("");
        jComboBox_alunoOuPersonal.addItem("Aluno");
        jComboBox_alunoOuPersonal.addItem("Personal");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTitulo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jPainelBotoes = new javax.swing.JPanel();
        jButtonNovo = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonGravar = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jPanelDados = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonProdutoPesquisa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFaturamentoItens = new javax.swing.JTable();
        jLabelFaturamentoValorTotal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldFaturamentoUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextFieldFaturamentoData = new javax.swing.JFormattedTextField();
        jFormattedTextFieldFaturamentoHora = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldClienteId = new javax.swing.JTextField();
        jButton_ClienteBuscaId = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldClienteNome = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldClienteCidade = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldClienteBairro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldClienteEmail = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabelF1 = new javax.swing.JLabel();
        jLabelF2 = new javax.swing.JLabel();
        jLabelF3 = new javax.swing.JLabel();
        jLabelF4 = new javax.swing.JLabel();
        jLabelF5 = new javax.swing.JLabel();
        jTextFieldClienteTel1 = new javax.swing.JTextField();
        jTextFieldClienteTel2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonProdutoAdicionar = new javax.swing.JButton();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jButton_ProdutoRemover = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_Obs = new javax.swing.JTextArea();
        jFormattedTextField_ValorDeDesconto = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jFormattedTextField_DataDeVencimento = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField_Faturamento_Id = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jComboBox_alunoOuPersonal = new javax.swing.JComboBox<>();
        jFormattedTextField_ProdutoCodBarras = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de Cadastro de ...");
        setPreferredSize(new java.awt.Dimension(920, 700));

        jPanelTitulo.setBackground(new java.awt.Color(204, 255, 0));
        jPanelTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelTitulo.setPreferredSize(new java.awt.Dimension(533, 75));

        jLabelTitulo.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(51, 0, 153));
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("VENDA A CLIENTES - ALUNO & PERSONAL");
        jLabelTitulo.setPreferredSize(new java.awt.Dimension(533, 50));
        jLabelTitulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabelTituloKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTituloLayout = new javax.swing.GroupLayout(jPanelTitulo);
        jPanelTitulo.setLayout(jPanelTituloLayout);
        jPanelTituloLayout.setHorizontalGroup(
            jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
        );
        jPanelTituloLayout.setVerticalGroup(
            jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelTitulo, java.awt.BorderLayout.NORTH);

        jPainelBotoes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPainelBotoes.setMinimumSize(new java.awt.Dimension(347, 37));
        jPainelBotoes.setPreferredSize(new java.awt.Dimension(533, 50));

        jButtonNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo.png"))); // NOI18N
        jButtonNovo.setText("Novo");
        jButtonNovo.setPreferredSize(new java.awt.Dimension(90, 30));
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });
        jPainelBotoes.add(jButtonNovo);

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setEnabled(false);
        jButtonCancelar.setIconTextGap(3);
        jButtonCancelar.setMaximumSize(new java.awt.Dimension(91, 30));
        jButtonCancelar.setMinimumSize(new java.awt.Dimension(91, 24));
        jButtonCancelar.setPreferredSize(new java.awt.Dimension(90, 30));
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPainelBotoes.add(jButtonCancelar);

        jButtonGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/gravar.png"))); // NOI18N
        jButtonGravar.setText("Gravar");
        jButtonGravar.setEnabled(false);
        jButtonGravar.setPreferredSize(new java.awt.Dimension(90, 30));
        jPainelBotoes.add(jButtonGravar);

        jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar.png"))); // NOI18N
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.setIconTextGap(3);
        jButtonBuscar.setPreferredSize(new java.awt.Dimension(90, 30));
        jPainelBotoes.add(jButtonBuscar);

        jButtonSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sair.png"))); // NOI18N
        jButtonSair.setText("Sair");
        jButtonSair.setIconTextGap(3);
        jButtonSair.setMaximumSize(new java.awt.Dimension(5, 5));
        jButtonSair.setPreferredSize(new java.awt.Dimension(90, 30));
        jPainelBotoes.add(jButtonSair);

        getContentPane().add(jPainelBotoes, java.awt.BorderLayout.SOUTH);

        jPanelDados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelDados.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanelDados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanelDadosKeyPressed(evt);
            }
        });

        jLabel1.setText("Código de barras do produto");

        jButtonProdutoPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/buscar.png"))); // NOI18N
        jButtonProdutoPesquisa.setText("Pesquisa Produto");
        jButtonProdutoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProdutoPesquisaActionPerformed(evt);
            }
        });

        jTableFaturamentoItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Id", "Produto", "Qtd", "Valor", "SubTotal"
            }
        ));
        jScrollPane1.setViewportView(jTableFaturamentoItens);
        if (jTableFaturamentoItens.getColumnModel().getColumnCount() > 0) {
            jTableFaturamentoItens.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableFaturamentoItens.getColumnModel().getColumn(1).setMaxWidth(50);
            jTableFaturamentoItens.getColumnModel().getColumn(3).setMaxWidth(50);
            jTableFaturamentoItens.getColumnModel().getColumn(4).setMaxWidth(50);
            jTableFaturamentoItens.getColumnModel().getColumn(5).setMaxWidth(60);
        }

        jLabelFaturamentoValorTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelFaturamentoValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelFaturamentoValorTotal.setText("000000");

        jLabel3.setText("Data Da Venda");

        jLabel4.setText("Usuário");

        jTextFieldFaturamentoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFaturamentoUsuarioActionPerformed(evt);
            }
        });

        jLabel5.setText("Hora:");

        try {
            jFormattedTextFieldFaturamentoData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldFaturamentoData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextFieldFaturamentoData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldFaturamentoDataActionPerformed(evt);
            }
        });

        try {
            jFormattedTextFieldFaturamentoHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldFaturamentoHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setText("Id Cliente");

        jTextFieldClienteId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldClienteId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClienteIdActionPerformed(evt);
            }
        });

        jButton_ClienteBuscaId.setText("...");
        jButton_ClienteBuscaId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ClienteBuscaIdActionPerformed(evt);
            }
        });

        jLabel7.setText("Nome:");

        jLabel8.setText("Ciddae");

        jTextFieldClienteCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClienteCidadeActionPerformed(evt);
            }
        });

        jLabel9.setText("Bairro");

        jTextFieldClienteBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClienteBairroActionPerformed(evt);
            }
        });

        jLabel10.setText("Email");

        jLabel11.setText("Telefone 1");
        jLabel11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel11KeyPressed(evt);
            }
        });

        jLabelF1.setText("F1 = Buscar produto");
        jLabelF1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabelF1KeyPressed(evt);
            }
        });

        jLabelF2.setText("F2 = Novo Faturamento");

        jLabelF3.setText("F3 = Cancelar Faturamento");

        jLabelF4.setText("F4 = Encerrar faturamento");

        jLabelF5.setText("F5 = Cancelar item Faturado");

        jLabel2.setText("Telefone 2");

        jButtonProdutoAdicionar.setText("Adicionar");
        jButtonProdutoAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProdutoAdicionarActionPerformed(evt);
            }
        });

        jComboBoxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStatusActionPerformed(evt);
            }
        });

        jLabel12.setText("Status");

        jButton_ProdutoRemover.setText("Remover");

        jTextArea_Obs.setColumns(20);
        jTextArea_Obs.setRows(5);
        jScrollPane2.setViewportView(jTextArea_Obs);

        jFormattedTextField_ValorDeDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel13.setText("Observação");

        jLabel14.setText("Valor do Desconto");

        try {
            jFormattedTextField_DataDeVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_DataDeVencimento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField_DataDeVencimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField_DataDeVencimentoActionPerformed(evt);
            }
        });

        jLabel15.setText("Data Vencimento");

        jLabel16.setText("Id Faturamento");

        try {
            jFormattedTextField_ProdutoCodBarras.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###.##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_ProdutoCodBarras.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                                .addComponent(jFormattedTextField_ProdutoCodBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonProdutoPesquisa))
                                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jTextFieldClienteId, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(jLabel16))
                                                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                                                        .addComponent(jComboBox_alunoOuPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jButton_ClienteBuscaId)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jTextField_Faturamento_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButtonProdutoAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton_ProdutoRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createSequentialGroup()
                                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jTextFieldClienteTel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(30, 30, 30)
                                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldClienteTel2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)))
                                .addComponent(jTextFieldClienteEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldClienteCidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldClienteBairro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldClienteNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelFaturamentoValorTotal)
                        .addGap(19, 19, 19))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3)
                                        .addComponent(jFormattedTextFieldFaturamentoData, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jFormattedTextField_DataDeVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(67, 67, 67)
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextFieldFaturamentoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jFormattedTextFieldFaturamentoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(77, 77, 77)
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12)
                                    .addComponent(jComboBoxStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField_ValorDeDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))))
                        .addContainerGap())))
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabelF1)
                .addGap(30, 30, 30)
                .addComponent(jLabelF2)
                .addGap(28, 28, 28)
                .addComponent(jLabelF3)
                .addGap(29, 29, 29)
                .addComponent(jLabelF4)
                .addGap(34, 34, 34)
                .addComponent(jLabelF5)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelDadosLayout.setVerticalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonProdutoPesquisa)
                            .addComponent(jButtonProdutoAdicionar)
                            .addComponent(jFormattedTextField_ProdutoCodBarras))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton_ProdutoRemover)
                                .addComponent(jLabel16))
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(7, 7, 7)
                                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextFieldClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox_alunoOuPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton_ClienteBuscaId)))
                                    .addComponent(jTextField_Faturamento_Id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(4, 4, 4)
                                        .addComponent(jTextFieldClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(53, 53, 53))
                                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel8)
                                        .addGap(3, 3, 3)
                                        .addComponent(jTextFieldClienteCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel9)
                                .addGap(12, 12, 12)
                                .addComponent(jTextFieldClienteBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldClienteEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldClienteTel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldClienteTel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFaturamentoValorTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldFaturamentoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldFaturamentoHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField_ValorDeDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel4)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField_DataDeVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldFaturamentoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelF1)
                    .addComponent(jLabelF2)
                    .addComponent(jLabelF3)
                    .addComponent(jLabelF4)
                    .addComponent(jLabelF5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        getContentPane().add(jPanelDados, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonProdutoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProdutoPesquisaActionPerformed
        //TelaBuscaProduto telaBuscaProduto = new TelaBuscaProduto(null,true);
        //ControllerBuscaProduto listaDeProdutos = new ControllerProdutoBusca(telaBuscaProduto);
        //telaBuscaProduto.setVisible(true);
        //getjTextFieldProdutoCodBarras().setText(telaBuscaProduto.getCodProduto()+"");
    }//GEN-LAST:event_jButtonProdutoPesquisaActionPerformed

    private void jTextFieldFaturamentoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFaturamentoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFaturamentoUsuarioActionPerformed

    private void jFormattedTextFieldFaturamentoDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldFaturamentoDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldFaturamentoDataActionPerformed

    private void jTextFieldClienteIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClienteIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClienteIdActionPerformed

    private void jTextFieldClienteCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClienteCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClienteCidadeActionPerformed

    private void jTextFieldClienteBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClienteBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClienteBairroActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButton_ClienteBuscaIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ClienteBuscaIdActionPerformed

    }//GEN-LAST:event_jButton_ClienteBuscaIdActionPerformed

    private void jButtonProdutoAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProdutoAdicionarActionPerformed
        //
    }//GEN-LAST:event_jButtonProdutoAdicionarActionPerformed

    public JPanel getjPanelDados() {
        return jPanelDados;
    }

    public JButton getjButton_ProdutoPesquisa() {
        return jButtonProdutoPesquisa;
    }

    private void jLabelF1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelF1KeyPressed

    }//GEN-LAST:event_jLabelF1KeyPressed

    private void jLabelTituloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelTituloKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelTituloKeyPressed

    public JComboBox<Object> getjComboBoxStatus() {
        if (jComboBoxStatus.getSelectedItem() == "Faturando") {
            jComboBoxStatus.setSelectedItem("Faturando");
        } else if (jComboBoxStatus.getSelectedItem() == "Faturado") {
            jComboBoxStatus.setSelectedItem("Faturado");
        }
        return jComboBoxStatus;
    }

    private void jComboBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStatusActionPerformed
        //
    }//GEN-LAST:event_jComboBoxStatusActionPerformed

    private void jLabel11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel11KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel11KeyPressed

    private void jPanelDadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanelDadosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelDadosKeyPressed

    private void jFormattedTextField_DataDeVencimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField_DataDeVencimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField_DataDeVencimentoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*jButtonNovoe and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGravar;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonProdutoAdicionar;
    private javax.swing.JButton jButtonProdutoPesquisa;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JButton jButton_ClienteBuscaId;
    private javax.swing.JButton jButton_ProdutoRemover;
    private javax.swing.JComboBox<Object> jComboBoxStatus;
    private javax.swing.JComboBox<Object> jComboBox_alunoOuPersonal;
    private javax.swing.JFormattedTextField jFormattedTextFieldFaturamentoData;
    private javax.swing.JFormattedTextField jFormattedTextFieldFaturamentoHora;
    private javax.swing.JFormattedTextField jFormattedTextField_DataDeVencimento;
    private javax.swing.JFormattedTextField jFormattedTextField_ProdutoCodBarras;
    private javax.swing.JFormattedTextField jFormattedTextField_ValorDeDesconto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelF1;
    private javax.swing.JLabel jLabelF2;
    private javax.swing.JLabel jLabelF3;
    private javax.swing.JLabel jLabelF4;
    private javax.swing.JLabel jLabelF5;
    private javax.swing.JLabel jLabelFaturamentoValorTotal;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPainelBotoes;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableFaturamentoItens;
    private javax.swing.JTextArea jTextArea_Obs;
    private javax.swing.JTextField jTextFieldClienteBairro;
    private javax.swing.JTextField jTextFieldClienteCidade;
    private javax.swing.JTextField jTextFieldClienteEmail;
    private javax.swing.JTextField jTextFieldClienteId;
    private javax.swing.JTextField jTextFieldClienteNome;
    private javax.swing.JTextField jTextFieldClienteTel1;
    private javax.swing.JTextField jTextFieldClienteTel2;
    private javax.swing.JTextField jTextFieldFaturamentoUsuario;
    private javax.swing.JTextField jTextField_Faturamento_Id;
    // End of variables declaration//GEN-END:variables

    

    
    public JTextField getjTextField_ClienteId() {
        return jTextFieldClienteId;
    }

    public JTextField getjTextField_Cliente_Bairro() {
        return jTextFieldClienteBairro;
    }

    public JTextField getjTextField_Cliente_Cidade() {
        return jTextFieldClienteCidade;
    }

    public JTextField getjTextField_Cliente_Email() {
        return jTextFieldClienteEmail;
    }

    public JTextField getjTextField_Cliente_Nome() {
        return jTextFieldClienteNome;
    }

    public JTextField getjTextField_Cliente_Tel1() {
        return jTextFieldClienteTel1;
    }

    public JTextField getjTextField_Cliente_Tel2() {
        return jTextFieldClienteTel2;
    }

    public JFormattedTextField getjFormattedTextField_FaturamentoData() {
        return jFormattedTextFieldFaturamentoData;
    }

    public JFormattedTextField getjFormattedTextField_FaturamentoHora() {
        return jFormattedTextFieldFaturamentoHora;
    }

    public JTextField getjTextField_FaturamentoUsuario() {
        return jTextFieldFaturamentoUsuario;
    }

    public void setjFormattedTextField_FaturamentoData(String data) {
        this.jFormattedTextFieldFaturamentoData.setText(data);
    }

    public JLabel getjLabel_FaturamentoValorTotal() {
        return jLabelFaturamentoValorTotal;
    }

    public JButton getjButton_ProdutoAdicionar() {
        return jButtonProdutoAdicionar;
    }

    public JTable getjTable_FaturamentoItens() {
        return jTableFaturamentoItens;
    }

    public JButton getjButton_Buscar() {
        return jButtonBuscar;
    }

    public JFormattedTextField getjFormattedTextField_ProdutoCodBarras() {
        return jFormattedTextField_ProdutoCodBarras;
    }

    public JButton getjButton_Cancelar() {
        return jButtonCancelar;
    }

    public JButton getjButton_ClienteBuscaId() {
        return jButton_ClienteBuscaId;
    }

    public JButton getjButton_Gravar() {
        return jButtonGravar;
    }

    public JButton getjButton_Novo() {
        return jButtonNovo;
    }

    public JButton getjButton_Sair() {
        return jButtonSair;
    }

    public JLabel getjLabel_F1() {
        return jLabelF1;
    }

    public JLabel getjLabel_F2() {
        return jLabelF2;
    }

    public JLabel getjLabelF3() {
        return jLabelF3;
    }

    public JLabel getjLabel_F4() {
        return jLabelF4;
    }

    public JLabel getjLabel_F5() {
        return jLabelF5;
    }


    public JButton getjButton_ProdutoRemover() {
        return jButton_ProdutoRemover;
    }

    public JTextArea getjTextArea_Obs() {
        return jTextArea_Obs;
    }

    public JFormattedTextField getjFormattedTextField_ValorDeDesconto() {
        return jFormattedTextField_ValorDeDesconto;
    }

    public JFormattedTextField getjFormattedTextField_DataDeVencimento() {
        return jFormattedTextField_DataDeVencimento;
    }

    public JTextField getjTextField_Faturamento_Id() {
        return jTextField_Faturamento_Id;
    }

    public JComboBox<Object> getjComboBox_alunoOuPersonal() {
        return jComboBox_alunoOuPersonal;
    }

    
}

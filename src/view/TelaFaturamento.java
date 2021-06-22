
package view;


import controller.ControllerBuscaPessoaFisica;
import controller.ControllerBuscaProduto;
import java.awt.event.KeyEvent;
import java.text.DateFormat;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.ParseConversionEvent;
import model.bo.ItemDeVenda;
import model.bo.PessoaFisica;
import model.bo.Produto;


    
public class TelaFaturamento extends javax.swing.JFrame{
    
    private String hora;
    private String data;
    private String usuario;
    private List<ItemDeVenda> listaDeItens;
    private DefaultTableModel tabela;
    private int contador;

    public DefaultTableModel getTabela() {
        return tabela;
    }
    
    public TelaFaturamento() {
        initComponents();
        this.listaDeItens = new ArrayList<>();
        dataHora();        
        caixaAtual();
        this.tabela = (DefaultTableModel) getjTableFaturamentoItens().getModel();
        this.contador = 1;
        getjComboBoxStatus().addItem("Faturando");
        getjComboBoxStatus().addItem("Faturado");
    }

    public List<ItemDeVenda> getListaDeItens() {
        return listaDeItens;
    }

    public void setListaDeItens(List<ItemDeVenda> listaDeItens) {
        this.listaDeItens = listaDeItens;
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
        jTextFieldProdutoCodBarras = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButtonProdutoBusca = new javax.swing.JButton();
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
        jButtonClienteBuscaId = new javax.swing.JButton();
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
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldClienteTel1 = new javax.swing.JTextField();
        jTextFieldClienteTel2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonAdicionar = new javax.swing.JButton();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Formulário de Cadastro de ...");
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jPanelTitulo.setBackground(new java.awt.Color(204, 255, 0));
        jPanelTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelTitulo.setPreferredSize(new java.awt.Dimension(533, 75));

        jLabelTitulo.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(51, 0, 153));
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Faturamento");
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
            .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
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

        jTextFieldProdutoCodBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldProdutoCodBarrasActionPerformed(evt);
            }
        });

        jLabel1.setText("Código de barras do produto");

        jButtonProdutoBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/buscar.png"))); // NOI18N
        jButtonProdutoBusca.setText("Pesquisa Produto");
        jButtonProdutoBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProdutoBuscaActionPerformed(evt);
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

        jLabelFaturamentoValorTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelFaturamentoValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelFaturamentoValorTotal.setText("000000");

        jLabel3.setText("Data");

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

        jFormattedTextFieldFaturamentoHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));

        jLabel6.setText("Id Cliente");

        jTextFieldClienteId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClienteIdActionPerformed(evt);
            }
        });

        jButtonClienteBuscaId.setText("...");
        jButtonClienteBuscaId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClienteBuscaIdActionPerformed(evt);
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

        jLabelF1.setText("F1 = Buscar produto");
        jLabelF1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabelF1KeyPressed(evt);
            }
        });

        jLabel13.setText("F2 = Novo Faturamento");

        jLabel14.setText("F3 = Cancelar Faturamento");

        jLabel15.setText("F4 = Encerrar faturamento");

        jLabel16.setText("F5 = Cancelar item Faturado");

        jLabel2.setText("Telefone 2");

        jButtonAdicionar.setText("Adicionar");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        jComboBoxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStatusActionPerformed(evt);
            }
        });

        jLabel12.setText("Status");

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanelDadosLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(jPanelDadosLayout.createSequentialGroup()
                                            .addComponent(jTextFieldProdutoCodBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButtonProdutoBusca)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButtonAdicionar)))
                                    .addGap(27, 27, 27))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createSequentialGroup()
                                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextFieldClienteId, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(18, 18, 18)
                                    .addComponent(jButtonClienteBuscaId)))
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldFaturamentoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(18, 18, 18)
                                                .addComponent(jFormattedTextFieldFaturamentoData, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addComponent(jFormattedTextFieldFaturamentoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(jLabel12)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabelFaturamentoValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBoxStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)))
                        .addGap(89, 89, 89))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelF1)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel13)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel14)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel15)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanelDadosLayout.setVerticalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldProdutoCodBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonProdutoBusca)
                            .addComponent(jButtonAdicionar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(7, 7, 7)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonClienteBuscaId))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(jTextFieldClienteTel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelF1)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jFormattedTextFieldFaturamentoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelFaturamentoValorTotal, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextFieldFaturamentoHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldFaturamentoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelDados, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonProdutoBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProdutoBuscaActionPerformed
        //TelaBuscaProduto telaBuscaProduto = new TelaBuscaProduto(null,true);
        //ControllerBuscaProduto listaDeProdutos = new ControllerBuscaProduto(telaBuscaProduto);
        //telaBuscaProduto.setVisible(true);
        //getjTextFieldProdutoCodBarras().setText(telaBuscaProduto.getCodProduto()+"");
    }//GEN-LAST:event_jButtonProdutoBuscaActionPerformed

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

    private void jTextFieldProdutoCodBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldProdutoCodBarrasActionPerformed
        
    }//GEN-LAST:event_jTextFieldProdutoCodBarrasActionPerformed

    private void jButtonClienteBuscaIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClienteBuscaIdActionPerformed
        /*TelaBuscaPessoaFisica telaBuscaPessoaFisica = new TelaBuscaPessoaFisica(null,true);
        ControllerBuscaPessoaFisica controllerBuscaPessoaFisica = new ControllerBuscaPessoaFisica(telaBuscaPessoaFisica);
        telaBuscaPessoaFisica.setVisible(true);
        getjTextFieldClienteId().setText(telaBuscaPessoaFisica.getCodPessoaFisica()+"");
        PessoaFisica pessoaFisica = service.ServicePessoaFisica.Buscar(telaBuscaPessoaFisica.getCodPessoaFisica());
        getjTextFieldClienteNome().setText(pessoaFisica.getNome());
        getjTextFieldClienteCidade().setText(pessoaFisica.getEndereco().getCidade()+"");
        getjTextFieldClienteBairro().setText(pessoaFisica.getEndereco().getBairro()+"");
        getjTextFieldClienteEmail().setText(pessoaFisica.getEmail()+"");
        getjTextFieldClienteTel1().setText(pessoaFisica.getTelefone1()+"");
        getjTextFieldClienteTel2().setText(pessoaFisica.getTelefone2()+"");*/
    }//GEN-LAST:event_jButtonClienteBuscaIdActionPerformed

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        /*if(!getjTextFieldProdutoCodBarras().getText().equals("")){
            criarProdutoEAdicionar();
        }else{
            getjTextFieldProdutoCodBarras().requestFocus();
        }*/
    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    public JButton getjButtonProdutoBusca() {
        return jButtonProdutoBusca;
    }

    private void jLabelF1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelF1KeyPressed
        
    }//GEN-LAST:event_jLabelF1KeyPressed

    private void jLabelTituloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelTituloKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelTituloKeyPressed

    public JComboBox<Object> getjComboBoxStatus() {
         if(jComboBoxStatus.getSelectedItem() == "Faturando"){
            jComboBoxStatus.setSelectedItem("Faturando");
        }else if(jComboBoxStatus.getSelectedItem() == "Faturado"){
            jComboBoxStatus.setSelectedItem("Faturado");
        } 
        return jComboBoxStatus;
    }

    private void jComboBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStatusActionPerformed
       //
    }//GEN-LAST:event_jComboBoxStatusActionPerformed

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
            java.util.logging.Logger.getLogger(TelaFaturamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaFaturamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaFaturamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaFaturamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*jButtonNovoe and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaFaturamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonClienteBuscaId;
    private javax.swing.JButton jButtonGravar;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonProdutoBusca;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JComboBox<Object> jComboBoxStatus;
    private javax.swing.JFormattedTextField jFormattedTextFieldFaturamentoData;
    private javax.swing.JFormattedTextField jFormattedTextFieldFaturamentoHora;
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
    private javax.swing.JLabel jLabelFaturamentoValorTotal;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPainelBotoes;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFaturamentoItens;
    private javax.swing.JTextField jTextFieldClienteBairro;
    private javax.swing.JTextField jTextFieldClienteCidade;
    private javax.swing.JTextField jTextFieldClienteEmail;
    private javax.swing.JTextField jTextFieldClienteId;
    private javax.swing.JTextField jTextFieldClienteNome;
    private javax.swing.JTextField jTextFieldClienteTel1;
    private javax.swing.JTextField jTextFieldClienteTel2;
    private javax.swing.JTextField jTextFieldFaturamentoUsuario;
    private javax.swing.JTextField jTextFieldProdutoCodBarras;
    // End of variables declaration//GEN-END:variables

    public JTextField getjTextFieldProdutoCodBarras() {
        return jTextFieldProdutoCodBarras;
    }

    public JTextField getjTextFieldClienteId() {
        return jTextFieldClienteId;
    }

    public JTextField getjTextFieldClienteBairro() {
        return jTextFieldClienteBairro;
    }

    public JTextField getjTextFieldClienteCidade() {
        return jTextFieldClienteCidade;
    }

    public JTextField getjTextFieldClienteEmail() {
        return jTextFieldClienteEmail;
    }

    public JTextField getjTextFieldClienteNome() {
        return jTextFieldClienteNome;
    }

    public JTextField getjTextFieldClienteTel1() {
        return jTextFieldClienteTel1;
    }

    public JTextField getjTextFieldClienteTel2() {
        return jTextFieldClienteTel2;
    }

    public JFormattedTextField getjFormattedTextFieldFaturamentoData() {
        return jFormattedTextFieldFaturamentoData;
    }

    public JFormattedTextField getjFormattedTextFieldFaturamentoHora() {
        return jFormattedTextFieldFaturamentoHora;
    }

    public JTextField getjTextFieldFaturamentoUsuario() {
        return jTextFieldFaturamentoUsuario;
    }

    public void setjFormattedTextFieldFaturamentoData(String data) {
        this.jFormattedTextFieldFaturamentoData.setText(data);
    }

    public JLabel getjLabelFaturamentoValorTotal() {
        return jLabelFaturamentoValorTotal;
    }
////



    public JButton getjButtonAdicionar() {
        return jButtonAdicionar;
    }

    public JTable getjTableFaturamentoItens() {
        return jTableFaturamentoItens;
    }



    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
       
    private void dataHora(){        
        
        // data/hora atual
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        setData(formatterData.format(agora));
        jFormattedTextFieldFaturamentoData.setText(getData());
        
        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        setHora(formatterHora.format(agora));
        jFormattedTextFieldFaturamentoHora.setText(getHora());
        
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    private void caixaAtual() {
        this.setUsuario("Eliel");
        jTextFieldFaturamentoUsuario.setText(getUsuario());
    }
    /*
    private void criarProdutoEAdicionar(){

        int idProduto = (Integer.parseInt(jTextFieldProdutoCodBarras.getText()));
        Produto produto = service.ServiceProduto.Buscar(idProduto);

        ItemDeVenda item = new ItemDeVenda(false,1, produto, produto.getValor());
        listaDeItens.add(item);

        tabela.addRow(new Object[]{
                     contador,
                     item.getId(),
                     item.getProduto().getId(),
                     1,
                     item.getValor(),
                     item.getValor()* 1
               });

        atualizarValorTotal();
    }   */
    /*
    private void atualizarValorTotal() {
        float total=0;
        for(ItemDeVenda i:listaDeItens){
            total += i.getQuantidade()*i.getValor();
        }
        getjLabelFaturamentoValorTotal().setText(total+"");
    }*/

    public JButton getjButtonBuscar() {
        return jButtonBuscar;
    }

    public JButton getjButtonCancelar() {
        return jButtonCancelar;
    }

    public JButton getjButtonClienteBuscaId() {
        return jButtonClienteBuscaId;
    }

    public JButton getjButtonGravar() {
        return jButtonGravar;
    }

    public JButton getjButtonNovo() {
        return jButtonNovo;
    }

    public JButton getjButtonSair() {
        return jButtonSair;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
}

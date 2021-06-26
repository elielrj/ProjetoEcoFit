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
import model.bo.ItemDeVenda;
import model.bo.PessoaFisica;
import model.bo.Produto;
import model.bo.Venda;
import view.TelaBuscaPessoaFisica;
import view.TelaBuscaProduto;
import view.TelaFaturamento;
import model.bo.Faturamento;

public class ControllerFaturamento implements ActionListener {

    TelaFaturamento telaFaturamento = new TelaFaturamento();
    public static int codigo;
    Faturamento faturamento;
    private DefaultTableModel tabela;

    public ControllerFaturamento(TelaFaturamento telaFaturamento) {

        this.telaFaturamento = telaFaturamento;
        this.faturamento = new Faturamento();

        this.tabela = (DefaultTableModel) this.telaFaturamento.getjTable_FaturamentoItens().getModel();

        this.telaFaturamento.getjButton_Novo().addActionListener(this);
        this.telaFaturamento.getjButton_Buscar().addActionListener(this);
        this.telaFaturamento.getjButton_Cancelar().addActionListener(this);
        this.telaFaturamento.getjButton_Gravar().addActionListener(this);
        this.telaFaturamento.getjButton_Sair().addActionListener(this);
        this.telaFaturamento.getjButtonProdutoBusca().addActionListener(this);

        this.telaFaturamento.getjTextField_Produto_Cod_Barras().addActionListener(this);
        this.telaFaturamento.getjButtonProdutoBusca().addActionListener(this);
        this.telaFaturamento.getjButton_Adicionar().addActionListener(this);

        this.telaFaturamento.getjTextField_Cliente_Id().addActionListener(this);
        this.telaFaturamento.getjButton_ClienteBuscaId().addActionListener(this);

        //this.telaFaturamento.getjTextField_Cliente_Nome().addActionListener(this);
        //this.telaFaturamento.getjTextField_Cliente_Cidade().addActionListener(this);
        //this.telaFaturamento.getjTextField_Cliente_Bairro().addActionListener(this);
        //this.telaFaturamento.getjTextField_Cliente_Email().addActionListener(this);
        //this.telaFaturamento.getjTextField_Cliente_Tel1().addActionListener(this);
        //this.telaFaturamento.getjTextField_Cliente_Tel2().addActionListener(this);
        //this.telaFaturamento.getjFormattedTextField_FaturamentoData().addActionListener(this);
        //this.telaFaturamento.getjFormattedTextField_FaturamentoHora().addActionListener(this);
        // this.telaFaturamento.getjTextField_FaturamentoUsuario().addActionListener(this);
        this.telaFaturamento.getjComboBoxStatus().addActionListener(this);
        
        this.telaFaturamento.getjTextField_Produto_Cod_Barras().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    inserirItem();
                } else if (evt.getKeyCode() == KeyEvent.VK_F1) {
                    buscaProdutoPorId();
                } else if (evt.getKeyCode() == KeyEvent.VK_F2) {
                    novoFaturamento();
                  
                } else if (evt.getKeyCode() == KeyEvent.VK_F3) {
                    Ativa(true);
                    LimpaEstadoComponentes(false);
                } else if (evt.getKeyCode() == KeyEvent.VK_F4) {
                    //persistir no banco
                } else if (evt.getKeyCode() == KeyEvent.VK_F5) {
                    //remover item selecionado
                }
            }
        });
        
        this.telaFaturamento.getjFormattedTextField_FaturamentoHora().setText(this.faturamento.getHora());
        this.telaFaturamento.getjFormattedTextField_FaturamentoData().setText(this.faturamento.getData());
        this.telaFaturamento.getjTextField_FaturamentoUsuario().setText(this.faturamento.getUsuario());
        Ativa(true);
        LimpaEstadoComponentes(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaFaturamento.getjButton_Novo()) {
            Ativa(false);
            LimpaEstadoComponentes(true);
            codigo = 0;
        } else if (e.getSource() == this.telaFaturamento.getjButton_Cancelar()) {
            Ativa(true);
            LimpaEstadoComponentes(false);
        } else if (e.getSource() == this.telaFaturamento.getjButton_Gravar()) {
            //montar objeto a persistir
            Venda venda = new Venda();

            venda.setData(this.telaFaturamento.getjFormattedTextField_FaturamentoData().getText());
            venda.setHora(this.telaFaturamento.getjFormattedTextField_FaturamentoHora().getText());
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
        if (e.getSource() == this.telaFaturamento.getjButton_Buscar()) {

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
                this.telaFaturamento.getjFormattedTextField_FaturamentoData().setText(venda.getData());
                this.telaFaturamento.getjFormattedTextField_FaturamentoHora().setText(venda.getHora());
                //this.telaFaturamento.getjComboBoxStatus().setSelectedItem(venda.getStatus());

                // this.telaFaturamento.getjTextFieldIdVenda().setEnabled(false);
            }
        }
        if (e.getSource() == this.telaFaturamento.getjButtonProdutoBusca()) {
            buscaProdutoPorId();

            //TelaBuscaProduto telaBuscaProduto = new TelaBuscaProduto(null,true);
            //ControllerBuscaProduto controllerBuscaProduto = new ControllerBuscaProduto(telaBuscaProduto);
            //telaBuscaProduto.setVisible(true);
            //this.telaFaturamento.getjTextField_Produto_Cod_Barras().setText(telaBuscaProduto.getCodProduto()+"");
        }

        if (e.getSource() == this.telaFaturamento.getjButton_Adicionar()) {
            if (!this.telaFaturamento.getjTextField_Produto_Cod_Barras().getText().equals("")) {
                inserirItem();
            } else {
                this.telaFaturamento.getjTextField_Produto_Cod_Barras().requestFocus();
            }
        }

        if (e.getSource() == this.telaFaturamento.getjButton_Sair()) {
            this.telaFaturamento.dispose();
        }

        if (e.getSource() == this.telaFaturamento.getjButton_ClienteBuscaId()) {
            buscaCliente();
        }
    }

    public void Ativa(boolean estadoBotoes) {
        this.telaFaturamento.getjButton_Novo().setEnabled(estadoBotoes);
        this.telaFaturamento.getjButton_Cancelar().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButton_Gravar().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButton_Buscar().setEnabled(estadoBotoes);
        this.telaFaturamento.getjButton_Sair().setEnabled(estadoBotoes);

        this.telaFaturamento.getjTextField_Produto_Cod_Barras().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButtonProdutoBusca().setEnabled(!estadoBotoes);

        this.telaFaturamento.getjButton_Adicionar().setEnabled(!estadoBotoes);

        this.telaFaturamento.getjTextField_Cliente_Id().setEnabled(!estadoBotoes);
        this.telaFaturamento.getjButton_ClienteBuscaId().setEnabled(!estadoBotoes);

        this.telaFaturamento.getjTextField_Cliente_Nome().setEnabled(false);
        this.telaFaturamento.getjTextField_Cliente_Cidade().setEnabled(false);
        this.telaFaturamento.getjTextField_Cliente_Bairro().setEnabled(false);
        this.telaFaturamento.getjTextField_Cliente_Email().setEnabled(false);
        this.telaFaturamento.getjTextField_Cliente_Tel1().setEnabled(false);
        this.telaFaturamento.getjTextField_Cliente_Tel2().setEnabled(false);

        this.telaFaturamento.getjFormattedTextField_FaturamentoData().setEnabled(false);
        this.telaFaturamento.getjFormattedTextField_FaturamentoHora().setEnabled(false);
        this.telaFaturamento.getjTextField_FaturamentoUsuario().setEnabled(false);
        this.telaFaturamento.getjComboBoxStatus().setEnabled(!estadoBotoes);

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

        this.telaFaturamento.getjTextField_Produto_Cod_Barras().setText(service.ServiceProduto.Buscar(telaBuscaProduto.getCodProduto()).getCodigoDeBarras());
    }

    public void inserirItem() {
        if (this.telaFaturamento.getjComboBoxStatus().getSelectedItem().equals("Faturando")) {
            String codBarrasSohNumeros = faturamento.semMascara(this.telaFaturamento.getjTextField_Produto_Cod_Barras().getText());

            if (service.ServiceProduto.codigoDeBarrasValido(Integer.parseInt(codBarrasSohNumeros)) && (codBarrasSohNumeros.length() >= 13)) {

                Produto produto = service.ServiceProduto.Buscar(Integer.parseInt(faturamento.semMascara(this.telaFaturamento.getjTextField_Produto_Cod_Barras().getText())));
                ItemDeVenda item = new ItemDeVenda(false, 1, produto, produto.getValor());
                this.faturamento.adicionar(item);
                int contador = 1;

                this.telaFaturamento.getjTable_FaturamentoItens().removeAll();
                tabela.getDataVector().removeAllElements();

                for (ItemDeVenda i : faturamento.getListaDeItens()) {
                    tabela.addRow(new Object[]{
                        contador,
                        i.getProduto().getId(),
                        i.getProduto().getDescricao(),
                        i.getQuantidade(),
                        i.getValor(),
                        i.getValor() * i.getQuantidade()
                    });
                    contador++;
                }
                this.faturamento.setContador(this.faturamento.getContador() + 1);
                this.faturamento.valorTotal();
                this.telaFaturamento.getjLabel_FaturamentoValorTotal().setText(this.faturamento.valorTotal() + "");
            } else {
                JOptionPane.showMessageDialog(null, "Cód de Barras inválido");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Não existe um faturamento em andamento");
        }
    }

    public void novoFaturamento() {
        if (this.telaFaturamento.getjComboBoxStatus().getSelectedItem().equals("Faturando")) {
            this.telaFaturamento.getjComboBoxStatus().setSelectedItem("Faturando");
        } else {
            JOptionPane.showMessageDialog(null, "Existe um faturamento em andamento!");
        }
    }

    public void buscaCliente() {
        TelaBuscaPessoaFisica telaBuscaPessoaFisica = new TelaBuscaPessoaFisica(null, true);
        ControllerBuscaPessoaFisica controllerBuscaPessoaFisica = new ControllerBuscaPessoaFisica(telaBuscaPessoaFisica);
        telaBuscaPessoaFisica.setVisible(true);
        this.telaFaturamento.getjTextField_Cliente_Id().setText(telaBuscaPessoaFisica.getCodPessoaFisica() + "");
        PessoaFisica pessoaFisica = service.ServicePessoaFisica.Buscar(telaBuscaPessoaFisica.getCodPessoaFisica());
        this.telaFaturamento.getjTextField_Cliente_Nome().setText(pessoaFisica.getNome());
        this.telaFaturamento.getjTextField_Cliente_Cidade().setText(pessoaFisica.getEndereco().getCidade() + "");
        this.telaFaturamento.getjTextField_Cliente_Bairro().setText(pessoaFisica.getEndereco().getBairro() + "");
        this.telaFaturamento.getjTextField_Cliente_Email().setText(pessoaFisica.getEmail() + "");
        this.telaFaturamento.getjTextField_Cliente_Tel1().setText(pessoaFisica.getTelefone1() + "");
        this.telaFaturamento.getjTextField_Cliente_Tel2().setText(pessoaFisica.getTelefone2() + "");
    }

    

}

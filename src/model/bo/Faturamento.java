package model.bo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JTable;

public class Faturamento {

    private int id;
    private String hora;
    private String data;
    private String userCaixa;
    protected ArrayList<ItemDeVenda> listaDeItens;
    private String dataDeVencimento;
    private String observacao;
    private float valorDoDesconto;
    private float valorTotal;
    private boolean status;
    private PessoaFisica pessoaFisica;

    //private JTable jTable;

    private int contador;

    public Faturamento() {
        this.listaDeItens = new ArrayList<>();
        this.dataHora();
        this.userCaixa = "Eliel";
        this.dataDeVencimento = getData();
    }

    public Faturamento(String observacao, float valorDoDesconto, float valorTotal,PessoaFisica pessoaFisica) {
        this();
        this.observacao = observacao;
        this.valorDoDesconto = valorDoDesconto;
        this.valorTotal = valorTotal;
        this.status = status;
        this.pessoaFisica = pessoaFisica;
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

    public String getUserCaixa() {
        return userCaixa;
    }

    public void setUserCaixa(String userCaixa) {
        this.userCaixa = userCaixa;
    }

    public ArrayList<ItemDeVenda> getListaDeItens() {
        return listaDeItens;
    }

    public void adicionar(ItemDeVenda item) {
        for(ItemDeVenda i: this.listaDeItens){
            if (item.getProduto().getId() == i.getProduto().getId()){
                i.setQuantidade(item.getQuantidade()+i.getQuantidade());
                return;
            }
        }
        listaDeItens.add(item);
    }

  //  public JTable getjTable() {
    //    return jTable;
    //}

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    private void dataHora() {

        // data/hora atual
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        this.setData(formatterData.format(agora));

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.setHora(formatterHora.format(agora));

    }

    public float valorTotal() {
        float total = 0;
        for (ItemDeVenda item : this.getListaDeItens()) {
            total += item.getQuantidade() * item.getValor();
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(String dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public float getValorDoDesconto() {
        return valorDoDesconto;
    }

    public void setValorDoDesconto(float valorDoDesconto) {
        this.valorDoDesconto = valorDoDesconto;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public static String semMascara(String dado) {
        dado = dado.replaceAll("\\.", "");
        return dado;
    }
    
    public void removerItemDaLista(int idDaLinhaSelecionada){
        for(ItemDeVenda itemDeVenda : listaDeItens){
            if (itemDeVenda.getProduto().getId() == idDaLinhaSelecionada){
                if (itemDeVenda.getQuantidade() > 1){
                    itemDeVenda.setQuantidade(itemDeVenda.getQuantidade() -1 );
                    break;
                } else if (itemDeVenda.getQuantidade() == 1){
                    listaDeItens.remove(itemDeVenda);
                    break;
                }
            }
        }
    }

    public boolean existeItemNaLista(int idDaLinhaSelecionada) {
        if(idDaLinhaSelecionada > listaDeItens.size())
            return false;        
        return true;
    }

    public void removerTudo() {
       
        this.dataHora();
        this.userCaixa = "Eliel";
        this.listaDeItens = new ArrayList<>();
        
        this.observacao = "";
        this.valorDoDesconto = 0;
        this.valorTotal = 0;
        this.status = false;
        this.pessoaFisica = new PessoaFisica();
        this.contador = 0;
    }

    public void setListaDeItens(ArrayList<ItemDeVenda> listaDeItemDeVenda) {
        listaDeItens = listaDeItemDeVenda;
    }
    
    
    
}

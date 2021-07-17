package model.bo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Venda {

    private int id;//1
    private String data;//2
    private String hora;//3
    private String userCaixa;//4
    private String dataDeVencimento;//5
    private String observacao;//6
    private float valorDoDesconto;//7
    private float valorTotal;//8
    private boolean status;//9
    private PessoaFisica pessoaFisica;//10
    private List<ItemDeVenda> itensDeVenda;//11

    private Venda(VendaBuilder vendaBuilder) {
        this.id = vendaBuilder.id;
        this.data = vendaBuilder.data;
        this.hora = vendaBuilder.hora;
        this.dataDeVencimento = vendaBuilder.dataDeVencimento;
        this.observacao = vendaBuilder.observacao;
        this.valorDoDesconto = vendaBuilder.valorDoDesconto;
        this.valorTotal = vendaBuilder.valorTotal;
        this.status = vendaBuilder.status;
        this.pessoaFisica = vendaBuilder.pessoaFisica;
        this.itensDeVenda = new ArrayList<>(); 
        dataHora();
        setUserCaixa("Eliel");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

    public List<ItemDeVenda> getItensDeVenda() {
        return itensDeVenda;
    }

    public void setItensDeVenda(List<ItemDeVenda> itensDeVenda) {
        this.itensDeVenda = itensDeVenda;
    }

    public String getUserCaixa() {
        return userCaixa;
    }

    public void setUserCaixa(String userCaixa) {
        this.userCaixa = userCaixa;
    }
    
    public int quantidadeDeItensNaLista(){
        return itensDeVenda.size();
    }

    
    public static class VendaBuilder {

        private int id;
        private String data;
        private String hora;
        private String dataDeVencimento;
        private String observacao;
        private float valorDoDesconto;
        private float valorTotal;
        private boolean status;
        private PessoaFisica pessoaFisica;
        private String userCaixa;
        private List<ItemDeVenda> itensDeVenda;

        public VendaBuilder() {
        }

        public VendaBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public VendaBuilder setData(String data) {
            this.data = data;
            return this;
        }

        public VendaBuilder setHora(String hora) {
            this.hora = hora;
            return this;
        }

        public VendaBuilder setDataDeVencimento(String dataDeVencimento) {
            this.dataDeVencimento = dataDeVencimento;
            return this;
        }

        public VendaBuilder setObservacao(String observacao) {
            this.observacao = observacao;
            return this;
        }

        public VendaBuilder setValorDoDesconto(float valorDoDesconto) {
            this.valorDoDesconto = valorDoDesconto;
            return this;
        }

        public VendaBuilder setValorTotal(float valorTotal) {
            this.valorTotal = valorTotal;
            return this;
        }

        public VendaBuilder setStatus(boolean status) {
            this.status = status;
            return this;
        }

        public VendaBuilder setUserCaixa(String userCaixa) {
            this.userCaixa = userCaixa;
            return this;
        }

        public VendaBuilder setPessoaFisica(PessoaFisica pessoaFisica) {
            this.pessoaFisica = pessoaFisica;
            return this;
        }

        public VendaBuilder setItensDeVenda(List<ItemDeVenda> itensDeVenda) {
            this.itensDeVenda = itensDeVenda;
            return this;
        }

        public Venda createVenda() {
            return new Venda(this);
        }
    }
    

    
    public  void adicionarItem(int quantidade, Produto produto){
        ItemDeVenda itemDeVenda = new ItemDeVenda.ItemDeVendaBuilder()
                .setQuantidade(quantidade)
                .setProduto(produto)
                .createItemDeVenda();
        for(ItemDeVenda i : itensDeVenda){
            if(itemDeVenda.getProduto().getId() == i.getProduto().getId()){
                i.setQuantidade(itemDeVenda.getQuantidade() + i.getQuantidade());
                i.atualizarSubTotal();
                return;
            }
        }
        itensDeVenda.add(itemDeVenda);
    }
    
    public  void adicionarItem(Produto produto){
        adicionarItem(1,produto);
    }
    
    public void removerItemDaLista(Produto produto){
        
        for(ItemDeVenda i : itensDeVenda){
            if (i.getProduto().getId() == produto.getId()){
                if (i.getQuantidade() > 1){
                    i.setQuantidade(i.getQuantidade() -1 );
                    break;
                } else if (i.getQuantidade() == 1){
                    itensDeVenda.remove(i);
                    break;
                }
            }
        }
    }
    
    public boolean existeItemNaLista(int idDaLinhaSelecionada) {
        if(idDaLinhaSelecionada > itensDeVenda.size())
            return false;        
        return true;
    }
    private void dataHora() {

        // data/hora atual
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        this.setData(formatterData.format(agora));

        //30 dias para pagamento
        this.setDataDeVencimento(formatterData.format(agora.plusMonths(1)));
        
        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.setHora(formatterHora.format(agora));

    }
    
    public void removerTodosOsItensDaLista(){
        itensDeVenda.clear();
    }
    
   
    
    public float calcularValorTotal(){
       float total = 0;
        
        for (ItemDeVenda itemDeVenda : itensDeVenda){
            total += itemDeVenda.calcularSubTotal();
        }
        return total;
    }
}

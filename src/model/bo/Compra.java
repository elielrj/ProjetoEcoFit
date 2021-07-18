package model.bo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Compra {

    private int id;
    private String data;
    private String hora;
    private String dataDeVencimento;
    private String observacao;
    private float valorDeDesconto;
    private float valorTotal;
    private boolean status;
    private List<ItemDeCompra> itensDeCompra;
    private Fornecedor fornecedor;
    private String userCaixa;

    private Compra(CompraBuilder compraBuilder) {
        this.id = compraBuilder.id;
        this.data = compraBuilder.data;
        this.hora = compraBuilder.hora;
        this.dataDeVencimento = compraBuilder.dataDeVencimento;
        this.observacao = compraBuilder.observacao;
        this.valorDeDesconto = compraBuilder.valorDeDesconto;
        this.valorTotal = compraBuilder.valorTotal;
        this.fornecedor = compraBuilder.fornecedor;
        this.status = compraBuilder.status;
        this.userCaixa = compraBuilder.userCaixa;
        this.itensDeCompra = compraBuilder.itensDeCompra;
        dataHora();
        setUserCaixa("Eliel");
    }

    public boolean getStatus() {
        return status;
    }

    public List<ItemDeCompra> getItensDeCompra() {
        return itensDeCompra;
    }

    public void setItensDeCompra(List<ItemDeCompra> itensDeCompra) {
        this.itensDeCompra = itensDeCompra;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
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

    public float getValorDeDesconto() {
        return valorDeDesconto;
    }

    public void setValorDeDesconto(float valorDeDesconto) {
        this.valorDeDesconto = valorDeDesconto;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public void setUserCaixa(String userCaixa) {
        this.userCaixa = userCaixa;
    }

    public boolean isStatus() {
        return status;
    }

    public String getUserCaixa() {
        return userCaixa;
    }
    

    
    public static class CompraBuilder {

        private int id;
        private String data;
        private String hora;
        private String dataDeVencimento;
        private String observacao;
        private float valorDeDesconto;
        private float valorTotal;
        private Fornecedor fornecedor;
        private boolean status;
        private String userCaixa;
        private List<ItemDeCompra> itensDeCompra;

        public CompraBuilder() {
            this.itensDeCompra = new ArrayList<>();
        }

        public CompraBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public CompraBuilder setData(String data) {
            this.data = data;
            return this;
        }

        public CompraBuilder setHora(String hora) {
            this.hora = hora;
            return this;
        }

        public CompraBuilder setItensDeCompra(List<ItemDeCompra> itensDeCompra) {
            this.itensDeCompra = itensDeCompra;
            return this;
        }
        
        

        public CompraBuilder setDataDeVencimento(String dataDeVencimento) {
            this.dataDeVencimento = dataDeVencimento;
            return this;
        }

        public CompraBuilder setObservacao(String observacao) {
            this.observacao = observacao;
            return this;
        }

        public CompraBuilder setValorDeDesconto(float valorDeDesconto) {
            this.valorDeDesconto = valorDeDesconto;
            return this;
        }

        public CompraBuilder setValorTotal(float valorTotal) {
            this.valorTotal = valorTotal;
            return this;
        }

        public CompraBuilder setFornecedor(Fornecedor fornecedor) {
            this.fornecedor = fornecedor;
            return this;
        }

        public CompraBuilder setStatus(boolean status) {
            this.status = status;
            return this;
        }
        public CompraBuilder setUserCaixa(String userCaixa) {
            this.userCaixa = userCaixa;
            return this;
        }
        

        public Compra createCompra() {
            return new Compra(this);
        }

    }


     public  void adicionarItem(int quantidade, Produto produto){
        ItemDeCompra itemDeCompra = new ItemDeCompra.ItemDeCompraBuilder()
                .setQuantidade(quantidade)
                .setProduto(produto)
                .createItemDeCompra();
        for(ItemDeCompra i : itensDeCompra){
            if(itemDeCompra.getProduto().getId() == i.getProduto().getId()){
                i.setQuantidade(itemDeCompra.getQuantidade() + i.getQuantidade());
                i.atualizarSubTotal();
                return;
            }
        }
        itensDeCompra.add(itemDeCompra);
    }
    
    public  void adicionarItem(Produto produto){
        adicionarItem(1,produto);
    }
    
    public int quantidadeDeItensNaLista(){
        return itensDeCompra.size();
    }
    
    public void removerItemDaLista(Produto produto){
        
        for(ItemDeCompra i : itensDeCompra){
            if (i.getProduto().getId() == produto.getId()){
                if (i.getQuantidade() > 1){
                    i.setQuantidade(i.getQuantidade() -1 );
                    break;
                } else if (i.getQuantidade() == 1){
                    itensDeCompra.remove(i);
                    break;
                }
            }
        }
    }
    
    public boolean existeItemNaLista(int idDaLinhaSelecionada) {
        if(idDaLinhaSelecionada > itensDeCompra.size())
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
        itensDeCompra.clear();
    }
    
   
    
    public float calcularValorTotal(){
       float total = 0;
        
        for (ItemDeCompra itemDeCompra : itensDeCompra){
            total += itemDeCompra.calcularSubTotal();
        }
        return total;
    }

}

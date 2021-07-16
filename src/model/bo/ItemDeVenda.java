package model.bo;

public class ItemDeVenda {

    private int id;//1
    private int quantidade;//3
    private Produto produto;//4
    private float subTotal;//5
    private int vendaId;//6

    private ItemDeVenda(ItemDeVendaBuilder itemDeVendaBuilder) {
        this.id = itemDeVendaBuilder.id;
        this.quantidade = itemDeVendaBuilder.quantidade;
        this.produto = itemDeVendaBuilder.produto;
        this.subTotal = itemDeVendaBuilder.subTotal;
        this.vendaId = itemDeVendaBuilder.vendaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public float getSubTotal() {
        return subTotal;
    }



    public int getVendaId() {
        return vendaId;
    }

    public void setVendaId(int vendaId) {
        this.vendaId = vendaId;
    }

    public static class ItemDeVendaBuilder {

        private int id;
        private boolean status;
        private int quantidade;
        private Produto produto;
        private float subTotal;
        private int vendaId;

        public ItemDeVendaBuilder() {
        }

        public ItemDeVendaBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ItemDeVendaBuilder setQuantidade(int quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public ItemDeVendaBuilder setProduto(Produto produto) {
            this.produto = produto;
            return this;
        }

        public ItemDeVendaBuilder setVendaId(int vendaId) {
            this.vendaId = vendaId;
            return this;
        }

        public ItemDeVenda createItemDeVenda() {
            this.subTotal = quantidade * produto.getValor();
            return new ItemDeVenda(this);
        }

    }
    
    public Float calcularSubTotal(){
        subTotal = quantidade * produto.getValor();
        return subTotal;
    }
    
    public void atualizarSubTotal(){
        subTotal = quantidade * produto.getValor();
    }

}

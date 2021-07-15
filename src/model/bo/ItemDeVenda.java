package model.bo;

public class ItemDeVenda {

    private int id;//1
    private boolean status;//2
    private int quantidade;//3
    private Produto produto;//4
    private float subTotal;//5
    private int vendaId;//6

    private ItemDeVenda(ItemDeVendaBuilder itemDeVendaBuilder) {
        this.id = itemDeVendaBuilder.id;
        this.status = itemDeVendaBuilder.status;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
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

        public ItemDeVendaBuilder setStatus(boolean status) {
            this.status = status;
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

        public ItemDeVendaBuilder setSubTotal(float subTotal) {
            this.subTotal = subTotal;
            return this;
        }

        public ItemDeVendaBuilder setVendaId(int vendaId) {
            this.vendaId = vendaId;
            return this;
        }

        public ItemDeVenda createItemDeVenda() {
            return new ItemDeVenda(this);
        }

    }

}

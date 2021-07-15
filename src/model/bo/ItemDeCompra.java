package model.bo;

public class ItemDeCompra {

    private int id;
    private int quantidade;
    private float subTotal;
    private Produto produto;
    private int compraId;

    private ItemDeCompra(ItemDeCompraBuilder itemDeCompraBuilder) {
        this.id = itemDeCompraBuilder.id;
        this.quantidade = itemDeCompraBuilder.quantidade;
        this.subTotal = itemDeCompraBuilder.subTotal;
        this.produto = itemDeCompraBuilder.produto;
        this.compraId = itemDeCompraBuilder.compraId;
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

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }

    public static class ItemDeCompraBuilder {

        private int id;
        private int quantidade;
        private float subTotal;
        private Produto produto;
        private int compraId;

        public ItemDeCompraBuilder() {
        }

        public ItemDeCompraBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ItemDeCompraBuilder setQuantidade(int quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public ItemDeCompraBuilder setSubTotal(float subTotal) {
            this.subTotal = subTotal;
            return this;
        }

        public ItemDeCompraBuilder setProduto(Produto produto) {
            this.produto = produto;
            return this;
        }

        public ItemDeCompraBuilder setCompraId(int compraId) {
            this.compraId = compraId;
            return this;
        }

        public ItemDeCompra createItemDeCompra() {
            return new ItemDeCompra(this);
        }

    }

}

package model.bo;

public class ItemDeVenda {

    private int id;
    private boolean status;
    private int quantidade;
    private Produto produto;
    private float valor;
    private int vendaId;

    public ItemDeVenda() {

    }

    public ItemDeVenda(boolean status) {
        this.status = status;
    }

    public ItemDeVenda(boolean status, int quantidade) {
        this(status);
        this.quantidade = quantidade;
    }

    public ItemDeVenda(boolean status, int quantidade, Produto produto) {
        this(status, quantidade);
        this.produto = produto;
    }

    public ItemDeVenda(boolean status, int quantidade, Produto produto, float valor) {
        this(status, quantidade, produto);
        this.valor = valor;
    }

    public ItemDeVenda(boolean status, int quantidade, Produto produto, float valor, int vendaId) {
        this(status, quantidade, produto, valor);
        this.vendaId = vendaId;
    }

    public ItemDeVenda(boolean status, int quantidade, Produto produto, float valor, int vendaId, int id) {
        this(status, quantidade, produto, valor, vendaId);
        this.id = id;
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getVendaId() {
        return vendaId;
    }

    public void setVendaId(int vendaId) {
        this.vendaId = vendaId;
    }

    @Override
    public String toString() {
        return "Item: " + getId() + " Produto: " + getProduto().getDescricao() + " Qtd: " + getQuantidade() + " Valor: " + getValor() + " SubTotal: " + getQuantidade() * getProduto().getValor();
    }
}

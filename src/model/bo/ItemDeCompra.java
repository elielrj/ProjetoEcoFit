package model.bo;
public class ItemDeCompra extends Item{
    
    private int id;
            
    private Compra compra;
    private Produto produto;
    
    public ItemDeCompra() {
    }

    public ItemDeCompra(int quantidade, float valor, int id,  Compra compra, Produto produto) {
        super(quantidade, valor);
        this.id = id;
        this.compra = compra;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }


     @Override
    public String toString() {
        return  this.getId() + " " +
                super.toString() + " " +
                this.getCompra().toString() + " " +
                this.getProduto().toString();
    }
    

   
}

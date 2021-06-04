
package model.bo;


public class ItemDeVenda extends Item {
    
    private int id;
      
    private Venda venda; 
    private Produto produto;

    public ItemDeVenda() {
        
    }

    public ItemDeVenda( int quantidade, float valor, int id,  Venda venda, Produto produto) {
        super(quantidade, valor);
        this.id = id;
        this.venda = venda;
        this.produto = produto;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
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
                this.getVenda().toString() + " " +
                this.getProduto().toString();
    }
}

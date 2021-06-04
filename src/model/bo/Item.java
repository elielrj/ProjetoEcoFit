
package model.bo;

public class Item {
    
    private int quantidade;
    private float valor;

    
    public Item() {
    }

    public Item(int quantidade, float valor) {
        this.quantidade = quantidade;
        this.valor = valor;
    }

    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    
    @Override
    public String toString() {
        return  this.getQuantidade() + " " + 
                this.getValor();
    }
}

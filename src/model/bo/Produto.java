package model.bo;

public class Produto {

private int id;
private String descricao;
private String unidadeDeCompra;
private String unidadeDeVenda;
private String correlacaoUnidade;
private float valor;
private int quantidadeDeEstoque;
private String codigoDeBarras;
private boolean status;
       

    public Produto() {
    }

    public Produto(int id, String descricao, String unidadeDeCompra, String unidadeDeVenda, String correlacaoUnidade, float valor, int quantidadeDeEstoque, String codigoDeBarras, boolean status) {
        this.id = id;
        this.descricao = descricao;
        this.unidadeDeCompra = unidadeDeCompra;
        this.unidadeDeVenda = unidadeDeVenda;
        this.correlacaoUnidade = correlacaoUnidade;
        this.valor = valor;
        this.quantidadeDeEstoque = quantidadeDeEstoque;
        this.codigoDeBarras = codigoDeBarras;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidadeDeCompra() {
        return unidadeDeCompra;
    }

    public void setUnidadeDeCompra(String unidadeDeCompra) {
        this.unidadeDeCompra = unidadeDeCompra;
    }

    public String getUnidadeDeVenda() {
        return unidadeDeVenda;
    }

    public void setUnidadeDeVenda(String unidadeDeVenda) {
        this.unidadeDeVenda = unidadeDeVenda;
    }

    public String getCorrelacaoUnidade() {
        return correlacaoUnidade;
    }

    public void setCorrelacaoUnidade(String correlacaoUnidade) {
        this.correlacaoUnidade = correlacaoUnidade;
    }

    public float getValor() {
        return valor;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getQuantidadeDeEstoque() {
        return quantidadeDeEstoque;
    }

    public void setQuantidadeDeEstoque(int quantidadeDeEstoque) {
        this.quantidadeDeEstoque = quantidadeDeEstoque;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }



    

    @Override
    public String toString() {
        return getId() + " " + 
                getDescricao() + " " + 
                getUnidadeDeCompra() + " " + 
                getUnidadeDeVenda() + " " + 
                getCorrelacaoUnidade() + " " + 
                getValor() + " " + 
                getQuantidadeDeEstoque() + " " + 
                getCodigoDeBarras();
    }


                
}

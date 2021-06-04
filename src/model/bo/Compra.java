
package model.bo;

public class Compra {
    
    private int id;
    private String data;
    private String hora;
    private String dataDeVencimento;
    private String observacao;
    private float valorDeDesconto;
    private float valorTotal;
    
    private Fornecedor fornecedor;
    

    public Compra() {
    }

    public Compra(int id, String data, String hora, String dataDeVencimento, String observacao, float valorDeDesconto, float valorTotal, Fornecedor fornecedor) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.dataDeVencimento = dataDeVencimento;
        this.observacao = observacao;
        this.valorDeDesconto = valorDeDesconto;
        this.valorTotal = valorTotal;
        this.fornecedor = fornecedor;
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


    
    @Override
    public String toString() {
        return 
                this.getId() + " " + 
                this.getData() + " " + 
                this.getHora() + " " + 
                this.getObservacao() + " " + 
                this.getValorDeDesconto() + " " + 
                this.getValorTotal() + " " + 
                this.getValorDeDesconto() + " " + 
                this.getFornecedor().toString();
    }

    
    

            
    
}

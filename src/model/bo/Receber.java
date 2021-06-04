package model.bo;

public class Receber {
    
    private int id;
    private String data;
    private String hora;
    private float valorDeDescontoNegociado;
    private float valorDeAcrescimo;
    private float valorRecebido;
    private String observacao;
    
    private Venda venda;

    public Receber() {
    }

    public Receber(int id, String data, String hora, float valorDeDescontoNegociado, float valorDeAcrescimo, float valorRecebido, String observacao, Venda venda) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.valorDeDescontoNegociado = valorDeDescontoNegociado;
        this.valorDeAcrescimo = valorDeAcrescimo;
        this.valorRecebido = valorRecebido;
        this.observacao = observacao;
        this.venda = venda;
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

    public float getValorDeDescontoNegociado() {
        return valorDeDescontoNegociado;
    }

    public void setValorDeDescontoNegociado(float valorDeDescontoNegociado) {
        this.valorDeDescontoNegociado = valorDeDescontoNegociado;
    }

    public float getValorDeAcrescimo() {
        return valorDeAcrescimo;
    }

    public void setValorDeAcrescimo(float valorDeAcrescimo) {
        this.valorDeAcrescimo = valorDeAcrescimo;
    }

    public float getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(float valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }


    @Override
    public String toString() {
        return this.getId() + 
                " " + this.getData() + 
                " " + this.getHora() + 
                " " + this.getValorDeDescontoNegociado() + 
                " " + this.getValorDeAcrescimo() + 
                " " + this.getValorRecebido() + 
                " " + this.getObservacao() +
                " " + this.getVenda().toString();
    }
    
    
}


package model.bo;


public class Pagar {
    
    private int id;
    private String data;
    private String hora;
    private float valorDeDescontoNegociado;
    private float valorDeAcrescimo;
    private float valorPago;
    private String observacao;
    
    private Compra compra;

    public Pagar() {
    }

    public Pagar(int id, String data, String hora, float valorDeDescontoNegociado, float valorDeAcrescimo, float valorPago, String observacao, Compra compra) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.valorDeDescontoNegociado = valorDeDescontoNegociado;
        this.valorDeAcrescimo = valorDeAcrescimo;
        this.valorPago = valorPago;
        this.observacao = observacao;
        this.compra = compra;
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

    public float getValorPago() {
        return valorPago;
    }

    public void setValorPago(float valorPago) {
        this.valorPago = valorPago;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }



    @Override
    public String toString() {
        return 
        this.getId() + " " +
        this.getData()+ " " +
        this.getHora()+ " " +
        this.getValorDeDescontoNegociado()+ " " +
        this.getValorDeAcrescimo()+ " " +
        this.getValorPago()+ " " +
        this.getObservacao()+ " " +        
        this.getCompra().toString();
    }
    
    
}

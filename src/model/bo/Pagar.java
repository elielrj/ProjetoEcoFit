package model.bo;

public class Pagar {

    private int id;//1
    private String data;//2
    private String hora;//3
    private float valorDeDescontoNegociado;//4
    private float valorDeAcrescimo;//5
    private float valorPago;//6
    private String observacao;//7
    private Compra compra;//8
    private boolean status;//9

    private Pagar(PagarBuilder pagarBuilder) {
        this.id = pagarBuilder.id;
        this.data = pagarBuilder.data;
        this.hora = pagarBuilder.hora;
        this.valorDeDescontoNegociado = pagarBuilder.valorDeDescontoNegociado;
        this.valorDeAcrescimo = pagarBuilder.valorDeAcrescimo;
        this.valorPago = pagarBuilder.valorPago;
        this.observacao = pagarBuilder.observacao;
        this.compra = pagarBuilder.compra;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public static class PagarBuilder {

        private int id;
        private String data;
        private String hora;
        private float valorDeDescontoNegociado;
        private float valorDeAcrescimo;
        private float valorPago;
        private String observacao;
        private Compra compra;
        private boolean status;

        public PagarBuilder() {
        }

        public PagarBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public PagarBuilder setData(String data) {
            this.data = data;
            return this;
        }

        public PagarBuilder setHora(String hora) {
            this.hora = hora;
            return this;
        }

        public PagarBuilder setValorDeDescontoNegociado(float valorDeDescontoNegociado) {
            this.valorDeDescontoNegociado = valorDeDescontoNegociado;
            return this;
        }

        public PagarBuilder setValorDeAcrescimo(float valorDeAcrescimo) {
            this.valorDeAcrescimo = valorDeAcrescimo;
            return this;
        }

        public PagarBuilder setValorPago(float valorPago) {
            this.valorPago = valorPago;
            return this;
        }

        public PagarBuilder setStatus(boolean status) {
            this.status = status;
            return this;
        }

        public PagarBuilder setObservacao(String observacao) {
            this.observacao = observacao;
            return this;
        }

        public PagarBuilder setCompra(Compra compra) {
            this.compra = compra;
            return this;
        }

        public Pagar createPagar() {
            return new Pagar(this);
        }

    }

}

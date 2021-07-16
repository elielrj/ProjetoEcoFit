package model.bo;

public class Receber {

    private int id;//1
    private String data;//2
    private String hora;//3
    private float valorDeDescontoNegociado;//4
    private float valorDeAcrescimo;//5
    private float valorRecebido;//6
    private String observacao;//7
    private Venda venda;//8

    private Receber(ReceberBuilder receberBuilder) {
        this.id = receberBuilder.id;
        this.data = receberBuilder.data;
        this.hora = receberBuilder.hora;
        this.valorDeDescontoNegociado = receberBuilder.valorDeDescontoNegociado;
        this.valorDeAcrescimo = receberBuilder.valorDeAcrescimo;
        this.valorRecebido = receberBuilder.valorRecebido;
        this.observacao = receberBuilder.observacao;
        this.venda = receberBuilder.venda;
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

    public static class ReceberBuilder {

        private int id;
        private String data;
        private String hora;
        private float valorDeDescontoNegociado;
        private float valorDeAcrescimo;
        private float valorRecebido;
        private String observacao;
        private Venda venda;

        public ReceberBuilder() {
        }

        public ReceberBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ReceberBuilder setData(String data) {
            this.data = data;
            return this;
        }

        public ReceberBuilder setHora(String hora) {
            this.hora = hora;
            return this;
        }

        public ReceberBuilder setValorDeDescontoNegociado(float valorDeDescontoNegociado) {
            this.valorDeDescontoNegociado = valorDeDescontoNegociado;
            return this;
        }

        public ReceberBuilder setValorDeAcrescimo(float valorDeAcrescimo) {
            this.valorDeAcrescimo = valorDeAcrescimo;
            return this;
        }

        public ReceberBuilder setValorRecebido(float valorRecebido) {
            this.valorRecebido = valorRecebido;
            return this;
        }

        public ReceberBuilder setObservacao(String observacao) {
            this.observacao = observacao;
            return this;
        }

        public ReceberBuilder setVenda(Venda venda) {
            this.venda = venda;
            return this;
        }

        public Receber createReceber() {
            return new Receber(this);
        }

    }

}

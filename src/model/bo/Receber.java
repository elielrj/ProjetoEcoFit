package model.bo;

public class Receber {

    private int id;//1
    private String dataRecebimento;//2
    private String hora;//3
//    private float valorDesconto;//4
    private float valorAcrescimo;//5
    private float valorRecebido;//6
    private String observacao;//7
    private ContaAReceber contaAReceber;//8

    private Receber(ReceberBuilder receberBuilder) {
        this.id = receberBuilder.id;
        this.dataRecebimento = receberBuilder.dataRecebimento;
        this.hora = receberBuilder.hora;
        //this.valorDesconto = receberBuilder.valorDesconto;
        this.valorAcrescimo = receberBuilder.valorAcrescimo;
        this.valorRecebido = receberBuilder.valorRecebido;
        this.observacao = receberBuilder.observacao;
        this.contaAReceber = receberBuilder.contaAReceber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(String dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public float getValorAcrescimo() {
        return valorAcrescimo;
    }

    public void setValorAcrescimo(float valorAcrescimo) {
        this.valorAcrescimo = valorAcrescimo;
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

    public ContaAReceber getContaAReceber() {
        return contaAReceber;
    }

    public void setContaAReceber(ContaAReceber contaAReceber) {
        this.contaAReceber = contaAReceber;
    }

    public static class ReceberBuilder {

        private int id;
        private String dataRecebimento;
        private String hora;
        private float valorDesconto;
        private float valorAcrescimo;
        private float valorRecebido;
        private String observacao;
        private ContaAReceber contaAReceber;

        public ReceberBuilder() {
        }

        public ReceberBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ReceberBuilder setDataRecebimento(String data) {
            this.dataRecebimento = data;
            return this;
        }

        public ReceberBuilder setHora(String hora) {
            this.hora = hora;
            return this;
        }

        public ReceberBuilder setValorAcrescimo(float valorAcrescimo) {
            this.valorAcrescimo = valorAcrescimo;
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

        public ReceberBuilder setContaAReceber(ContaAReceber contaAReceber) {
            this.contaAReceber = contaAReceber;
            return this;
        }

        public Receber createReceber() {
            return new Receber(this);
        }

    }

}

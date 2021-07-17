package model.bo;

public class ContaAReceber {

    private int id;
    private int vendaId;
    private float valor;
    private boolean status;

    private ContaAReceber(ContaAReceberBuilder contaAReceberBuilder) {
        this.id = contaAReceberBuilder.id;
        this.vendaId = contaAReceberBuilder.vendaId;
        this.valor = contaAReceberBuilder.valor;
        this.status = contaAReceberBuilder.status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVendaId() {
        return vendaId;
    }

    public void setVendaId(int vendaId) {
        this.vendaId = vendaId;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static class ContaAReceberBuilder {

        private int id;
        private int vendaId;
        private float valor;
        private boolean status;

        public ContaAReceberBuilder() {
        }

        public ContaAReceberBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ContaAReceberBuilder setVendaId(int vendaId) {
            this.vendaId = vendaId;
            return this;
        }

        public ContaAReceberBuilder setValor(float valor) {
            this.valor = valor;
            return this;
        }

        public ContaAReceberBuilder setStatus(boolean status) {
            this.status = status;
            return this;
        }

        public ContaAReceber createContaAReceber() {
            return new ContaAReceber(this);
        }

    }

}

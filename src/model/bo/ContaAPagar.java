package model.bo;
public class ContaAPagar {

    private int id;
    private int compraId;
    private float valor;
    private boolean status;

    private ContaAPagar(ContaAPagarBuilder contaAPagarBuilder) {
        this.id = contaAPagarBuilder.id;
        this.compraId = contaAPagarBuilder.compraId;
        this.valor = contaAPagarBuilder.valor;
        this.status = contaAPagarBuilder.status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
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

    public static class ContaAPagarBuilder {

        private int id;
        private int compraId;
        private float valor;
        private boolean status;

        public ContaAPagarBuilder() {
        }

        public ContaAPagarBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ContaAPagarBuilder setCompraId(int compraId) {
            this.compraId = compraId;
            return this;
        }

        public ContaAPagarBuilder setValor(float valor) {
            this.valor = valor;
            return this;
        }

        public ContaAPagarBuilder setStatus(boolean status) {
            this.status = status;
            return this;
        }

        public ContaAPagar createContaAPagar() {
            return new ContaAPagar(this);
        }

    }

}

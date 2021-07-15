package model.bo;

public class Endereco {

    private int id;//1
    private String logradouro;//2
    private String numero;//3
    private Bairro bairro;//4
    private String cep;//5
    private boolean status;//6

    private Endereco(EnderecoBuilder enderecoBuilder) {
        this.id = enderecoBuilder.id;
        this.logradouro = enderecoBuilder.logradouro;
        this.numero = enderecoBuilder.numero;
        this.bairro = enderecoBuilder.bairro;
        this.cep = enderecoBuilder.cep;
        this.status = enderecoBuilder.status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static  class EnderecoBuilder {

        private int id;
        private String logradouro;
        private String numero;
        private Bairro bairro;
        private String cep;
        private boolean status;

        public EnderecoBuilder() {
        }

        public EnderecoBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public EnderecoBuilder setLogradouro(String logradouro) {
            this.logradouro = logradouro;
            return this;
        }

        public EnderecoBuilder setNumero(String numero) {
            this.numero = numero;
            return this;
        }

        public EnderecoBuilder setBairro(Bairro bairro) {
            this.bairro = bairro;
            return this;
        }

        public EnderecoBuilder setCep(String cep) {
            this.cep = cep;
            return this;
        }

        public EnderecoBuilder setStatus(boolean status) {
            this.status = status;
            return this;
        }

        public Endereco createEndereco() {
            return new Endereco(this);
        }

    }

}

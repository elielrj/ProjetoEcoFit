package model.bo;

public class PessoaFisica {

    private int id;//1
    private String nome;//2
    private String rg;//3
    private String cpf;//4
    private String dataDeNascimento;//5
    private Endereco endereco;//6
    private String tipo;//7
    private String telefone1;//8
    private String telefone2;//9
    private String email;//10
    private String observacao;//11
    private boolean status;//12
    private String complemento;//13

    private PessoaFisica(PessoaFisicaBuilder pessoaFisicaBuilder) {
        this.id = pessoaFisicaBuilder.id;
        this.nome = pessoaFisicaBuilder.nome;
        this.rg = pessoaFisicaBuilder.rg;
        this.cpf = pessoaFisicaBuilder.cpf;
        this.dataDeNascimento = pessoaFisicaBuilder.dataDeNascimento;
        this.endereco = pessoaFisicaBuilder.endereco;
        this.tipo = pessoaFisicaBuilder.tipo;
        this.telefone1 = pessoaFisicaBuilder.telefone1;
        this.telefone2 = pessoaFisicaBuilder.telefone2;
        this.email = pessoaFisicaBuilder.email;
        this.observacao = pessoaFisicaBuilder.observacao;
        this.status = pessoaFisicaBuilder.status;
        this.complemento = pessoaFisicaBuilder.complemento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public static class PessoaFisicaBuilder {

        private int id;
        private String nome;
        private String rg;
        private String cpf;
        private String dataDeNascimento;
        private Endereco endereco;
        private String tipo;
        private String telefone1;
        private String telefone2;
        private String email;
        private String observacao;
        private boolean status;
        private String complemento;

        public PessoaFisicaBuilder() {
        }

        public PessoaFisicaBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public PessoaFisicaBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public PessoaFisicaBuilder setRg(String rg) {
            this.rg = rg;
            return this;
        }

        public PessoaFisicaBuilder setCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public PessoaFisicaBuilder setDataDeNascimento(String dataDeNascimento) {
            this.dataDeNascimento = dataDeNascimento;
            return this;
        }

        public PessoaFisicaBuilder setEndereco(Endereco endereco) {
            this.endereco = endereco;
            return this;
        }

        public PessoaFisicaBuilder setTipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public PessoaFisicaBuilder setTelefone1(String telefone1) {
            this.telefone1 = telefone1;
            return this;
        }

        public PessoaFisicaBuilder setTelefone2(String telefone2) {
            this.telefone2 = telefone2;
            return this;
        }

        public PessoaFisicaBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public PessoaFisicaBuilder setObservacao(String observacao) {
            this.observacao = observacao;
            return this;
        }

        public PessoaFisicaBuilder setStatus(boolean status) {
            this.status = status;
            return this;
        }

        public PessoaFisicaBuilder setComplemento(String complemento) {
            this.complemento = complemento;
            return this;
        }

        public PessoaFisica createPessoaFisica() {
            return new PessoaFisica(this);
        }

    }

    @Override
    public String toString() {
        return nome;
    }
    
    
    

}

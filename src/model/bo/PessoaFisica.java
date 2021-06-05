package model.bo;

public class PessoaFisica extends Pessoa {
    
    private int id;
    private String nome;
    private String rg;
    private String cpf;
    private String dataDeNascimento;
    private Endereco endereco;
    private String tipo;

    public PessoaFisica() {
    }

    public PessoaFisica(int id, String nome, String rg, String cpf, String dataDeNascimento, String complemento, String telefone1, String telefone2, String email, String observacao, Endereco endereco, String tipo, boolean status) {
        super(telefone1, telefone2, email, observacao, status);
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    
    @Override
    public String toString() {
        return  
                id + " - " + nome;
    }

    
}

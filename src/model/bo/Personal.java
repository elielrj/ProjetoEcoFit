
package model.bo;

public class Personal extends Pessoa{
    
    private int id;
    private String nome;
    private String dataDeNascimento;
    private String rg;
    private String cpf;
    
    private Endereco endereco;
    

    public Personal() {
    }

    public Personal(int id, String nome,  String dataDeNascimento, String rg, String cpf, String complemento, String telefone1, String telefone2, String email, String observacao, Endereco endereco) {
        super(complemento, telefone1, telefone2, email, observacao);
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;
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

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
        @Override
    public String toString() {
        return  this.getId() + " - Dt Nasc " +
                
                this.getDataDeNascimento() + " - End  " + 
                this.getEndereco().toString();
    }
    
}

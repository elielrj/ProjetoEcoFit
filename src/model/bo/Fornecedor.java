
package model.bo;

public class Fornecedor extends Pessoa{
    
    private int id;
    private String razaoSocial;
    private String inscricaoEstadual;
    private String cnpj;
    private String dataDeNascimento;
    private Endereco endereco;
    private String tipo;
    private String observaca;

    public Fornecedor(int id, String razaoSocial, String incricaoEstadual, String cnpj, String dataDeNascimento, Endereco endereco, String tipo, String telefone1, String telefone2, String email, String observacao, boolean status) {
        super(telefone1, telefone2, email, observacao, status);
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.inscricaoEstadual = incricaoEstadual;
        this.cnpj = cnpj;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.tipo = tipo;
    }

    public Fornecedor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getObservaca() {
        return observaca;
    }

    public void setObservaca(String observaca) {
        this.observaca = observaca;
    }

  
    
    @Override
    public String toString() {
        return this.getCnpj()+ this.getRazaoSocial();
    }
    
}


package model.bo;

public class Fornecedor extends Pessoa {
    private int id;
    private String razaoSocial;
    private String cnpj;
    private String inscricaoEstadual;
    
    private Endereco endereco;

    public Fornecedor() {
    }

    public Fornecedor(int id, String razaoSocial, String cnpj, String inscricaoEstadual, String complemento, String telefone1, String telefone2, String email, String observacao, Endereco endereco) {
        super(complemento, telefone1, telefone2, email, observacao);
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.endereco = endereco;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return  
                this.getId() + " - " +
                this.getRazaoSocial() + " " +
                this.getCnpj();
    }
   
    
}

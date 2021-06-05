
package model.bo;
class Fornecedor extends Pessoa{
    
    private int id;
    private String razaoSocial;
    private String incricaoestadual;
    private String cnpj;
    private String dataDeNascimento;
    private Endereco endereco;
    private String tipo;
    private String observaca;

    public Fornecedor(int id, String razaoSocial, String incricaoestadual, String cnpj, String dataDeNascimento, Endereco endereco, String tipo, String telefone1, String telefone2, String email, String observacao, boolean status) {
        super(telefone1, telefone2, email, observacao, status);
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.incricaoestadual = incricaoestadual;
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

    public String getNome() {
        return razaoSocial;
    }

    public void setNome(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getRg() {
        return incricaoestadual;
    }

    public void setRg(String incricaoestadual) {
        this.incricaoestadual = incricaoestadual;
    }

    public String getCpf() {
        return cnpj;
    }

    public void setCpf(String cnpj) {
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

    @Override
    public String toString() {
        return this.getCpf()+ this.getNome();
    }

    public String getObservaca() {
        return observaca;
    }

    public void setObservaca(String observaca) {
        this.observaca = observaca;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getIncricaoestadual() {
        return incricaoestadual;
    }

    public void setIncricaoestadual(String incricaoestadual) {
        this.incricaoestadual = incricaoestadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    
}

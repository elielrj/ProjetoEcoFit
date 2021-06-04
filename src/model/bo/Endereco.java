
package model.bo;


public class Endereco {
    private int id;
    
    private String logradouro;
    //private String observacao;
    private String numero;
    private String complemento;
    private Cidade cidade;
    private Bairro bairro;
    private String cep;
    private boolean status;

    public Endereco() {
    }

    public Endereco(int id, String logradouro, String numero, String complemento, Bairro bairro, Cidade cidade, String cep, boolean status) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.status = status;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return id + " - Rua " + logradouro + ", " + numero + ", Compl. " + complemento + ", Bairro " + bairro + "Cidade " + getCidade().getNome() + ", CEP " + cep;
    }

    

    
}

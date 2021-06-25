package model.bo;

public abstract class Pessoa {

    private String telefone1;
    private String telefone2;
    private String email;
    private String observacao;
    private boolean status;

    public Pessoa() {
    }

    public Pessoa(String telefone1, String telefone2, String email, String observacao, boolean status) {
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.email = email;
        this.observacao = observacao;
        this.status = status;
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

    @Override
    public String toString() {
        return this.getTelefone1() + " "
                + this.getTelefone2() + " "
                + this.getEmail() + " "
                + this.getObservacao();
    }

}

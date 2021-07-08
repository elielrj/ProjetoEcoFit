package model.bo;

public class Cidade {

    private int id;
    private String nome;
    private boolean status;

    public Cidade() {
    }

    public Cidade(int id, String nome, boolean status) {
        this(nome,status);
        this.id = id;
    }

    public Cidade(String nome, boolean status) {
        this.nome = nome;
        this.status = status;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getNome();
    }

}

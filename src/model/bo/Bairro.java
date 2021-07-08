package model.bo;

public class Bairro {

    private int id;
    private String nome;
    private boolean status;

    public Bairro() {
    }
    
    public Bairro(String nome, boolean status) {
        this.nome = nome;
        this.status = status;
    }
    
    public Bairro(int id, String nome, boolean status) {
        this(nome,status);
        this.id = id;
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
    
}

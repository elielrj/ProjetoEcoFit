package model.bo;

public class Cidade {

    private int id;
    private String nome;
    private boolean status;

    public Cidade(CidadeBuilder cidadeBuilder) {
        this.id = cidadeBuilder.id;
        this.nome = cidadeBuilder.nome;
        this.status = cidadeBuilder.status;
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
        return nome;
    }
    
    public static class CidadeBuilder {

        private int id;
        private String nome;
        private boolean status;

        public CidadeBuilder() {
        }

        public CidadeBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public CidadeBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }
        
        public CidadeBuilder setStatus(boolean status) {
            this.status = status;
            return this;
        }
        

        public Cidade createCidade() {
            return new Cidade(this);
        }
    
    }
}

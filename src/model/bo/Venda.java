package model.bo;

public class Venda {

    private int id;
    private String data;
    private String hora;
    private String dataDeVencimento;
    private String observacao;
    private float valorDoDesconto;
    private float valorTotal;
    private boolean status;
    private PessoaFisica pessoaFisica;

    public Venda() {
    }

    public Venda(int id, String data, String hora, String dataDeVencimento, String observacao, float valorDoDesconto, float valorTotal, PessoaFisica pessoaFisica, boolean status) {
        this(data, hora, dataDeVencimento, observacao, valorDoDesconto, valorTotal, pessoaFisica, status);
        this.id = id;
    }

    public Venda(String data, String hora, String dataDeVencimento, String observacao, float valorDoDesconto, float valorTotal, PessoaFisica pessoaFisica, boolean status) {
        this.data = data;
        this.hora = hora;
        this.dataDeVencimento = dataDeVencimento;
        this.observacao = observacao;
        this.valorDoDesconto = valorDoDesconto;
        this.valorTotal = valorTotal;
        this.pessoaFisica = pessoaFisica;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(String dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public float getValorDoDesconto() {
        return valorDoDesconto;
    }

    public void setValorDoDesconto(float valorDoDesconto) {
        this.valorDoDesconto = valorDoDesconto;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.getId() + " "
                + this.getData() + " "
                + this.getHora() + " "
                + this.getDataDeVencimento() + " "
                + this.getObservacao() + " "
                + this.getValorDoDesconto() + " "
                + this.getValorTotal() + " "
                + this.getPessoaFisica().getNome();
    }

}

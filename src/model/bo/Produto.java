package model.bo;

public class Produto {

    private int id;//1
    private String descricao;//2
    private String unidadeDeCompra;//3
    private String unidadeDeVenda;//4
    private String correlacaoUnidade;//5
    private float valor;//6
    private String codigoDeBarras;//7
    private boolean status;//8
    private String observacao;//9

    private Produto(ProdutoBuilder produtoBuilder) {
        this.id = produtoBuilder.id;
        this.descricao = produtoBuilder.descricao;
        this.unidadeDeCompra = produtoBuilder.unidadeDeCompra;
        this.unidadeDeVenda = produtoBuilder.unidadeDeVenda;
        this.correlacaoUnidade = produtoBuilder.correlacaoUnidade;
        this.valor = produtoBuilder.valor;
        this.codigoDeBarras = produtoBuilder.codigoDeBarras;
        this.status = produtoBuilder.status;
        this.observacao = produtoBuilder.observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidadeDeCompra() {
        return unidadeDeCompra;
    }

    public void setUnidadeDeCompra(String unidadeDeCompra) {
        this.unidadeDeCompra = unidadeDeCompra;
    }

    public String getUnidadeDeVenda() {
        return unidadeDeVenda;
    }

    public void setUnidadeDeVenda(String unidadeDeVenda) {
        this.unidadeDeVenda = unidadeDeVenda;
    }

    public String getCorrelacaoUnidade() {
        return correlacaoUnidade;
    }

    public void setCorrelacaoUnidade(String correlacaoUnidade) {
        this.correlacaoUnidade = correlacaoUnidade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public static class ProdutoBuilder {

        private int id;
        private String descricao;
        private String unidadeDeCompra;
        private String unidadeDeVenda;
        private String correlacaoUnidade;
        private float valor;
        private String codigoDeBarras;
        private boolean status;
        private String observacao;

        public ProdutoBuilder() {
        }

        public ProdutoBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ProdutoBuilder setDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public ProdutoBuilder setUnidadeDeCompra(String unidadeDeCompra) {
            this.unidadeDeCompra = unidadeDeCompra;
            return this;
        }

        public ProdutoBuilder setUnidadeDeVenda(String unidadeDeVenda) {
            this.unidadeDeVenda = unidadeDeVenda;
            return this;
        }

        public ProdutoBuilder setCorrelacaoUnidade(String correlacaoUnidade) {
            this.correlacaoUnidade = correlacaoUnidade;
            return this;
        }

        public ProdutoBuilder setValor(float valor) {
            this.valor = valor;
            return this;
        }

        public ProdutoBuilder setCodigoDeBarras(String codigoDeBarras) {
            this.codigoDeBarras = codigoDeBarras;
            return this;
        }

        public ProdutoBuilder setStatus(boolean status) {
            this.status = status;
            return this;
        }

        public ProdutoBuilder setObservacao(String observacao) {
            this.observacao = observacao;
            return this;
        }

        public Produto createProduto() {
            return new Produto(this);
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public static class ContaAPagarBuilder {

    private int id;
    private int vendaId;
    private float valor;
    private boolean status;

    public ContaAPagarBuilder() {
    }

    public ContaAPagarBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ContaAPagarBuilder setVendaId(int vendaId) {
        this.vendaId = vendaId;
        return this;
    }

    public ContaAPagarBuilder setValor(float valor) {
        this.valor = valor;
        return this;
    }

    public ContaAPagarBuilder setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public ContaAPagar createContaAPagar() {
        return new ContaAPagar(id, vendaId, valor, status);
    }
    
}

package model.bo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JTable;

public class Faturamento {

    private String hora;
    private String data;
    private String usuario;
    protected ArrayList<ItemDeVenda> listaDeItens;
    private JTable jTable;

    private int contador;

    public Faturamento() {
        this.listaDeItens = new ArrayList<>();
        this.dataHora();
        this.usuario = "Eliel";

    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public ArrayList<ItemDeVenda> getListaDeItens() {
        return listaDeItens;
    }

    public void adicionar(ItemDeVenda item) {
        for(ItemDeVenda i: this.listaDeItens){
            if (item.getProduto().getId() == i.getProduto().getId()){
                i.setQuantidade(item.getQuantidade()+i.getQuantidade());
                return;
            }
        }
        listaDeItens.add(item);
    }

    public JTable getjTable() {
        return jTable;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    private void dataHora() {

        // data/hora atual
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        this.setData(formatterData.format(agora));

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.setHora(formatterHora.format(agora));

    }

    public float valorTotal() {
        float total = 0;
        for (ItemDeVenda item : this.getListaDeItens()) {
            total += item.getQuantidade() * item.getValor();
        }
        return total;
    }

    public static String semMascara(String dado) {
        dado = dado.replaceAll("\\.", "");
        return dado;
    }
}

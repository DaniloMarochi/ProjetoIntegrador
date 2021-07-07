package projetointegrador.models;

public class Venda {

    public int id;
    public int qtd_artesanato;
    public float valor_total;
    public int id_cliente;
    public int id_artesanato;

    public Venda(int id, int qtd_artesanato, float valor_total, int id_cliente, int id_artesanato) {
        this.id = id;
        this.qtd_artesanato = qtd_artesanato;
        this.valor_total = valor_total;
        this.id_cliente = id_cliente;
        this.id_artesanato = id_artesanato;
    }

    public Venda(int qtd_artesanato, float valor_total, int id_cliente, int id_artesanato) {
        this.qtd_artesanato = qtd_artesanato;
        this.valor_total = valor_total;
        this.id_cliente = id_cliente;
        this.id_artesanato = id_artesanato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtd_artesanato() {
        return qtd_artesanato;
    }

    public void setQtd_artesanato(int qtd_artesanato) {
        this.qtd_artesanato = qtd_artesanato;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_artesanato() {
        return id_artesanato;
    }

    public void setId_artesanato(int id_artesanato) {
        this.id_artesanato = id_artesanato;
    }

    @Override
    public String toString() {
        return
                "\n Quantidade Artesanato: " + qtd_artesanato +
                "\n Valor Total: " + valor_total +
                "\n ID Cliente: " + id_cliente +
                "\n ID Artesanato: " + id_artesanato+ "\n";
    }
}

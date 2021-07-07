package projetointegrador.models;

public class ArtesanatoHasVenda {

    private int id;
    private int id_artesanato;
    private int id_venda;

    public ArtesanatoHasVenda(int id, int id_artesanato, int id_venda) {
        this.id = id;
        this.id_artesanato = id_artesanato;
        this.id_venda = id_venda;
    }

    public ArtesanatoHasVenda(int id_artesanato, int id_venda) {
        this.id_artesanato = id_artesanato;
        this.id_venda = id_venda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_artesanato() {
        return id_artesanato;
    }

    public void setId_artesanato(int id_artesanato) {
        this.id_artesanato = id_artesanato;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    @Override
    public String toString() {
        return "ArtesanatoHasVenda{" +
                "id=" + id +
                ", id_artesanato=" + id_artesanato +
                ", id_venda=" + id_venda +
                '}';
    }
}

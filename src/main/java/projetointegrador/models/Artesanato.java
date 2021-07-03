package projetointegrador.models;

public class Artesanato {

    public int id;
    public String tamanho;
    public String material;
    public String categoria;
    public float valor;
    public int estoque;

    public Artesanato(int id, String tamanho, String material, String categoria, float valor, int estoque) {
        this.id = id;
        this.tamanho = tamanho;
        this.material = material;
        this.categoria = categoria;
        this.valor = valor;
        this.estoque = estoque;
    }

    public Artesanato(String tamanho, String material, String categoria, float valor, int estoque) {
        this.id = -1;
        this.tamanho = tamanho;
        this.material = material;
        this.categoria = categoria;
        this.valor = valor;
        this.estoque = estoque;
    }

    public int getId() {
        return id;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "Artesanato{" +
                "id=" + id +
                ", tamanho='" + tamanho + '\'' +
                ", material='" + material + '\'' +
                ", categoria='" + categoria + '\'' +
                ", valor=" + valor +
                ", estoque=" + estoque +
                '}';
    }
}

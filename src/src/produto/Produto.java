package produto;

public class Produto {
    private long id;
    private String descricao;
    private String marca;
    private double preco;

    public Produto(){}
    
    public Produto(long id, String descricao, String marca, double preco){
        this.id = id;
        this.descricao = descricao;
        this.marca = marca;
        this.preco = preco;
                
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
  
}

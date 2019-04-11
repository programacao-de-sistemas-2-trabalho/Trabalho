public class Aplicativo {
    private long id;
    private String nome;
    private String desenvolvedor;
    private int numerodowloads;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(String desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    public int getNumerodowloads() {
        return numerodowloads;
    }

    public void setNumerodowloads(int numerodowloads) {
        this.numerodowloads = numerodowloads;
    }
}

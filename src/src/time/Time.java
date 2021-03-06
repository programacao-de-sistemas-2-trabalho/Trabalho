package time;

public class Time {
   private long id;
   private String nome;
   private int anodefundacao;
   private String cidade;
   private String estado;

    public Time(){}
    
    public Time(long id, String nome, int anodefundacao, String cidade, String estado){
        this.id = id;
        this.nome = nome;
        this.anodefundacao = anodefundacao;
        this.cidade = cidade;
        this.estado = estado;
                
    }

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

    public int getAnodefundacao() {
        return anodefundacao;
    }

    public void setAnodefundacao(int anodefundacao) {
        this.anodefundacao = anodefundacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    

}

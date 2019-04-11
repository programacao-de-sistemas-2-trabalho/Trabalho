import java.util.List;


public interface CRUDProduto {
    
    public boolean save(Produto produto);

    public boolean update(Produto produto);

    public boolean delete(Produto produto);

    public Time get(long id);

    public List<Produto> getAll();
}

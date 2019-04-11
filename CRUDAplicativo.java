import java.util.List;


public interface CRUDAplicativo {
    
    public boolean save(Aplicativo aplicativo);

    public boolean update(Aplicativo aplicativo);

    public boolean delete(Aplicativo aplicativo);

    public Time get(long id);

    public List<Aplicativo> getAll();
}

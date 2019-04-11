import java.util.*;

public interface CRUDTime {

    public boolean save(Time time);

    public boolean update(Time time);

    public boolean delete(Time time);

    public Time get(long id);

    public List<Time> getAll();
}

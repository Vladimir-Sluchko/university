package dao.api;

import java.util.List;

public interface ICRUDao<T,ID> {
    T create (T item);
    List<T> getAll();
    T update(ID id,T item);
    void delete(ID id);
}

package dao.api;

import java.util.List;

public interface ICRUDao<T,ID> {
    T create (T item);
    List<T> get();
    T update(ID id,T iteam);
    void delete(ID id);
}

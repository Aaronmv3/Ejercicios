package dao;

import java.util.List;

public interface DAO<T> {
    
    T get(int id);    
    List<T> getAll();     
    public boolean save(T t);     
    public boolean update(T t);     
    public boolean delete(T t);
}

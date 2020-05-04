package aufgabe_2.repositories.services;

import java.util.Collection;

public interface Repository <T, K>{
    
    Collection<T> getAll();
    T getById(K id);
    T create(T entity);
    T update(T entity);
    T delete(T entity);
}
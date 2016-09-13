package ua.goit.java.DAO;

import java.util.List;

/**
 * Created by 7 on 22.08.2016.
 */
public interface TableDAO<T> {

    void save(T t);

    void remove(T t);

    List<T> findAll();

    T findByName();

}

package ua.goit.java.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.goit.java.Model.CategoryOfDishes;

import java.util.List;

/**
 * Created by 7 on 22.08.2016.
 */
public class CategoryDAO implements TableDAO<CategoryOfDishes>{

    private SessionFactory sessionFactory;

    @Override
    public void save(CategoryOfDishes categoryOfDishes) {
        Session session = sessionFactory.getCurrentSession();
        session.save(categoryOfDishes);

    }

    @Override
    public void remove(CategoryOfDishes categoryOfDishes) {
        sessionFactory.getCurrentSession().delete(categoryOfDishes);
    }

    @Override
    public List<CategoryOfDishes> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from CategoryOfDishes e").list();
    }

    @Override
    public CategoryOfDishes findByName() {

        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

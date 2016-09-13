package ua.goit.java.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.goit.java.tables.Position;
import ua.goit.java.tables.Positions;

import java.util.List;

/**
 * Created by 7 on 21.08.2016.
 */
public class PositionsDAO {

    private SessionFactory sessionFactory;

    public void save(Positions position) {
        Session session = sessionFactory.getCurrentSession();
        session.save(position);
    }

    public void remove(Positions position) {
        sessionFactory.getCurrentSession().delete(position);

    }

    public Positions findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Positions e where e.ID = :id");
        query.setParameter("id", id);

        return (Positions) query.uniqueResult();
    }

    public List<Positions> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from Positions e").list();
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

package ua.goit.java.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.goit.java.tables.Menu;

import java.util.List;
import java.util.Scanner;

/**
 * Created by 7 on 23.08.2016.
 */
public class MenuDAO implements TableDAO<Menu> {

    private DishToOrderDAO dishToOrderDAO = new DishToOrderDAO();

    private SessionFactory sessionFactory;

    @Override
    public void save(Menu menu) {
        Session session = sessionFactory.getCurrentSession();
        session.save(menu);
    }

    @Override
    public void remove(Menu menu) {
        sessionFactory.getCurrentSession().delete(menu);
    }

    @Override
    public Menu findByName() {

        Session session = sessionFactory.getCurrentSession();
        System.out.println("Введите название :");
        String name = new Scanner(System.in).nextLine();
        Query query = session.createQuery("select m from Menu m where m.name like :name");
        query.setParameter("name", name);

        return (Menu) query.uniqueResult();

    }

    @Override
    public List<Menu> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from Menu m");
        return query.list();
    }

    static int ID_MAX;



    public void enteringInformation(Menu menu) {

        Scanner scanner = new Scanner(System.in);


        System.out.println("Введите название меню :");
        String name = scanner.nextLine();
        menu.setName(name);

    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

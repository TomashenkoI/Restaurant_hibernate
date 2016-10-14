package ua.goit.java.DAO;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.Model.Ingredient;

import java.util.List;
import java.util.Scanner;

/**
 * Created by 7 on 22.08.2016.
 */
public class IngredientsDAO implements TableDAO<Ingredient> {

    private SessionFactory sessionFactory;

    @Override
    public void save(Ingredient ingredient) {
        Session session = sessionFactory.getCurrentSession();
        session.save(ingredient);
    }

    @Override
    public void remove(Ingredient ingredient) {
        sessionFactory.getCurrentSession().delete(ingredient);
    }

    @Override
    @Transactional
    public List<Ingredient> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select i from Ingredient i").list();
    }

    @Override
    public Ingredient findByName() {

        Session session = sessionFactory.getCurrentSession();
        System.out.println("Введите название :");
        String name = new Scanner(System.in).nextLine();
        Query query = session.createQuery("select i from Ingredient i where i.name like :name");
        query.setParameter("name", name);

        return (Ingredient) query.uniqueResult();

    }

    public void enteringInformation(Ingredient ingredient) {

        System.out.println("Введите название ингридиента");
        String name = new Scanner(System.in).nextLine();
        ingredient.setName(name);
        save(ingredient);
    }


    public Ingredient getById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Ingredient i where i.ID = :id");
        query.setParameter("id", id);

        return (Ingredient) query.uniqueResult();
    }

    public int getIngredientIdByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i.ID from Ingredient i where i.name = :name");
        query.setParameter("name", name);

        return (int) query.uniqueResult();

    }

    @Transactional
    public int getMaxId() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session
                .createCriteria(Ingredient.class)
                .setProjection(Projections.max("id"));
        return (Integer)criteria.uniqueResult();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

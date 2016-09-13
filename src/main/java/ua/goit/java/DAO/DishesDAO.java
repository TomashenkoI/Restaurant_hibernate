package ua.goit.java.DAO;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.Controller.DishController;
import ua.goit.java.Controller.ListOfIngredientsController;
import ua.goit.java.tables.Dish;

import java.util.List;
import java.util.Scanner;

/**
 * Created by 7 on 21.08.2016.
 */
public class DishesDAO implements TableDAO<Dish> {

    private SessionFactory sessionFactory;
    private DishController dishController;

    public void save(Dish dish) {
        Session session = sessionFactory.getCurrentSession();
        session.save(dish);
    }

    @Transactional
    public void remove(Dish dish){
        sessionFactory.getCurrentSession().delete(dish);
    }

    @Transactional
    public List<Dish> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select d from Dish d").list();
    }

    public Dish findByName() {

        Session session = sessionFactory.getCurrentSession();
        System.out.println("Введите название: ");
        String name = new Scanner(System.in).nextLine();
        Query query = session.createQuery("select d from Dish d where d.name like :name");
        query.setParameter("name", name);

        return (Dish) query.uniqueResult();

    }

    @Transactional
    public Dish findById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Dish d where d.ID = :id");
        query.setParameter("id", id);

        return (Dish) query.uniqueResult();

    }

    @Transactional
    public int getMaxId() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session
                .createCriteria(Dish.class)
                .setProjection(Projections.max("id"));
        return (Integer)criteria.uniqueResult();
    }

    private ListOfIngredientsController listOfIngredientsController = new ListOfIngredientsController();

    public void enteringInformation(Dish dish) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выбирите категорию :");
        int categoryID = Integer.parseInt(scanner.nextLine());
        dish.setCategoryOfDishes_ID(categoryID);

        System.out.println("Введите название блюда :");
        String name = scanner.nextLine();
        dish.setName(name);

        System.out.println("Введите перечень ингредиентов :");
        listOfIngredientsController.createPosition();
        dish.setListOfIngredients_ID(dishController.getMaxId() + 1);

        System.out.println("Введите цену :");
        double price = Double.parseDouble(scanner.nextLine());
        dish.setPrice(price);

        System.out.println("Введите вес блюда :");
        int weight = Integer.parseInt(scanner.nextLine());
        dish.setWeight(weight);

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setListOfIngredientsController(ListOfIngredientsController listOfIngredientsController) {
        this.listOfIngredientsController = listOfIngredientsController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }
}

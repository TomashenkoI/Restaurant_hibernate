package ua.goit.java.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.Controller.DishesController;
import ua.goit.java.Controller.IngredientsController;
import ua.goit.java.Model.ListOfIngredients;

import java.util.List;
import java.util.Scanner;

/**
 * Created by 7 on 22.08.2016.
 */
public class ListOfIngredientsDAO implements TableDAO<ListOfIngredients> {

    private SessionFactory sessionFactory;

    IngredientsController ingredientsController;
    private DishesController dishesController;

    @Override
    public void save(ListOfIngredients listOfIngredients) {
        Session session = sessionFactory.getCurrentSession();
        session.save(listOfIngredients);

    }

    @Override
    public void remove(ListOfIngredients listOfIngredients) {
        sessionFactory.getCurrentSession().delete(listOfIngredients);

    }

    public void removeById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        final Query query = session.createQuery("delete from ListOfIngredients i where i.dishID = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public ListOfIngredients findByName() {
        return null;
    }

    @Override
    public List<ListOfIngredients> findAll() {
        return null;
    }

    @Transactional
    public List<ListOfIngredients> findByDishId(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select l from ListOfIngredients l where l.dishID = :id");
        query.setParameter("id", id);
        return query.list();
    }

    public void enteringInformation() {

        Scanner scanner = new Scanner(System.in);

        int id = dishesController.getMaxId() + 1;
        String enteredString;

        while (true) {
            ingredientsController.showAllPositions();
            System.out.println("Выбирите номер ингридиента или введите 'exit' :");
            enteredString = scanner.nextLine();
            if (enteredString.equals("exit")) break;
            ListOfIngredients listOfIngredients = new ListOfIngredients();
            listOfIngredients.setDishID(id);
            listOfIngredients.setIngredientID(Integer.parseInt(enteredString));
            save(listOfIngredients);
        }

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setIngredientsController(IngredientsController ingredientsController) {
        this.ingredientsController = ingredientsController;
    }

    public void setDishesController(DishesController dishesController) {
        this.dishesController = dishesController;
    }
}

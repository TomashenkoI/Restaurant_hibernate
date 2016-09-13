package ua.goit.java.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.goit.java.tables.Ingredient;
import ua.goit.java.tables.Storage;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by 7 on 23.08.2016.
 */
public class StorageDAO {

    private IngredientsDAO ingredientsDAO;
    private SessionFactory sessionFactory;


    public void save(Storage storage) {
        Session session = sessionFactory.getCurrentSession();
        session.save(storage);
    }

    public void remove(Storage storage) {
        sessionFactory.getCurrentSession().delete(storage);
    }

    public Storage findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from Storage s where s.ID = :id");
        query.setParameter("id", id);

        return (Storage) query.uniqueResult();
    }

    public List<Storage> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select s from Storage s").list();
    }


    public void enteringInformation() {

        Ingredient ingredient = new Ingredient();
        int enteredDigit;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите : "+ "\n" +"1 - для добавления ингридиента из списка" +
                            "\n2 - для добавления нового" +
                            "\n3 - для выхода");

        while (true) {
            enteredDigit = Integer.parseInt(scanner.nextLine());
            if (enteredDigit == 1 || enteredDigit == 2 || enteredDigit == 3) {
                break;
            } else {
                System.out.println("Неверный ввод. Повторите попытку !");
            }
        }

        int ingredientId = 0;
        int amount = 0;
        Storage storage = new Storage();

        if (enteredDigit == 1) {
            ingredientsDAO.findAll().forEach(System.out::println);
            System.out.println("Выбирите ингридиент из списка");
            ingredientId = Integer.parseInt(scanner.nextLine());
            storage.setIngredient_ID(ingredientId);
        }
        if (enteredDigit == 2) {
            ingredientsDAO.enteringInformation(ingredient);
            storage.setIngredient_ID(ingredientsDAO.getMaxId());
        }
        if (enteredDigit < 3) {
            System.out.println("Введите колличество :");
            amount = Integer.parseInt(scanner.nextLine());
            storage.setAmount(amount);
        }
        save(storage);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setIngredientsDAO(IngredientsDAO ingredientsDAO) {
        this.ingredientsDAO = ingredientsDAO;
    }
}

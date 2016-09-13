package ua.goit.java.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.goit.java.tables.CategoryOfDishes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.goit.java.DAO.Requests.*;

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

    public void showAllPositions() {

        List<CategoryOfDishes> list = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){
            String sql = "SELECT * FROM category_of_dishes";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
//                CategoryOfDishes categoryOfDishes = createObject(resultSet);
//                list.add(categoryOfDishes);
            }
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

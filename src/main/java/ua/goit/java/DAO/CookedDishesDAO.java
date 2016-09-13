package ua.goit.java.DAO;

import org.hibernate.SessionFactory;
import ua.goit.java.tables.CookedDishes;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by 7 on 23.08.2016.
 */
public class CookedDishesDAO implements TableDAO<CookedDishes>{

    private SessionFactory sessionFactory;

    public void enteringInformation(Statement statement) throws SQLException {

        OrdersDAO ordersDAO = new OrdersDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        DishesDAO dishesDAO = new DishesDAO();
        Scanner scanner = new Scanner(System.in);


//        dishesDAO.showAllPositions();
        System.out.println("Введите ID блюда :");
        int dishId = Integer.parseInt(scanner.nextLine());

//        employeeDAO.showAllCooks();
        System.out.println("Введите ID повара :");
        int cookId = Integer.parseInt(scanner.nextLine());

//        ordersDAO.showOpenedOrders();
        System.out.println("Введите ID заказа :");
        int orderId = Integer.parseInt(scanner.nextLine());

        Date date = new Date();

    }

    @Override
    public void save(CookedDishes cookedDishes) {

    }

    @Override
    public void remove(CookedDishes cookedDishes) {

    }

    @Override
    public CookedDishes findByName() {

        return null;
    }

    @Override
    public List<CookedDishes> findAll() {
        return null;
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

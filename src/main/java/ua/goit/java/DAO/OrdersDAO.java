package ua.goit.java.DAO;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.Controller.DishToOrderController;
import ua.goit.java.Controller.EmployeesController;
import ua.goit.java.Model.Orders;

import java.util.List;
import java.util.Scanner;

/**
 * Created by 7 on 23.08.2016.
 */
public class OrdersDAO {

    private EmployeesController employeesController;
    private DishToOrderController dishToOrderController;

    private SessionFactory sessionFactory;

    public void save(Orders orders) {
        Session session = sessionFactory.getCurrentSession();
        session.save(orders);
    }

    @Transactional
    public void remove(Orders orders) {
        sessionFactory.getCurrentSession().delete(orders);
    }

    public Orders findById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select o from Orders o where o.ID = :id");
        query.setParameter("id", id);

        return (Orders) query.uniqueResult();
    }


    public List<Orders> findAllOpenedOrders() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select o from Orders o where o.access = true").list();
    }

    public List<Orders> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select o from Orders o").list();
    }

    public int getMaxId(){
        Session session = sessionFactory.getCurrentSession();
        int result;
        Criteria criteria = session
                .createCriteria(Orders.class)
                .setProjection(Projections.max("id"));
        try {
            result = (Integer) criteria.uniqueResult();
        } catch (NullPointerException e) {
            result = 0;
        }
        System.out.println(result);
        return result;
    }

    @Transactional
    public void enteringInformation(Orders orders) {

        Requests requests = new Requests();

        Scanner scanner = new Scanner(System.in);

        employeesController.showAllWaiters();
        System.out.println("Введите ID оффицианта :");
        int waiterId = Integer.parseInt(scanner.nextLine());
        orders.setEmployee(employeesController.getPositionById(waiterId));

        dishToOrderController.createPosition();
        orders.setListOfDishes(getMaxId() + 1);

        System.out.println("Введите номер столика :");
        int tableNumber = Integer.parseInt(scanner.nextLine());
        orders.setTableNumber(tableNumber);

        String date = requests.getCurrentTime();
        orders.setDate(date);

        orders.setAccess(true);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setEmployeesController(EmployeesController employeesController) {
        this.employeesController = employeesController;
    }

    public void setDishToOrderController(DishToOrderController dishToOrderController) {
        this.dishToOrderController = dishToOrderController;
    }

}


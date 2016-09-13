package ua.goit.java.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.Controller.DishController;
import ua.goit.java.Controller.OrderController;
import ua.goit.java.tables.DishToOrder;

import java.util.List;
import java.util.Scanner;

/**
 * Created by 7 on 23.08.2016.
 */
public class DishToOrderDAO{

    private OrdersDAO ordersDAO;
    private OrderController orderController;
    private DishController dishController;

    private SessionFactory sessionFactory;

    @Transactional
    public void save(DishToOrder dishToOrder) {
        Session session = sessionFactory.getCurrentSession();
        session.save(dishToOrder);
    }

    public void remove(DishToOrder dishToOrder) {
        sessionFactory.getCurrentSession().delete(dishToOrder);
    }

    public List<DishToOrder> findAll() {
        return null;
    }

    public DishToOrder findByName() {
        return null;
    }

    @Transactional
    public void removeById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        final Query query = session.createQuery("delete from DishToOrder i where i.orderId = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void enteringInformation() {

        Scanner scanner = new Scanner(System.in);

        int id = orderController.getMaxId() + 1;
        String enteredString;

        for (;;) {
            dishController.showAllPositions();
            System.out.println("Выбирите номер блюда или введите 'exit' :");
            enteredString = scanner.nextLine();
            if (enteredString.equals("exit")) break;
            DishToOrder dishToOrder = new DishToOrder();
            dishToOrder.setOrderId(id);
            dishToOrder.setDishId(Integer.parseInt(enteredString));
            save(dishToOrder);
        }

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    public void setOrdersDAO(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }
}


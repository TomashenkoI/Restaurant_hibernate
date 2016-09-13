package ua.goit.java.Controller;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.DAO.OrdersDAO;
import ua.goit.java.tables.Orders;

import java.util.Scanner;

/**
 * Created by 7 on 11.09.2016.
 */
public class OrderController implements TableController {

    private OrdersDAO ordersDAO;
    private DishToOrderController dishToOrderController;

    @Override
    @Transactional
    public void createPosition() {
        Orders orders = new Orders();
        ordersDAO.enteringInformation(orders);
        ordersDAO.save(orders);
    }

    public int getMaxId() {
        int result = ordersDAO.getMaxId();
        return result;
    }

    @Override
    @Transactional
    public void deletePosition() {
        showAllPositions();
        System.out.println("Введите id заказа для удаления");
        int enteredId = Integer.parseInt(new Scanner(System.in).nextLine());
        dishToOrderController.deletePosition(enteredId);
        ordersDAO.remove(ordersDAO.findById(enteredId));
    }

    @Override
    public void findPositionByName() {

    }

    @Override
    @Transactional
    public void showAllPositions() {
        ordersDAO.findAll().forEach(System.out::println);
    }

    public void setOrdersDAO(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    public void setDishToOrderController(DishToOrderController dishToOrderController) {
        this.dishToOrderController = dishToOrderController;
    }
}

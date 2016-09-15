package ua.goit.java.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.Controller.DishController;
import ua.goit.java.Controller.EmployeeController;
import ua.goit.java.Controller.OrderController;
import ua.goit.java.tables.CookedDish;
import ua.goit.java.tables.ListOfIngredients;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by 7 on 23.08.2016.
 */
public class CookedDishesDAO implements TableDAO<CookedDish>{

    private DishController dishController;
    private EmployeeController employeeController;
    private OrderController orderController;
    private ListOfIngredientsDAO listOfIngredientsDAO;
    private StorageDAO storageDAO;
    private IngredientsDAO ingredientsDAO;

    private SessionFactory sessionFactory;


    @Override
    public void save(CookedDish cookedDish) {
        Session session = sessionFactory.getCurrentSession();
        session.save(cookedDish);
    }

    @Override
    public void remove(CookedDish cookedDish) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(cookedDish);
    }

    @Override
    public CookedDish findByName() {

        Session session = sessionFactory.getCurrentSession();
        System.out.println("Введите название блюда: ");
        String enteredName = new Scanner(System.in).nextLine();
        Query query = session.createQuery("select c from CookedDish c where c.dish = :name");
        query.setParameter("name", enteredName);

        return (CookedDish) query.uniqueResult();
    }

    @Override
    public List<CookedDish> findAll() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CookedDish c");

        return query.list();

    }

    @Transactional
    public void enteringInformation(CookedDish cookedDish) {

        Scanner scanner = new Scanner(System.in);

        Requests requests = new Requests();

        dishController.showAllPositions();
        System.out.println("Введите ID блюда :");
        int dishId = Integer.parseInt(scanner.nextLine());
        cookedDish.setDishId(dishId);

        if (enoughIngredients(cookedDish)) {
            employeeController.showAllCooks();
            System.out.println("Введите ID повара :");
            int cookId = Integer.parseInt(scanner.nextLine());
            cookedDish.setEmployee(employeeController.getPositionById(cookId));

            orderController.showOpenedOrders();
            System.out.println("Введите ID заказа :");
            int orderId = Integer.parseInt(scanner.nextLine());
            cookedDish.setOrderId(orderId);

            String date = requests.getCurrentTime();
            cookedDish.setDate(date);

            save(cookedDish);
        }

    }

    @Transactional
    private boolean enoughIngredients(CookedDish cookedDish) {
        boolean flag = true;

        List<ListOfIngredients> list = listOfIngredientsDAO.findByDishId(cookedDish.getDishId());
        ArrayList<Integer> neededIngredients = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            neededIngredients.add(list.get(i).getIngredientID());

            int amount = storageDAO.findById(neededIngredients.get(i)).getAmount();
            if (amount <= 3) {
                String ingredient = ingredientsDAO.getById(list.get(i).getIngredientID()).getName();
                if (amount == 0) {
                    System.out.println("Нехватает ингрединта для приготовления блюда: " + ingredient);
                    flag = false;
                }
                if (flag) {
                    System.out.println("Заканчивается ингредиент на складе: " + ingredient);
                } else {
                    break;
                }
            }

        }
        if (flag) {
            for (int i = 0; i < neededIngredients.size(); i++) {
                storageDAO.decreaseIngredientAmountById(neededIngredients.get(i));
            }
        }

        return flag;
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void setListOfIngredientsDAO(ListOfIngredientsDAO listOfIngredientsDAO) {
        this.listOfIngredientsDAO = listOfIngredientsDAO;
    }

    public void setStorageDAO(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    public void setIngredientsDAO(IngredientsDAO ingredientsDAO) {
        this.ingredientsDAO = ingredientsDAO;
    }
}

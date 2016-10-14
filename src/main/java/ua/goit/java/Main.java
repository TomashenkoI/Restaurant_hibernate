package ua.goit.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.goit.java.Controller.*;
import ua.goit.java.DAO.IngredientsDAO;

import java.util.Scanner;

public class Main {

    private EmployeesController employeesController;
    private DishesController dishesController;
    private IngredientsController ingredientsController;
    private PositionController positionController;
    private OrderController orderController;
    private MenuController menuController;
    private StorageController storageController;
    private CookedDishesController cookedDishesController;
    private IngredientsDAO ingredientsDAO;

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml", "hibernate-context.xml");
        Main main = applicationContext.getBean(Main.class);
        main.test();

    }

    public void test() {
//        ingredientsController.showAllPositions();
        ingredientsDAO.findAll().forEach(System.out::println);
    }

    public void execution() {

        Scanner scanner = new Scanner(System.in);
        int number;

        while (true) {

            boolean flag = true;

            while (flag) {

                System.out.println();
                System.out.println("Выбирите раздел : ");
                System.out.println("1 : Сотрудники" + "\n" +
                        "2 : Блюда" + "\n" +
                        "3 : Меню" + "\n" +
                        "4 : Заказы " + "\n" +
                        "5 : Журнал блюд" + "\n" +
                        "6 : Склад" + "\n" +
                        "0 : Выйти из приложения ");
                number = Integer.parseInt(scanner.nextLine());
                if (number == 0) break;

                switch (number) {
                    case 1:
//                        System.out.println("Не работает !");
                        System.out.println("Выбирите действие :" + "\n" +
                                "1 : посмотреть всех сотрудников" + "\n" +
                                "2 : добавить сотрудника " + "\n" +
                                "3 : удалить сотрудника" + "\n" +
                                "4 : найти сотрудника по имени" + "\n" +
                                "0 : выход");
                        number = Integer.parseInt(scanner.nextLine());
                        flag = switchCases(flag, number, employeesController);
                        break;
                    case 2:
                        System.out.println("Выбирите действие :" + "\n" +
                                "1 : посмотреть все блюда" + "\n" +
                                "2 : добавить блюдо " + "\n" +
                                "3 : удалить блюдо" + "\n" +
                                "4 : найти блюдо по названию" + "\n" +
                                "0 : выход");
                        number = Integer.parseInt(scanner.nextLine());
                        flag = switchCases(flag, number, dishesController);
                        break;
                    case 3 :
                        System.out.println("Выбирите действие :" + "\n" +
                                "1 : посмотреть все меню" + "\n" +
                                "2 : добавить меню " + "\n" +
                                "3 : удалить меню" + "\n" +
                                "4 : найти меню по названию" + "\n" +
                                "0 : выход");
                        number = Integer.parseInt(scanner.nextLine());
                        flag = switchCases(flag, number, menuController);
                        break;
                    case 4 :
                        System.out.println("Выбирите действие :" + "\n" +
                                "1 : посмотреть все заказы" + "\n" +
                                "2 : добавить заказ " + "\n" +
                                "3 : удалить заказ" + "\n" +
                                "0 : выход");
                        number = Integer.parseInt(scanner.nextLine());
                        flag = switchCases(flag, number, orderController);
                        break;
                    case 5 :
                        System.out.println();
                        break;
                }
            }
        }
    }

    private static boolean switchCases(boolean flag, int number, TableController tableController) {
        switch (number) {
            case 1:
                tableController.showAllPositions();
                break;
            case 2:
                tableController.createPosition();
                break;
            case 3:
                tableController.deletePosition();
                break;
            case 4:
                tableController.findPositionByName();
                break;
            case 0:
                flag = false;
        }
        return flag;
    }

    public void setIngredientsController(IngredientsController ingredientsController) {
        this.ingredientsController = ingredientsController;
    }

    public void setEmployeesController(EmployeesController employeesController) {
        this.employeesController = employeesController;
    }

    public void setDishesController(DishesController dishesController) {
        this.dishesController = dishesController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void setStorageController(StorageController storageController) {
        this.storageController = storageController;
    }

    public void setCookedDishesController(CookedDishesController cookedDishesController) {
        this.cookedDishesController = cookedDishesController;
    }

    @Autowired
    public void setIngredientsDAO(IngredientsDAO ingredientsDAO) {
        this.ingredientsDAO = ingredientsDAO;
    }
}
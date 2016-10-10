package ua.goit.java.Controller;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.DAO.DishesDAO;
import ua.goit.java.Model.Dish;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by 7 on 07.09.2016.
 */
public class DishController implements TableController {

    private DishesDAO dishesDAO;
    private ListOfIngredientsController listOfIngredientsController;

    @Override
    @Transactional
    public void createPosition() {
        Set<Dish> dishes = new HashSet<Dish>(dishesDAO.findAll());

        Dish dish = new Dish();
        dishesDAO.enteringInformation(dish);

        if (!dishes.contains(dish)) {
            dishesDAO.save(dish);
        }
    }

    @Override
    @Transactional
    public void deletePosition() {
        showAllPositions();

        System.out.println("Введите id: ");
        int id = Integer.parseInt(new Scanner(System.in).nextLine());

        listOfIngredientsController.deletePosition(id);
        dishesDAO.remove(dishesDAO.findById(id));

    }

    @Override
    public void showAllPositions() {
        dishesDAO.findAll().forEach(System.out::println);
    }

    public void findPositionByName() {
        System.out.println(dishesDAO.findByName());
    }

    public int getMaxId() {
        int result = dishesDAO.getMaxId();
        return result;
    }

    public void setDishesDAO(DishesDAO dishesDAO) {
        this.dishesDAO = dishesDAO;
    }

    public void setListOfIngredientsController(ListOfIngredientsController listOfIngredientsController) {
        this.listOfIngredientsController = listOfIngredientsController;
    }
}

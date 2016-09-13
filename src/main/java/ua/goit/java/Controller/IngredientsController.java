package ua.goit.java.Controller;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.DAO.IngredientsDAO;
import ua.goit.java.tables.Ingredient;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by 7 on 11.09.2016.
 */
public class IngredientsController implements TableController{

    IngredientsDAO ingredientsDAO;

    @Override
    public void createPosition() {
        Set<Ingredient> allIngredients = new HashSet<Ingredient>(ingredientsDAO.findAll());

        Ingredient ingredient = new Ingredient();
        ingredientsDAO.enteringInformation(ingredient);

        if (!allIngredients.contains(ingredient)) {
            ingredientsDAO.save(ingredient);
        }
    }

    @Override
    public void deletePosition() {

        showAllPositions();

        System.out.println("Введите id: ");
        int id = Integer.parseInt(new Scanner(System.in).nextLine());

        ingredientsDAO.remove(ingredientsDAO.getById(id));
    }

    @Override
    public void findPositionByName() {
        System.out.println(ingredientsDAO.findByName());
    }

    @Override
    @Transactional
    public void showAllPositions() {
        ingredientsDAO.findAll().forEach(System.out::println);
    }

    public void setIngredientsDAO(IngredientsDAO ingredientsDAO) {
        this.ingredientsDAO = ingredientsDAO;
    }
}

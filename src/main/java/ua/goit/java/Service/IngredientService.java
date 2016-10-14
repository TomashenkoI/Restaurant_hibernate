package ua.goit.java.Service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.DAO.IngredientsDAO;
import ua.goit.java.DAO.ListOfIngredientsDAO;
import ua.goit.java.Model.Ingredient;
import ua.goit.java.Model.ListOfIngredients;

import java.util.ArrayList;
import java.util.List;

public class IngredientService {

    private ListOfIngredientsDAO listOfIngredientsDAO;
    private IngredientsDAO ingredientsDAO;

    @Transactional
    public void addNewIngredient(Ingredient ingredient){
        ingredientsDAO.save(ingredient);
    }

    @Transactional
    public List<Ingredient> getIngredientsToThisDish(int dishId) {

        List<ListOfIngredients> listOfIngredients = listOfIngredientsDAO.findByDishId(dishId);

        List<Ingredient> ingredients = new ArrayList<>();
        for (ListOfIngredients ingredientsToDish : listOfIngredients) {
            ingredients.add(ingredientsDAO.getById(ingredientsToDish.getIngredientID()));
        }

        return ingredients;

    }

    public List<Ingredient> getAllIngredients() {
        return ingredientsDAO.findAll();
    }

    public void setIngredientsDAO(IngredientsDAO ingredientsDAO) {
        this.ingredientsDAO = ingredientsDAO;
    }

    public void setListOfIngredientsDAO(ListOfIngredientsDAO listOfIngredientsDAO) {
        this.listOfIngredientsDAO = listOfIngredientsDAO;
    }
}

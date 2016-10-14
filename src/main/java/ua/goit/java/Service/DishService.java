package ua.goit.java.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.DAO.DishesDAO;
import ua.goit.java.DAO.IngredientsDAO;
import ua.goit.java.DAO.ListOfIngredientsDAO;
import ua.goit.java.Model.Dish;
import ua.goit.java.Model.DishCategory;
import ua.goit.java.Model.Ingredient;
import ua.goit.java.Model.ListOfIngredients;

import java.util.ArrayList;
import java.util.List;

public class DishService {

    private DishesDAO dishesDao;
    private ListOfIngredientsDAO listOfIngredientsDAO;
    private IngredientsDAO ingredientsDAO;

    public List<Dish> getDishes(){
        return dishesDao.findAll();
    }

    @Transactional
    public void updateDishInfo(int id, Dish dishWithNewInformation) {
        dishesDao.updateDish(id, dishWithNewInformation);
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

    @Transactional
    public int setListOfIngredients(List listOfIngredientsForTheDish) {

        int id = dishesDao.getMaxId() + 1;

        for (int i = 0; i < listOfIngredientsForTheDish.size(); i++) {

            ListOfIngredients listOfIngredients = new ListOfIngredients();
            listOfIngredients.setDishID(id);
            listOfIngredients.setIngredientID(ingredientsDAO.getIngredientIdByName(listOfIngredientsForTheDish.get(i).toString()));
            listOfIngredientsDAO.save(listOfIngredients);
        }

        return id;
    }

    @Transactional
    public Dish getDishByID(int dishId) {
        return dishesDao.findById(dishId);
    }

    @Transactional
    public void deleteDish(Dish dish) {
        dishesDao.remove(dish);
        listOfIngredientsDAO.removeById(dish.getID());
    }

    public List<Dish> getDishByName(String dishName) {
        return dishesDao.findByName(dishName);
    }

    @Transactional
    public void addNewDish(Dish dish) {
        dishesDao.save(dish);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientsDAO.findAll();
    }

    @Transactional
    public void setDishCategory(String category, Dish dish) {

        if (category.equals("Soup")) {
            dish.setDishCategory(DishCategory.SOUP);
        } else if (category.equals("Salad")) {
            dish.setDishCategory(DishCategory.SALAD);
        } else if (category.equals("Dessert")) {
            dish.setDishCategory(DishCategory.DESSERT);
        } else if (category.equals("Garnish")) {
            dish.setDishCategory(DishCategory.GARNISH);
        } else if (category.equals("Main course")) {
            dish.setDishCategory(DishCategory.MAIN_COURSE);
        }
    }

    @Transactional
    public Dish setInformation(String name, String category, int price, int weight, List listOfIngredients) {

        Dish dish = new Dish();

        dish.setName(name);
        dish.setPrice(price);
        dish.setWeight(weight);
        dish.setListOfIngredients_ID(setListOfIngredients(listOfIngredients));
        setDishCategory(category, dish);

        return dish;
    }

    public void setDishesDao(DishesDAO dishesDao) {
        this.dishesDao = dishesDao;
    }

    @Autowired
    public void setListOfIngredientsDAO(ListOfIngredientsDAO listOfIngredientsDAO) {
        this.listOfIngredientsDAO = listOfIngredientsDAO;
    }

    @Autowired
    public void setIngredientsDAO(IngredientsDAO ingredientsDAO) {
        this.ingredientsDAO = ingredientsDAO;
    }
}



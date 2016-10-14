package ua.goit.java.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.java.Model.Dish;
import ua.goit.java.Model.Ingredient;
import ua.goit.java.Service.DishService;
import ua.goit.java.Service.IngredientService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DishController{

    private DishService dishService;
    private IngredientService ingredientService;

    @RequestMapping(value = "/dishes", method = RequestMethod.GET)
    public ModelAndView employees(){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("dishes", dishService.getDishes());

        modelAndView.setViewName("dishes");

        return modelAndView;
    }

    @RequestMapping(value = "/findDishesByName", method = RequestMethod.GET)
    public ModelAndView findDishesByName(@RequestParam("dishName") String dishName) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("dishName", dishName);
        modelAndView.addObject("dishes", dishService.getDishByName(dishName));

        modelAndView.setViewName("dishes");

        return modelAndView;
    }

    @RequestMapping(value = "/dishes", method = RequestMethod.POST)
    public ModelAndView addNewDish(@RequestParam("selectedIngredients") ArrayList selectedIngredients,
                                   @RequestParam("dishName") String name,
                                   @RequestParam("dishCategory") String category,
                                   @RequestParam("dishPrice") int price,
                                   @RequestParam("dishWeight") int weight
                                   )
    {

        ModelAndView modelAndView = new ModelAndView();

        for (int i = 0; i < selectedIngredients.size(); i++) {
            System.out.println(selectedIngredients.get(i));
        }

        Dish dish = new Dish();
        dish.setName(name);
        dish.setPrice(price);
        dish.setWeight(weight);
        dish.setListOfIngredients_ID(dishService.setListOfIngredients(selectedIngredients));
        dishService.setDishCategory(category, dish);

        dishService.addNewDish(dish);

        modelAndView.addObject("dishes", dishService.getDishes());

        return modelAndView;
    }

    @RequestMapping(value = "/dishes/dishId={dishId}", method = RequestMethod.GET)
    public ModelAndView employee(@PathVariable int dishId){

        List<Ingredient> ingredientsToDish = dishService.getIngredientsToThisDish(dishId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dish", dishService.getDishByID(dishId));
        modelAndView.addObject("ingredientsToDish", ingredientsToDish);

        modelAndView.setViewName("dish");

        setPictureForEmployee(dishId, modelAndView);

        return modelAndView;
    }

    @RequestMapping(value = "/delete_dishId={dishID}", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable int dishID) {

        ModelAndView modelAndView = new ModelAndView();

        dishService.deleteDish(dishService.getDishByID(dishID));

        modelAndView.addObject("dishes", dishService.getDishes());

        modelAndView.setViewName("dishes");

        return modelAndView;
    }

    @RequestMapping(value = "/newDish", method = RequestMethod.GET)
    public ModelAndView addNewEmployee() {

        List<Ingredient> ingredients = dishService.getAllIngredients();
        boolean doesItAlreadyExist = false;

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("listOfIncludedIngredients", new ArrayList<>());
        modelAndView.addObject("doesItAlreadyExist", doesItAlreadyExist);
        modelAndView.addObject("ingredients", ingredients);

        modelAndView.setViewName("newDish");

        return modelAndView;
    }

    @RequestMapping(value = "/update_DishId={dishID}", method = RequestMethod.GET)
    public ModelAndView updateDish(@PathVariable int dishID) {

        ModelAndView modelAndView = new ModelAndView();

        boolean doesItAlreadyExist = true;
        modelAndView.addObject("doesItAlreadyExist", doesItAlreadyExist);

        modelAndView.addObject("dish", dishService.getDishByID(dishID));

        modelAndView.setViewName("newDish");

        return modelAndView;
    }

    @RequestMapping(value = "/updatedDishId={dishId}", method = RequestMethod.POST)
    public ModelAndView updateExistingEmployee(@RequestParam("selectedIngredients") ArrayList selectedIngredients,
                                               @RequestParam("dishName") String name,
                                               @RequestParam("dishCategory") String category,
                                               @RequestParam("dishPrice") int price,
                                               @RequestParam("dishWeight") int weight,
                                               @RequestParam("dishId") int dishId
    )
    {


        ModelAndView modelAndView = new ModelAndView();

        Dish dishWithNewInformation = dishService.setInformation(name, category, price, weight, selectedIngredients);

        dishService.updateDishInfo(dishId, dishWithNewInformation);

        modelAndView.addObject("employee", dishService.getDishByID(dishId));

        setPictureForEmployee(dishId, modelAndView);

        modelAndView.setViewName("dish");

        return modelAndView;
    }

    private void setPictureForEmployee(@PathVariable int dishId, ModelAndView modelAndView) {
        String path = "D:\\edu\\IdeaProjects\\Restaurant_MVC\\src\\main\\webapp\\resources\\images\\dishes\\id"+dishId+".jpg";
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) {
            path = "/images/dishes/id"+dishId+".jpg";
            modelAndView.addObject("picturePath", path);
        } else {
            path = "/images/dishes/default.jpg";
            modelAndView.addObject("picturePath", path);
        }
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }


}

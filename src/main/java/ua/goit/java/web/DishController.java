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

import java.io.File;
import java.util.List;

/**
 * Created by 7 on 22.09.2016.
 */
@Controller
public class DishController {

    private DishService dishService;

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
    public ModelAndView addNewDish(@RequestParam("dishName") String name,
                                   @RequestParam("dishCategory") String category,
                                   @RequestParam("dishPrice") int price,
                                   @RequestParam("dishWeight") int weight)
    {

        ModelAndView modelAndView = new ModelAndView();

        Dish dish = new Dish();
        dish.setName(name);
        dish.setPrice(price);
        dish.setWeight(weight);
        dish.setListOfIngredients_ID(1);

        dishService.setDishCategory(category, dish);

        dishService.addNewDish(dish);

        modelAndView.addObject("dishes", dishService.getDishes());

        return modelAndView;
    }

    @RequestMapping(value = "/dishes/dishId={dishId}", method = RequestMethod.GET)
    public ModelAndView employee(@PathVariable int dishId){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dish", dishService.getDishByID(dishId));

        modelAndView.setViewName("dish");

        List<Ingredient> ingredientsToDish = dishService.getIngredientsToThisDish(dishId);

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

        ModelAndView modelAndView = new ModelAndView();

        boolean doesItAlreadyExist = false;
        modelAndView.addObject("doesItAlreadyExist", doesItAlreadyExist);

        modelAndView.setViewName("newDish");

        return modelAndView;
    }
//
//    @RequestMapping(value = "/update_EmployeeId={employeeID}", method = RequestMethod.GET)
//    public ModelAndView updateEmployee(@PathVariable String employeeID) {
//
//        ModelAndView modelAndView = new ModelAndView();
//
//        boolean doesItAlreadyExist = true;
//        modelAndView.addObject("doesItAlreadyExist", doesItAlreadyExist);
//
//        modelAndView.addObject("employee", employeeService.getEmployeeByID(Integer.parseInt(employeeID)));
//
//        modelAndView.setViewName("newEmployee");
//
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/updatedEmployeeId={employeeId}", method = RequestMethod.POST)
//    public ModelAndView updateExistingEmployee(@PathVariable int employeeId,
//                                             @RequestParam("employeeName") String firstName,
//                                             @RequestParam("employeeSurname") String lastName,
//                                             @RequestParam("employeePhoneNumber") String phoneNumber,
//                                             @RequestParam("employeeDOB") String dateOfBirth,
//                                             @RequestParam("employeePosition") String position,
//                                             @RequestParam("employeeSalary") double salary)
//    {
//
//
//        ModelAndView modelAndView = new ModelAndView();
//
//        Employee employeeWithNewInformation = employeeService.setInformation(firstName, lastName, phoneNumber,dateOfBirth,
//                                                                             position, salary);
//
//        employeeService.updateEmployeeInfo(employeeId, employeeWithNewInformation);
//
//        modelAndView.addObject("employee", employeeService.getEmployeeByID(employeeId));
//
//        setPictureForEmployee(employeeId, modelAndView);
//
//        modelAndView.setViewName("employee");
//
//        return modelAndView;
//    }
//
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

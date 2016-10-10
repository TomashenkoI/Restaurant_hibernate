package ua.goit.java.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.java.Model.Employee;
import ua.goit.java.Service.EmployeeService;

import java.io.File;
import java.util.Map;

/**
 * Created by 7 on 22.09.2016.
 */
@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String employees(Map<String, Object> model){
        model.put("employees", employeeService.getEmployees());
        return "employees";
    }

    @RequestMapping(value = "/findEmployeeByName", method = RequestMethod.GET)
    public ModelAndView findEmployeesByName(@RequestParam("employeeName") String employeeName) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("employeeName", employeeName);
        modelAndView.addObject("employees", employeeService.getEmployeesByName(employeeName));
        modelAndView.setViewName("employees");

        return modelAndView;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public String addNewEmployee(Map<String, Object> model,
                                 @RequestParam("employeeName") String firstName,
                                 @RequestParam("employeeSurname") String lastName,
                                 @RequestParam("employeePhoneNumber") String phoneNumber,
                                 @RequestParam("employeeDOB") String dateOfBirth,
                                 @RequestParam("employeePosition") String position,
                                 @RequestParam("employeeSalary") double salary)
    {

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPhoneNumber(phoneNumber);
        employee.setDateOfBirth(dateOfBirth);
        employee.setSalary(salary);

        employeeService.setEmployeePosition(position, employee);

        employeeService.addNewEmployee(employee);

        model.put("employees", employeeService.getEmployees());

        return "employees";
    }


    @RequestMapping(value = "/employee/employeeId={employeeId}", method = RequestMethod.GET)
    public ModelAndView employee(@PathVariable int employeeId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employee", employeeService.getEmployeeByID(employeeId));

        modelAndView.setViewName("employee");

        setPictureForEmployee(employeeId, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/delete_employeeId={employeeID}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable String employeeID, Map<String, Object> model) {

        employeeService.deleteEmployee(employeeService.getEmployeeByID(Integer.parseInt(employeeID)));

        model.put("employees", employeeService.getEmployees());

        return "employees";
    }

    @RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
    public ModelAndView addNewEmployee() {

        ModelAndView modelAndView = new ModelAndView();

        boolean doesItAlreadyExist = false;
        modelAndView.addObject("doesItAlreadyExist", doesItAlreadyExist);

        modelAndView.setViewName("newEmployee");

        return modelAndView;
    }

    @RequestMapping(value = "/update_EmployeeId={employeeID}", method = RequestMethod.GET)
    public ModelAndView updateEmployee(@PathVariable String employeeID) {

        ModelAndView modelAndView = new ModelAndView();

        boolean doesItAlreadyExist = true;
        modelAndView.addObject("doesItAlreadyExist", doesItAlreadyExist);

        modelAndView.addObject("employee", employeeService.getEmployeeByID(Integer.parseInt(employeeID)));

        modelAndView.setViewName("newEmployee");

        return modelAndView;
    }

    @RequestMapping(value = "/updatedEmployeeId={employeeId}", method = RequestMethod.POST)
    public ModelAndView updateExistingEmployee(@PathVariable int employeeId,
                                             @RequestParam("employeeName") String firstName,
                                             @RequestParam("employeeSurname") String lastName,
                                             @RequestParam("employeePhoneNumber") String phoneNumber,
                                             @RequestParam("employeeDOB") String dateOfBirth,
                                             @RequestParam("employeePosition") String position,
                                             @RequestParam("employeeSalary") double salary)
    {


        ModelAndView modelAndView = new ModelAndView();

        Employee employeeWithNewInformation = employeeService.setInformation(firstName, lastName, phoneNumber,dateOfBirth,
                                                                             position, salary);

        employeeService.updateEmployeeInfo(employeeId, employeeWithNewInformation);

        modelAndView.addObject("employee", employeeService.getEmployeeByID(employeeId));

        setPictureForEmployee(employeeId, modelAndView);

        modelAndView.setViewName("employee");

        return modelAndView;
    }

    private void setPictureForEmployee(@PathVariable int employeeId, ModelAndView modelAndView) {
        String path = "D:\\edu\\IdeaProjects\\Restaurant_MVC\\src\\main\\webapp\\resources\\images\\employees\\id"+employeeId+".jpg";
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) {
            path = "/images/employees/id"+employeeId+".jpg";
            modelAndView.addObject("picturePath", path);
        } else {
            path = "/images/employees/default.jpg";
            modelAndView.addObject("picturePath", path);
        }
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

}

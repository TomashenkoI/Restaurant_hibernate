package ua.goit.java.Controller;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.DAO.EmployeeDAO;
import ua.goit.java.tables.Employee;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by 7 on 01.09.2016.
 */
public class EmployeeController implements TableController{

    private EmployeeDAO employeeDAO;

    @Transactional
    public void deletePosition() {

        showAllPositions();

        System.out.println("Введите id: ");
        int id = Integer.parseInt(new Scanner(System.in).nextLine());

        employeeDAO.remove(employeeDAO.findById(id));
    }

    @Transactional
    public void createPosition() {
        Set<Employee> allEmployees = new HashSet<Employee>(employeeDAO.findAll());

        Employee employee = new Employee();
        employeeDAO.enteringInformation(employee);

        if (!allEmployees.contains(employee)) {
            employeeDAO.save(employee);
        }
    }

    @Transactional
    public void showAllPositions(){
        employeeDAO.findAll().forEach(System.out::println);
    }

    @Transactional
    public void findPositionByName(){
        System.out.println(employeeDAO.findByName1());
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
}
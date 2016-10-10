package ua.goit.java.Service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.DAO.EmployeeDAO;
import ua.goit.java.Model.Employee;
import ua.goit.java.Model.Position;

import java.util.List;

public class EmployeeService {

    private EmployeeDAO employeeDao;

    public List<Employee> getEmployees(){
        return employeeDao.findAll();
    }

    @Transactional
    public List<Employee> getEmployeesByName(String employeeName) {
        return employeeDao.findByName1(employeeName);
    }

    @Transactional
    public void updateEmployeeInfo(int id, Employee employeeWithNewInformation) {
        employeeDao.updateEmployee(id, employeeWithNewInformation);
    }

    @Transactional
    public Employee getEmployeeByID(int id) {
        return employeeDao.findById(id);
    }

    @Transactional
    public void deleteEmployee(Employee employee) {
        employeeDao.remove(employee);
    }

    public void addNewEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    public Employee setInformation(String name, String surname, String phoneNumber,
                                   String dob, String position, double salary) {

        Employee employee = new Employee();
        employee.setFirstName(name);
        employee.setLastName(surname);
        employee.setPhoneNumber(phoneNumber);
        employee.setDateOfBirth(dob);
        employee.setSalary(salary);

        setEmployeePosition(position, employee);

        return employee;
    }

    public void setEmployeePosition(String position, Employee employee) {
        if (position.equals("Waiter")) {
            employee.setPosition(Position.WAITER);
        } else if (position.equals("Cook")) {
            employee.setPosition(Position.COOK);
        } else if (position.equals("Manager")) {
            employee.setPosition(Position.MANAGER);
        } else if (position.equals("Cleaner")) {
            employee.setPosition(Position.CLEANER);
        }
    }

    public void setEmployeeDao(EmployeeDAO employeeDao) {
        this.employeeDao = employeeDao;
    }
}

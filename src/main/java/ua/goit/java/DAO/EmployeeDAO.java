package ua.goit.java.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.Model.Employee;
import ua.goit.java.Model.Position;

import java.util.List;
import java.util.Scanner;

/**
 * Created by 7 on 20.08.2016.
 */
public class EmployeeDAO {

    private SessionFactory sessionFactory;

    @Transactional
    public void save(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }

    public void remove(Employee employee){
        sessionFactory.getCurrentSession().delete(employee);
    }

    public List<Employee> findByName1(String name) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.firstName like :name");
        query.setParameter("name", "%"+name+"%");

        return query.list();

    }

    @Transactional
    public List<Employee> findAllCooks() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.position = :position");
        query.setParameter("position", Position.COOK);

        return query.list();

    }

    @Transactional
    public List<Employee> findAllWaiters() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.position = :position");
        query.setParameter("position", Position.WAITER);

        return query.list();
    }

    public Employee findById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.ID = :id");
        query.setParameter("id", id);

        return (Employee) query.uniqueResult();

    }

    public Employee findByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.firstName like :name");
        query.setParameter("name", name);

        return (Employee) query.uniqueResult();

    }

    @Transactional
    public void updateEmployee(int employeeId, Employee newEmployee){
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, employeeId);

        if (newEmployee.getFirstName() != null) {
            employee.setFirstName(newEmployee.getFirstName());
        }
        if (newEmployee.getLastName() != null) {
            employee.setLastName(newEmployee.getLastName());
        }
        if (newEmployee.getPosition() != null) {
            employee.setPosition(newEmployee.getPosition());
        }
        if (newEmployee.getPhoneNumber() != null) {
            employee.setPhoneNumber(newEmployee.getPhoneNumber());
        }
        if (newEmployee.getDateOfBirth() != null) {
            employee.setDateOfBirth(newEmployee.getDateOfBirth());
        }
        if ((Double) newEmployee.getSalary() != null) {
            employee.setSalary(newEmployee.getSalary());
        }
        session.update(employee);
    }

    @Transactional
    public List<Employee> findAll() {

        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from Employee e").list();

    }

    public void enteringInformation(Employee employee) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выбирите позицию:  \n"
                            + "1 - Повар  \n"
                            + "2 - Оффициант \n"
                            + "3 - Менеджен \n"
                            + "4 - Уборщик \n");

        int positionId = Integer.parseInt(scanner.nextLine());
        boolean flag = false;
        while (!flag) {
            switch (positionId) {
                case 1:
                    employee.setPosition(Position.COOK);
                    flag = true;
                    break;
                case 2:
                    employee.setPosition(Position.WAITER);
                    flag = true;
                    break;
                case 3:
                    employee.setPosition(Position.MANAGER);
                    flag = true;
                    break;
                case 4:
                    employee.setPosition(Position.CLEANER);
                    flag = true;
                    break;
            }
        }

        System.out.println("Введите имя :");
        String firstName = scanner.nextLine();
        employee.setFirstName(firstName);

        System.out.println("Введите фамилию :");
        String lastName = scanner.nextLine();
        employee.setLastName(lastName);

        System.out.println("Введите дату рождения (dd.mm.yyyy) :");
        String dateOfBirth = scanner.nextLine();
        employee.setDateOfBirth(dateOfBirth);

        System.out.println("Введите номер телефона :");
        String phoneNumber = scanner.nextLine();
        employee.setPhoneNumber(phoneNumber);

        System.out.println("Введите зарплату :");
        double salary = Double.parseDouble(scanner.nextLine());
        employee.setSalary(salary);

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

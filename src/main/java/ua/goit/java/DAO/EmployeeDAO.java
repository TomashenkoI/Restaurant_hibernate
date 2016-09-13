package ua.goit.java.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.tables.Employee;
import ua.goit.java.tables.Position;
import ua.goit.java.tables.Positions;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ua.goit.java.DAO.Requests.*;

/**
 * Created by 7 on 20.08.2016.
 */
public class EmployeeDAO implements TableDAO<Employee> {

    private SessionFactory sessionFactory;

    @Transactional
    public void save(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }

    public void remove(Employee employee){
        sessionFactory.getCurrentSession().delete(employee);
    }

    public List<Employee> findByName1() {

        Session session = sessionFactory.getCurrentSession();
        System.out.println("Введите имя для поиска: ");
        String name = new Scanner(System.in).nextLine();
        Query query = session.createQuery("select e from Employee e where e.firstName like :name");
        query.setParameter("name", name);

        return query.list();

    }

    public Employee findById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.ID = :id");
        query.setParameter("id", id);

        return (Employee) query.uniqueResult();

    }

    public Employee findByName() {

        Session session = sessionFactory.getCurrentSession();
        System.out.println("Введите имя :");
        String name = new Scanner(System.in).nextLine();
        Query query = session.createQuery("select e from Employee e where e.firstName like :name");
        query.setParameter("name", name);

        return (Employee) query.uniqueResult();

    }

    @Transactional
    public List<Employee> findAll() {

        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from Employee e").list();

    }

    public void enteringInformation(Employee employee) {

        Scanner scanner = new Scanner(System.in);


        System.out.println("Выбирите позицию: ");
        int positionId = Integer.parseInt(scanner.nextLine());
        employee.setPosition(positionId);

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

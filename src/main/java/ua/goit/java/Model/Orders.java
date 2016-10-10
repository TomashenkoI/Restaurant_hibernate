package ua.goit.java.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 7 on 12.08.2016.
 */
@Entity
@Table(name = "orders")
public class Orders extends Tables {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int ID;

    @ManyToOne
    @JoinColumn(name = "waiter_id")
    private Employee employee;

//    @Column(name = "waiter_id")
//    private int employeeID;

    @Column(name = "list_of_dishes")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int listOfDishes;


    @Column(name = "table_number")
    private int tableNumber;

    @Column(name = "date")
    private String date;

    @Column(name = "access")
    private boolean access;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getDishes() {
        return listOfDishes;
    }

    public void setListOfDishes(int dishes) {
        this.listOfDishes = dishes;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ID=" + ID +
                ", employee=" + employee +
                ", listOfDishesID=" + listOfDishes +
                ", tableNumber=" + tableNumber +
                ", date='" + date + '\'' +
                '}';
    }
}

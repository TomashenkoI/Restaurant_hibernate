package ua.goit.java.tables;

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

//    @ManyToOne
    @Column(name = "employee_id")
    private int employeeID;

//    @ManyToMany()
//    @JoinTable(
//            name = "dish_to_order",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "dish_id")
//    )
//    private List<Dish> dishes;

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


    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getDishes() {
        return listOfDishes;
    }

    public void setDishes(int dishes) {
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



    @Override
    public String toString() {
        return "Orders{" +
                "ID=" + ID +
                ", employeeID=" + employeeID +
                ", listOfDishesID=" + listOfDishes +
                ", tableNumber=" + tableNumber +
                ", date='" + date + '\'' +
                '}';
    }
}

package ua.goit.java.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 7 on 20.08.2016.
 */
public class Menu extends Tables {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int ID;

    @Column(name = "name")
    private String name;

    @Column(name = "ingredients_to_dish")
    private int listOfDishes_ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getListOfDishes_ID() {
        return listOfDishes_ID;
    }

    public void setListOfDishes_ID(int listOfDishes_ID) {
        this.listOfDishes_ID = listOfDishes_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "ID=" + ID +
                ", listOfDishes_ID=" + listOfDishes_ID +
                ", name='" + name + '\'' +
                '}';
    }
}

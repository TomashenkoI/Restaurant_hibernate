package ua.goit.java.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 7 on 12.08.2016.
 */
@Entity
@Table(name = "dishes")
public class Dish extends Tables {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int ID;

    @Column(name = "dishcategory")
    @Enumerated(EnumType.STRING)
    private DishCategory dishCategory;

    @Column(name = "list_of_ingredients")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int listOfIngredients_ID;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "weight")
    private int weight;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishCategory getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    public int getListOfIngredients_ID() {
        return listOfIngredients_ID;
    }

    public void setListOfIngredients_ID(int listOfIngredients_ID) {
        this.listOfIngredients_ID = listOfIngredients_ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "ID=" + ID +
                " Dish_category_ID =" + dishCategory + "\t" +
                " list_of_ingredients_ID =" + listOfIngredients_ID + "\t" +
                " name_of_the_dish= " + name + "\t" +
                " price=" + price + "\t" +
                " weight=" + weight +
                '}';
    }
}

package ua.goit.java.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 7 on 20.08.2016.
 */
@Entity
@Table(name = "ingredients_to_dish")
public class ListOfIngredients extends Tables {

    @Id
    @Column(name = "ingredient_id")
    private int ingredientID;

    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "dish_id")
    private int dishID;

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    @Override
    public String toString() {
        return "ListOfIngredients{" +
                "ingredientID=" + ingredientID +
                ", dishID=" + dishID +
                '}';
    }
}
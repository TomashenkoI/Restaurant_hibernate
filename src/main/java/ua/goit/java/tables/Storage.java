package ua.goit.java.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 7 on 20.08.2016.
 */
@Entity
@Table(name = "storage")
public class Storage extends Tables {

//    @Id
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name = "increment", strategy = "increment")
//    @Column(name = "id")
//    private int ID;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ingredient_id")
    private int ingredient_ID;

    @Column(name = "amount")
    private int amount;

//    public int getID() {
//        return ID;
//    }
//
//    public void setID(int ID) {
//        this.ID = ID;
//    }

    public int getIngredient_ID() {
        return ingredient_ID;
    }

    public void setIngredient_ID(int ingredient_ID) {
        this.ingredient_ID = ingredient_ID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Storage{" +
//                "ID=" + ID +
                ", ingredient_ID=" + ingredient_ID +
                ", amount=" + amount +
                '}';
    }
}

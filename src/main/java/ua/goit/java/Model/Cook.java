package ua.goit.java.Model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by 7 on 08.09.2016.
 */
@Entity
public class Cook extends Employee {

    @OneToMany()
    @JoinColumn(name = "id")
    private List<CookedDish> cookedDishes;

    public List<CookedDish> getCookedDishes() {
        return cookedDishes;
    }

    public void setCookedDishes(List<CookedDish> cookedDishes) {
        this.cookedDishes = cookedDishes;
    }

    @Override
    public String toString() {
        return "Cook{" +
                "ID=" + getID() +  "\t" +
                " lastName=" + "\t" + getLastName() +
                " firstName=" +  "\t" + getFirstName() +
                " dateOfBirth=" +  "\t" + getDateOfBirth() +
                " phoneNumber=" +  "\t" + getPhoneNumber() +
                " position=" +  "\t" + getPosition() + "\t" +
                " salary= " + getSalary() +
                "cookedDishes=" + cookedDishes +
                '}' ;
    }

}

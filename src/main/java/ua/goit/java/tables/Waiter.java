package ua.goit.java.tables;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by 7 on 08.09.2016.
 */
@Entity
//@Table(name = )
public class Waiter extends Employee {

    @OneToMany()
    @JoinColumn(name = "employee_id")
    private List<Orders> orders;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Waiter{" +
                "orders=" + orders +
                '}' ;
    }
}

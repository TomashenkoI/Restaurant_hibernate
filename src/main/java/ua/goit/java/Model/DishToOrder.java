package ua.goit.java.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 7 on 20.08.2016.
 */
@Entity
@Table(name = "dish_to_order")
public class DishToOrder extends Tables {

    @Id
    @Column(name = "dish_id")
    private int dishId;

    @Column(name = "order_Id")
    private int orderId;

    @Override
    public String toString() {
        return "DishToOrder{" +
                "ID=" + ID +
                ", dishID_N1=" + dishId +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }
}

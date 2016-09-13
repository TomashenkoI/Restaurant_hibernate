package ua.goit.java.Controller;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.DAO.DishToOrderDAO;

/**
 * Created by 7 on 12.09.2016.
 */
public class DishToOrderController {

    private DishToOrderDAO dishToOrderDAO;

    @Transactional
    public void createPosition() {
        dishToOrderDAO.enteringInformation();
    }

    @Transactional
    public void deletePosition(int id) {
        dishToOrderDAO.removeById(id);
    }

    public void findPositionByName() {

    }

    public void showAllPositions() {

    }

    public void setDishToOrderDAO(DishToOrderDAO dishToOrderDAO) {
        this.dishToOrderDAO = dishToOrderDAO;
    }
}

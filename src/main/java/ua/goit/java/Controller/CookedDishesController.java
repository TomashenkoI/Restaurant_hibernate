package ua.goit.java.Controller;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.DAO.CookedDishesDAO;
import ua.goit.java.tables.CookedDish;

/**
 * Created by 7 on 15.09.2016.
 */
public class CookedDishesController implements TableController {

    private CookedDishesDAO cookedDishesDAO;

    @Override
    @Transactional
    public void createPosition() {

        CookedDish cookedDish = new CookedDish();

        cookedDishesDAO.enteringInformation(cookedDish);

    }

    @Override
    public void deletePosition() {

    }

    @Override
    public void findPositionByName() {

    }

    @Override
    public void showAllPositions() {
        cookedDishesDAO.findAll().forEach(System.out::println);
    }

    public void setCookedDishesDAO(CookedDishesDAO cookedDishesDAO) {
        this.cookedDishesDAO = cookedDishesDAO;
    }
}

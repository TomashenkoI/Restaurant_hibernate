package ua.goit.java.Controller;

import ua.goit.java.DAO.MenuDAO;
import ua.goit.java.tables.Menu;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 7 on 11.09.2016.
 */
public class MenuController implements TableController {

    private MenuDAO menuDAO;

    @Override
    public void createPosition() {
        Set<Menu> allEmployees = new HashSet<Menu>(menuDAO.findAll());

        Menu menu = new Menu();
        menuDAO.enteringInformation(menu);

        if (!allEmployees.contains(menu)) {
            menuDAO.save(menu);
        }
    }

    @Override
    public void deletePosition() {

    }

    @Override
    public void findPositionByName() {

    }

    @Override
    public void showAllPositions() {
        menuDAO.findAll().forEach(System.out::println);
    }

    public void setMenuDAO(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }
}

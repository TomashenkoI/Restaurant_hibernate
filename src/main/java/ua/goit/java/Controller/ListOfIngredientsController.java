package ua.goit.java.Controller;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.DAO.ListOfIngredientsDAO;

/**
 * Created by 7 on 12.09.2016.
 */
public class ListOfIngredientsController{

    private ListOfIngredientsDAO listOfIngredientsDAO;

    public void createPosition() {
        listOfIngredientsDAO.enteringInformation();
    }

    @Transactional
    public void deletePosition(int id) {
        listOfIngredientsDAO.removeById(id);
    }


    public void setListOfIngredientsDAO(ListOfIngredientsDAO listOfIngredientsDAO) {
        this.listOfIngredientsDAO = listOfIngredientsDAO;
    }
}

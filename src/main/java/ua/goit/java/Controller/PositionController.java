package ua.goit.java.Controller;

import ua.goit.java.DAO.PositionsDAO;
import ua.goit.java.tables.Positions;

import java.util.Scanner;

/**
 * Created by 7 on 07.09.2016.
 */
public class PositionController implements TableController {

    private PositionsDAO positionsDAO = new PositionsDAO();

    @Override
    public void createPosition() {
        Positions position = new Positions();
        System.out.println("Введите название должности");
        String enteredString = new Scanner(System.in).nextLine();
        position.setName(enteredString);
        positionsDAO.save(position);
    }

    @Override
    public void deletePosition() {
        showAllPositions();

        System.out.println("Введите id должности :");
        int id = Integer.parseInt(new Scanner(System.in).nextLine());
        positionsDAO.remove(positionsDAO.findById(id));
    }

    @Override
    public void findPositionByName() {

    }

    @Override
    public void showAllPositions() {
        positionsDAO.findAll().forEach(System.out::println);
    }

    public void setPositionsDAO(PositionsDAO positionsDAO) {
        this.positionsDAO = positionsDAO;
    }
}

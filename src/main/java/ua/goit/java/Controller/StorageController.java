package ua.goit.java.Controller;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.DAO.StorageDAO;

import java.util.Scanner;

/**
 * Created by 7 on 13.09.2016.
 */
public class StorageController implements TableController {

    private StorageDAO storageDAO;

    @Override
    @Transactional
    public void createPosition() {
        storageDAO.enteringInformation();
    }

    @Override
    public void deletePosition() {
        showAllPositions();

        System.out.println("Введите id : ");
        int id = Integer.parseInt(new Scanner(System.in).nextLine());
        storageDAO.remove(storageDAO.findById(id));
    }

    @Override
    public void findPositionByName() {

    }

    @Override
    public void showAllPositions() {
        storageDAO.findAll().forEach(System.out::println);
    }

    public void setStorageDAO(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }
}

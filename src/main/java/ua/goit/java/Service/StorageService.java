package ua.goit.java.Service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.DAO.StorageDAO;
import ua.goit.java.Model.Storage;

import java.util.List;

public class StorageService {

    private StorageDAO storageDAO;

    private List<Storage> storage() {
        return storageDAO.findAll();
    }

    @Transactional
    private void updateStorage() {

    }

    public void setStorageDAO(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }
}

package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.ProductionDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.ProductionEntity;

import java.util.ArrayList;

public class ProductionDAOImpl implements ProductionDAO {
    @Override
    public ArrayList<String> getAllInventoryIds() {
        return null;
    }

    @Override
    public ArrayList<ProductionEntity> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(ProductionEntity dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(ProductionEntity dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean exists(String id) throws Exception {
        return false;
    }

    @Override
    public ArrayList<ProductionEntity> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }
}

package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.ProductionTaskDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.ProductionTaskEntity;

import java.util.ArrayList;

public class ProductionTaskDAOImpl implements ProductionTaskDAO {
    @Override
    public ArrayList<String> getAllProductionIds() {
        return null;
    }

    @Override
    public ArrayList<String> getAllTaskIds() {
        return null;
    }

    @Override
    public ArrayList<ProductionTaskEntity> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(ProductionTaskEntity dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(ProductionTaskEntity dto) throws Exception {
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
    public ArrayList<ProductionTaskEntity> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }
}

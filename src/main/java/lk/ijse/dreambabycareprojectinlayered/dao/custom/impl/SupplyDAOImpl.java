package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.SupplyDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.SupplyEntity;

import java.util.ArrayList;

public class SupplyDAOImpl implements SupplyDAO {
    @Override
    public ArrayList<String> getAllSupplierIds() {
        return null;
    }

    @Override
    public ArrayList<String> getAllMaterial() {
        return null;
    }

    @Override
    public ArrayList<SupplyEntity> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(SupplyEntity dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(SupplyEntity dto) throws Exception {
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
    public ArrayList<SupplyEntity> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }
}

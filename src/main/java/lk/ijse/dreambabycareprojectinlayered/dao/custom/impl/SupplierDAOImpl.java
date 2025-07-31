package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.SupplierDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.SupplierEntity;

import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<SupplierEntity> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(SupplierEntity dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(SupplierEntity dto) throws Exception {
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
    public ArrayList<SupplierEntity> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }
}

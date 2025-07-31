package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.ShipmentDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.ShipmentEntity;

import java.util.ArrayList;

public class ShipmentDAOImpl implements ShipmentDAO {
    @Override
    public ArrayList<ShipmentEntity> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(ShipmentEntity dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(ShipmentEntity dto) throws Exception {
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
    public ArrayList<ShipmentEntity> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }
}

package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.OrdersDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.OrdersEntity;

import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public ArrayList<String> getAllCustomerIds() {
        return null;
    }

    @Override
    public ArrayList<String> getAllShipmentIds() {
        return null;
    }

    @Override
    public ArrayList<OrdersEntity> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(OrdersEntity dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(OrdersEntity dto) throws Exception {
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
    public ArrayList<OrdersEntity> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }
}

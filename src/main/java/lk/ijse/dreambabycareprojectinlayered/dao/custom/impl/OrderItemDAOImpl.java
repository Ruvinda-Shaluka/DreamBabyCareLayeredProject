package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.OrderItemDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.OrderItemEntity;

import java.util.ArrayList;

public class OrderItemDAOImpl implements OrderItemDAO {
    @Override
    public ArrayList<String> getAllOrderIds() {
        return null;
    }

    @Override
    public ArrayList<String> getAllInventoryIds() {
        return null;
    }

    @Override
    public ArrayList<OrderItemEntity> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(OrderItemEntity dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(OrderItemEntity dto) throws Exception {
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
    public ArrayList<OrderItemEntity> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }
}

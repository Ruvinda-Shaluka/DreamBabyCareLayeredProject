package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.OrderItemBO;
import lk.ijse.dreambabycareprojectinlayered.dto.OrderItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemBOImpl implements OrderItemBO {
    @Override
    public ArrayList<OrderItemDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(OrderItemDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(OrderItemDto dto) throws Exception {
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
    public ArrayList<OrderItemDto> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }

    @Override
    public ArrayList<String> getAllOrderIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}

package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.OrdersBO;
import lk.ijse.dreambabycareprojectinlayered.dto.OrdersDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersBOImpl implements OrdersBO {
    @Override
    public ArrayList<OrdersDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(OrdersDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(OrdersDto dto) throws Exception {
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
    public ArrayList<OrdersDto> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllShipmentIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}

package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.OrdersEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersDAO extends CrudDAO<OrdersEntity> {
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllShipmentIds() throws SQLException, ClassNotFoundException;
}

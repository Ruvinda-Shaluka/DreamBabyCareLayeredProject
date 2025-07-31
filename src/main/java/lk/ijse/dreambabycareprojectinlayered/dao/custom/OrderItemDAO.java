package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.OrderItemEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderItemDAO extends CrudDAO<OrderItemEntity> {
    public ArrayList<String> getAllOrderIds() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException;
}

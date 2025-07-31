package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.OrdersEntity;

import java.util.ArrayList;

public interface OrdersDAO extends CrudDAO<OrdersEntity> {
    public ArrayList<String> getAllCustomerIds();
    public ArrayList<String> getAllShipmentIds();
}

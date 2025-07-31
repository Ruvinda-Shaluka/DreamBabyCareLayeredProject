package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.OrderItemEntity;

import java.util.ArrayList;

public interface OrderItemDAO extends CrudDAO<OrderItemEntity> {
    public ArrayList<String> getAllOrderIds();
    public ArrayList<String> getAllInventoryIds();
}

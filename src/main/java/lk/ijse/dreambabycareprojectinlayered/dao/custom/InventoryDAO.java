package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.InventoryEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryDAO extends CrudDAO<InventoryEntity> {
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException;
    public boolean reduceItemQty(String itemId, int cartQty);
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException;
    public String getItemNameById(String itemId);
    public boolean increaseInventoryQty(String itemId, int amount);

}

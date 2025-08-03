package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.InventoryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryBO extends SuperBO {
    public ArrayList<InventoryDto> loadAll() throws Exception;
    public boolean save(InventoryDto dto) throws Exception;
    public boolean update(InventoryDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<InventoryDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException;
    public boolean reduceItemQty(String itemId, int cartQty);
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException;
    public String getItemNameById(String itemId);
    public boolean increaseInventoryQty(String itemId, int amount);

}

package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.InventoryDAO;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.entity.InventoryEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDAOImpl implements InventoryDAO {
    @Override
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT inventory_id FROM inventory");
        ArrayList<String> inventoryIds = new ArrayList<>();
        while (resultSet.next()) {
            inventoryIds.add(resultSet.getString("inventory_id"));
        }
        return inventoryIds;
    }

    @Override
    public boolean reduceItemQty(String itemId, int cartQty) {
        try {
            ResultSet resultSet = SQLUtil.executeQuery(
                    "SELECT quantity_available FROM inventory WHERE inventory_id = ?",
                    itemId
            );
            if (resultSet.next()) {
                int currentQty = resultSet.getInt("quantity_available");
                if (currentQty >= cartQty) {
                    int newQty = currentQty - cartQty;
                    return SQLUtil.executeUpdate(
                            "UPDATE inventory SET quantity_available = ? WHERE inventory_id = ?",
                            newQty,
                            itemId);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Insufficient stock for Item ID: " + itemId).show();
                    return false;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error reducing item quantity...").show();
        }
        return false;
    }

    @Override
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT inventory_id FROM inventory");
        ArrayList<String> inventoryIds = new ArrayList<>();
        while (resultSet.next()) {
            inventoryIds.add(resultSet.getString("inventory_id"));
        }
        return inventoryIds;
    }

    @Override
    public String getItemNameById(String itemId) {
        try {
            ResultSet resultSet = SQLUtil.executeQuery("SELECT item_name FROM inventory WHERE inventory_id = ?", itemId);
            if (resultSet.next()) {
                return resultSet.getString("item_name");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load item name by ID...").show();
        }
        return null;
    }

    @Override
    public boolean increaseInventoryQty(String itemId, int amount) {
        try {
            ResultSet resultSet = SQLUtil.executeQuery(
                    "SELECT quantity_available FROM inventory WHERE inventory_id = ?",
                    itemId
            );
            if (resultSet.next()) {
                int currentQty = resultSet.getInt("quantity_available");
                int newQty = currentQty + amount;
                return SQLUtil.executeUpdate(
                        "UPDATE inventory SET quantity_available = ? WHERE inventory_id = ?",
                        newQty,
                        itemId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error increasing inventory quantity...").show();
        }
        return false;
    }

    @Override
    public ArrayList<InventoryEntity> loadAll() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM inventory");
        ArrayList<InventoryEntity> inventoryDtoArrayList = new ArrayList<>();
        while (rst.next()){
            inventoryDtoArrayList.add(new InventoryEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getInt(6),
                    rst.getString(7)
            ));
        }
        return inventoryDtoArrayList;
    }

    @Override
    public boolean save(InventoryEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "INSERT INTO inventory VALUES (?,?,?,?,?,?,?)",
                dto.getInventory_id(),
                dto.getItem_name(),
                dto.getPrinted_embroidered(),
                dto.getSize(),
                dto.getUnit_price(),
                dto.getQuantity_available(),
                dto.getStored_location()
        );
    }

    @Override
    public boolean update(InventoryEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE inventory SET item_name = ?, printed_embroidered = ?, size = ?, unit_price = ?, quantity_available = ? ,stored_location = ? WHERE inventory_id= ?",
                dto.getItem_name(),
                dto.getPrinted_embroidered(),
                dto.getSize(),
                dto.getUnit_price(),
                dto.getQuantity_available(),
                dto.getStored_location(),
                dto.getInventory_id()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate(
                "DELETE FROM inventory WHERE inventory_id = ?",
                id
        );
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM inventory WHERE inventory_id = ?", id);
        return resultSet.next();
    }

    @Override
    public ArrayList<InventoryEntity> search(String text) throws Exception {
        ArrayList<InventoryEntity> inventoryDtoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM inventory WHERE inventory_id LIKE ? OR item_name LIKE ? OR printed_embroidered LIKE ? OR size LIKE ? OR unit_price LIKE ? OR quantity_available LIKE ? OR stored_location LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet rst = SQLUtil.executeQuery(sql,pattern,pattern,pattern,pattern,pattern,pattern,pattern);

        while (rst.next()){
            inventoryDtoArrayList.add(new InventoryEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getInt(6),
                    rst.getString(7)
            ));
        }
        return inventoryDtoArrayList;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT inventory_id FROM inventory ORDER BY inventory_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("inventory_id");
            int newCustomerId = Integer.parseInt(id.replace("INV", "")) + 1;
            return String.format("INV%03d", newCustomerId);
        } else {
            return "INV001";
        }
    }
}

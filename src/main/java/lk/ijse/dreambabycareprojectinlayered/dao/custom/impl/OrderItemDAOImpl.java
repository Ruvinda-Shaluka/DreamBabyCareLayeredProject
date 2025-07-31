package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.OrderItemDAO;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.entity.OrderItemEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemDAOImpl implements OrderItemDAO {
    @Override
    public ArrayList<String> getAllOrderIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT order_id FROM orders");
        ArrayList<String> orderIds = new ArrayList<>();

        while (rst.next()) {
            orderIds.add(rst.getString("order_id"));
        }
        return orderIds;
    }

    @Override
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.executeQuery("SELECT inventory_id FROM inventory");
        ArrayList<String> inventoryIds = new ArrayList<>();

        while (rst.next()) {
            inventoryIds.add(rst.getString("inventory_id"));
        }
        return inventoryIds;
    }

    @Override
    public ArrayList<OrderItemEntity> loadAll() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM order_item");
        ArrayList<OrderItemEntity> orderItemDto = new ArrayList<>();

        while (rst.next()) {
            orderItemDto.add(new OrderItemEntity(
                    rst.getString("order_item_id"),
                    rst.getString("order_id"),
                    rst.getString("inventory_id"),
                    rst.getInt("quantity"),
                    rst.getDouble("amount")
            ));
        }
        return orderItemDto;
    }

    @Override
    public boolean save(OrderItemEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "INSERT INTO order_item VALUES (?,?,?,?,?)",
                dto.getOrder_item_id(),
                dto.getOrder_id(),
                dto.getInventory_id(),
                dto.getQuantity(),
                dto.getAmount()
        );
    }

    @Override
    public boolean update(OrderItemEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE order_item SET order_id = ?, inventory_id = ?, quantity = ?, amount = ?WHERE order_item_id= ?",
                dto.getOrder_id(),
                dto.getInventory_id(),
                dto.getQuantity(),
                dto.getAmount(),
                dto.getOrder_item_id()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate("DELETE FROM order_item WHERE order_item_id = ?", id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM order_item WHERE order_item_id = ?", id);
        return rs.next();
    }

    @Override
    public ArrayList<OrderItemEntity> search(String text) throws Exception {
        ArrayList<OrderItemEntity> orderItemDtoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM order_item WHERE order_item_id LIKE ? OR order_id LIKE ? OR inventory_id LIKE ? OR quantity LIKE ? OR amount LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet rst = SQLUtil.executeQuery(sql, pattern, pattern, pattern, pattern, pattern);

        while(rst.next()){
            orderItemDtoArrayList.add(new OrderItemEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getDouble(5)
            ));
        }
        return orderItemDtoArrayList;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT order_item_id FROM order_item ORDER BY order_item_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("order_item_id");
            int newCustomerId = Integer.parseInt(id.replace("OIT", "")) + 1;
            return String.format("OIT%03d", newCustomerId);
        } else {
            return "OIT001";
        }
    }
}

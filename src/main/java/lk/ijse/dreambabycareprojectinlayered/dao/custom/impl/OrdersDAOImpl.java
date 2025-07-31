package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.OrdersDAO;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.entity.OrdersEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> customerIds = new ArrayList<>();
        ResultSet rst = SQLUtil.executeQuery("SELECT customer_id FROM customer");
        while (rst.next()) {
            customerIds.add(rst.getString("customer_id"));
        }
        return customerIds;
    }

    @Override
    public ArrayList<String> getAllShipmentIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> shipmentIds = new ArrayList<>();
        ResultSet rst = SQLUtil.executeQuery("SELECT shipment_id FROM shipment");
        while (rst.next()) {
            shipmentIds.add(rst.getString("shipment_id"));
        }
        return shipmentIds;
    }

    @Override
    public ArrayList<OrdersEntity> loadAll() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM orders");
        ArrayList<OrdersEntity> orderDto = new ArrayList<>();

        while (rst.next()) {
            orderDto.add(new OrdersEntity(
                    rst.getString("order_id"),
                    LocalDate.parse(rst.getString("order_date")),
                    rst.getString("customer_id"),
                    rst.getString("shipment_id"),
                    rst.getString("status")
            ));
        }
        return orderDto;
    }

    @Override
    public boolean save(OrdersEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "INSERT INTO orders VALUES (?,?,?,?,?)",
                dto.getOrder_id(),
                dto.getOrder_date(),
                dto.getCustomer_id(),
                dto.getShipment_id(),
                dto.getStatus()
        );
    }

    @Override
    public boolean update(OrdersEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE orders SET order_date = ?, customer_id = ?, shipment_id = ?, status = ?WHERE order_id= ?",
                dto.getOrder_date(),
                dto.getCustomer_id(),
                dto.getShipment_id(),
                dto.getStatus(),
                dto.getOrder_id()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate("DELETE FROM orders WHERE order_id = ?", id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM orders WHERE order_id = ?", id);
        return resultSet.next();
    }

    @Override
    public ArrayList<OrdersEntity> search(String text) throws Exception {
        ArrayList<OrdersEntity> ordersDtoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE order_id LIKE ? OR order_date LIKE ? OR customer_id LIKE ? OR shipment_id LIKE ? OR status LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet rst = SQLUtil.executeQuery(sql, pattern, pattern, pattern, pattern, pattern);

        while (rst.next()) {
            ordersDtoArrayList.add(new OrdersEntity(
                    rst.getString(1),
                    LocalDate.parse(rst.getString(2)),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return ordersDtoArrayList;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("order_id");
            int newCustomerId = Integer.parseInt(id.replace("ORD", "")) + 1;
            return String.format("ORD%03d", newCustomerId);
        } else {
            return "ORD001";
        }
    }
}

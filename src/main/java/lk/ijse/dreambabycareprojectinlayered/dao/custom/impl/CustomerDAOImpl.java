package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.CustomerDAO;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.entity.CustomerEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<CustomerEntity> loadAll() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM customer");
        ArrayList<CustomerEntity> customerEntities = new ArrayList<>();
        while (resultSet.next()) {
            customerEntities.add(new CustomerEntity(
                    resultSet.getString("customer_id"),
                    resultSet.getString("name"),
                    resultSet.getString("phone"),
                    resultSet.getString("address"),
                    resultSet.getString("order_platform")
                    ));
        }
        return customerEntities;
    }

    @Override
    public boolean save(CustomerEntity dto) throws Exception {
        return SQLUtil.executeUpdate("INSERT INTO customer (customer_id, name, phone, address, order_platform) VALUES (?, ?, ?, ?, ?)",
                dto.getCustomerId(), dto.getCustomerName(), dto.getCustomerPhone(), dto.getCustomerAddress(), dto.getOrderPlatForm());
    }

    @Override
    public boolean update(CustomerEntity dto) throws Exception {
        return SQLUtil.executeUpdate("UPDATE customer SET name = ?, phone = ?, address = ?, order_platform = ? WHERE customer_id = ?",
                dto.getCustomerName(), dto.getCustomerPhone(), dto.getCustomerAddress(), dto.getOrderPlatForm(), dto.getCustomerId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate("DELETE FROM customer WHERE customer_id = ?", id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT customer_id FROM customer WHERE customer_id = ?", id);
        return resultSet.next();
    }

    @Override
    public ArrayList<CustomerEntity> search(String searchText) throws Exception {
        ArrayList<CustomerEntity> customerEntities = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE customer_id LIKE ? OR name LIKE ? OR phone LIKE ? OR address LIKE ? OR order_platform LIKE ?";
        String pattern = "%" + searchText + "%";
        ResultSet resultSet = SQLUtil.executeQuery(sql, pattern, pattern, pattern, pattern, pattern);

        while (resultSet.next()) {
            customerEntities.add(new CustomerEntity(
                    resultSet.getString("customer_id"),
                    resultSet.getString("name"),
                    resultSet.getString("phone"),
                    resultSet.getString("address"),
                    resultSet.getString("order_platform")
            ));
        }
        return customerEntities;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("customer_id");
            int newCustomerId = Integer.parseInt(id.replace("CUS", "")) + 1;
            return String.format("CUS%03d", newCustomerId);
        } else {
            return "CUS001";
        }
    }

    @Override
    public String getCustomerIdByContact(String contact) throws Exception {
        try {
            ResultSet resultSet = SQLUtil.executeQuery("SELECT customer_id FROM customer WHERE phone = ?", contact);
            if (resultSet.next()) {
                return resultSet.getString("customer_id");
            } else {
                return "Customer Not Found"; // or throw an exception if preferred
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error retrieving customer ID by contact: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<String> getCustomerIdByPartialContact(String contact) throws Exception {
        ArrayList<String> customerNames = new ArrayList<>();
        try {
            ResultSet resultSet = SQLUtil.executeQuery("SELECT name FROM customer WHERE phone LIKE ?", "%" + contact + "%");
            while (resultSet.next()) {
                customerNames.add(resultSet.getString("name"));
            }
            return customerNames;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error retrieving customer IDs by partial contact: " + e.getMessage());
        }
    }

}

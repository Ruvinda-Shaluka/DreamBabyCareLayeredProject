package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.PaymentDAO;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.entity.PaymentEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<String> getAllOrderIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> orderIds = new ArrayList<>();
        ResultSet rst = SQLUtil.executeQuery("SELECT order_id FROM orders");
        while (rst.next()) {
            orderIds.add(rst.getString("order_id"));
        }
        return orderIds;
    }

    @Override
    public ArrayList<PaymentEntity> loadAll() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM payment");
        ArrayList<PaymentEntity> paymentDto = new ArrayList<>();

        while (rst.next()) {
            paymentDto.add(new PaymentEntity(
                    rst.getString("payment_id"),
                    rst.getString("order_id"),
                    rst.getDouble("amount"),
                    rst.getString("payment_method")
            ));
        }
        return paymentDto;
    }

    @Override
    public boolean save(PaymentEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "INSERT INTO payment VALUES (?,?,?,?)",
                dto.getPayment_id(),
                dto.getOrder_id(),
                dto.getAmount(),
                dto.getPayment_method()
        );
    }

    @Override
    public boolean update(PaymentEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE payment SET order_id = ?, amount = ?, payment_method = ?WHERE payment_id= ?",
                dto.getOrder_id(),
                dto.getAmount(),
                dto.getPayment_method(),
                dto.getPayment_id()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate("DELETE FROM payment WHERE payment_id = ?", id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM payment WHERE payment_id = ?", id);
        return rs.next();
    }

    @Override
    public ArrayList<PaymentEntity> search(String text) throws Exception {
        ArrayList<PaymentEntity> paymentDtoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM payment WHERE payment_id LIKE ? OR order_id LIKE ? OR amount LIKE ? OR payment_method LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet rst = SQLUtil.executeQuery(sql, pattern, pattern, pattern, pattern);

        if (rst.next()) {
            paymentDtoArrayList.add(new PaymentEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4)
            ));
        }
        return paymentDtoArrayList;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT payment_id FROM payment ORDER BY payment_id DESC LIMIT 1");
        String tableCharacter = "PAY"; // Use any character Ex:- customer table for C, item table for I
        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // "C001"
            String lastIdNumberString = lastId.substring(tableCharacter.length()); // "001"
            int lastIdNumber = Integer.parseInt(lastIdNumberString); // 1
            int nextIdNUmber = lastIdNumber + 1; // 2
            // "C002"
            return String.format(tableCharacter + "%03d", nextIdNUmber);
        }
        // No data recode in table so return initial primary key
        return tableCharacter + "001";
    }
}

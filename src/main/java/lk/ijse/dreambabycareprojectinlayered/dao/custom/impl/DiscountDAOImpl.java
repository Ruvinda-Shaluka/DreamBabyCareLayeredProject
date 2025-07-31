package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.DiscountDAO;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.entity.DiscountEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DiscountDAOImpl implements DiscountDAO {
    @Override
    public ArrayList<String> getAllPaymentIds() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT payment_id FROM payment");
        ArrayList<String> paymentIds = new ArrayList<>();
        while (rst.next()) {
            paymentIds.add(rst.getString("payment_id"));
        }
        return paymentIds;
    }

    @Override
    public ArrayList<DiscountEntity> loadAll() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM discount");
        ArrayList<DiscountEntity> discounts = new ArrayList<>();
        while (rst.next()) {
            discounts.add(new DiscountEntity(
                    rst.getString("discount_id"),
                    rst.getString("payment_id"),
                    rst.getString("discount_type"),
                    rst.getDouble("discount_percentage")
            ));
        }
        return discounts;
    }

    @Override
    public boolean save(DiscountEntity dto) throws Exception {
        return SQLUtil.executeUpdate("INSERT INTO discount VALUES (?,?,?,?)",
                dto.getDiscount_id(),
                dto.getPayment_id(),
                dto.getDiscount_type(),
                dto.getDiscount_percentage());
    }

    @Override
    public boolean update(DiscountEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE discount SET payment_id = ?, discount_type = ?, discount_percentage = ?WHERE discount_id= ?",
                dto.getPayment_id(),
                dto.getDiscount_type(),
                dto.getDiscount_percentage(),
                dto.getDiscount_id()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate(
                "DELETE FROM discount WHERE discount_id = ?",
                id
        );
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM discount WHERE discount_id = ?", id);
        return rst.next();
    }

    @Override
    public ArrayList<DiscountEntity> search(String text) throws Exception {
        ArrayList<DiscountEntity> discountDtoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM discount WHERE discount_id LIKE ? OR payment_id LIKE ? OR discount_type LIKE ? OR discount_percentage LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet resultSet = SQLUtil.executeQuery(sql, pattern, pattern, pattern, pattern);

        while (resultSet.next()){
            discountDtoArrayList.add(new DiscountEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
                    ));
        }
        return discountDtoArrayList;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT discount_id FROM discount ORDER BY discount_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("discount_id");
            int newCustomerId = Integer.parseInt(id.replace("DIS", "")) + 1;
            return String.format("DIS%03d", newCustomerId);
        } else {
            return "DIS001";
        }
    }
}

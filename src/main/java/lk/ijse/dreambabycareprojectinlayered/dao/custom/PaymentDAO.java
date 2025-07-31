package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.PaymentEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<PaymentEntity> {
    public ArrayList<String> getAllOrderIds() throws SQLException, ClassNotFoundException;
}

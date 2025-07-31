package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.PaymentDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.PaymentEntity;

import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<String> getAllOrderIds() {
        return null;
    }

    @Override
    public ArrayList<PaymentEntity> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(PaymentEntity dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(PaymentEntity dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean exists(String id) throws Exception {
        return false;
    }

    @Override
    public ArrayList<PaymentEntity> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }
}

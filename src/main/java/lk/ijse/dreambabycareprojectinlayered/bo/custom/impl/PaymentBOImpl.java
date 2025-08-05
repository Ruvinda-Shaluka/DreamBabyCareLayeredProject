package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.PaymentBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.PaymentDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.PaymentDto;
import lk.ijse.dreambabycareprojectinlayered.entity.PaymentEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public ArrayList<PaymentDto> loadAll() throws Exception {
        ArrayList<PaymentEntity> paymentEntities = paymentDAO.loadAll();
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();
        for (PaymentEntity paymentEntity : paymentEntities) {
            paymentDtos.add(new PaymentDto(
                    paymentEntity.getPayment_id(),
                    paymentEntity.getOrder_id(),
                    paymentEntity.getAmount(),
                    paymentEntity.getPayment_method()
            ));
        }
        return paymentDtos;

    }

    @Override
    public boolean save(PaymentDto dto) throws Exception {
        return paymentDAO.save(new PaymentEntity(
                dto.getPayment_id(),
                dto.getOrder_id(),
                dto.getAmount(),
                dto.getPayment_method()
        ));
    }

    @Override
    public boolean update(PaymentDto dto) throws Exception {
        return paymentDAO.update(new PaymentEntity(
                dto.getPayment_id(),
                dto.getOrder_id(),
                dto.getAmount(),
                dto.getPayment_method()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return paymentDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return paymentDAO.exists(id);
    }

    @Override
    public ArrayList<PaymentDto> search(String text) throws Exception {
        ArrayList<PaymentEntity> paymentEntities = paymentDAO.search(text);
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();
        for (PaymentEntity paymentEntity : paymentEntities) {
            paymentDtos.add(new PaymentDto(
                    paymentEntity.getPayment_id(),
                    paymentEntity.getOrder_id(),
                    paymentEntity.getAmount(),
                    paymentEntity.getPayment_method()
            ));
        }
        return paymentDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return paymentDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllOrderIds() throws SQLException, ClassNotFoundException {
        return paymentDAO.getAllOrderIds();
    }
}

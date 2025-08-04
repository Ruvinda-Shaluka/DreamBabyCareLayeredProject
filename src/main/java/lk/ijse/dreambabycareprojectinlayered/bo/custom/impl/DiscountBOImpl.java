package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.DiscountBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.DiscountDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.DiscountDto;
import lk.ijse.dreambabycareprojectinlayered.entity.DiscountEntity;

import java.util.ArrayList;

public class DiscountBOImpl implements DiscountBO {
    DiscountDAO discountDAO = (DiscountDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DISCOUNT);

    @Override
    public ArrayList<DiscountDto> loadAll() throws Exception {
        ArrayList<DiscountEntity> discountEntities = discountDAO.loadAll();
        ArrayList<DiscountDto> discountDtos = new ArrayList<>();
        for (DiscountEntity entity : discountEntities) {
            discountDtos.add(new DiscountDto(
                    entity.getDiscount_id(),
                    entity.getPayment_id(),
                    entity.getDiscount_type(),
                    entity.getDiscount_percentage()
            ));
        }
        return discountDtos;
    }

    @Override
    public boolean save(DiscountDto dto) throws Exception {
        return discountDAO.save(new DiscountEntity(
                dto.getDiscount_id(),
                dto.getPayment_id(),
                dto.getDiscount_type(),
                dto.getDiscount_percentage()
        ));
    }

    @Override
    public boolean update(DiscountDto dto) throws Exception {
        return discountDAO.update(new DiscountEntity(
                dto.getDiscount_id(),
                dto.getPayment_id(),
                dto.getDiscount_type(),
                dto.getDiscount_percentage()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return discountDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return discountDAO.exists(id);
    }

    @Override
    public ArrayList<DiscountDto> search(String text) throws Exception {
        ArrayList<DiscountEntity> discountEntities = discountDAO.search(text);
        ArrayList<DiscountDto> discountDtos = new ArrayList<>();
        for (DiscountEntity entity : discountEntities) {
            discountDtos.add(new DiscountDto(
                    entity.getDiscount_id(),
                    entity.getPayment_id(),
                    entity.getDiscount_type(),
                    entity.getDiscount_percentage()
            ));
        }
        return discountDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return discountDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllPaymentIds() throws Exception {
        ArrayList<String> paymentIds = discountDAO.getAllPaymentIds();
        return paymentIds;
    }
}

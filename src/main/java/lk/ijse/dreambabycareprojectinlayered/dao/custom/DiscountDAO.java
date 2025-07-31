package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.DiscountEntity;

import java.util.ArrayList;

public interface DiscountDAO extends CrudDAO<DiscountEntity> {
    public ArrayList<String> getAllPaymentIds()throws Exception;
}

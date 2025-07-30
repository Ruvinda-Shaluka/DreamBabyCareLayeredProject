package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.CustomerEntity;

import java.util.ArrayList;

public interface CustomerBO extends CrudDAO<CustomerEntity> {
    public String getCustomerIdByContact(String contact) throws Exception;
    public ArrayList<String> getCustomerIdByPartialContact(String email) throws Exception;
}

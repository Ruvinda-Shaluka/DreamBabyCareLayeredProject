package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.CustomerBO;
import lk.ijse.dreambabycareprojectinlayered.dto.CustomerDto;

import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    @Override
    public ArrayList<CustomerDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(CustomerDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(CustomerDto dto) throws Exception {
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
    public ArrayList<CustomerDto> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }

    @Override
    public String getCustomerIdByContact(String contact) throws Exception {
        return "";
    }

    @Override
    public ArrayList<String> getCustomerIdByPartialContact(String email) throws Exception {
        return null;
    }
}

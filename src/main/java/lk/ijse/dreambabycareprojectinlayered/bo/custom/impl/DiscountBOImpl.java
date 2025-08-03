package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.DiscountBO;
import lk.ijse.dreambabycareprojectinlayered.dto.DiscountDto;

import java.util.ArrayList;

public class DiscountBOImpl implements DiscountBO {
    @Override
    public ArrayList<DiscountDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(DiscountDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(DiscountDto dto) throws Exception {
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
    public ArrayList<DiscountDto> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }

    @Override
    public ArrayList<String> getAllPaymentIds() throws Exception {
        return null;
    }
}

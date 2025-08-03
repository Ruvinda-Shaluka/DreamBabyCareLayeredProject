package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.SupplierBO;
import lk.ijse.dreambabycareprojectinlayered.dto.SupplierDto;

import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    @Override
    public ArrayList<SupplierDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(SupplierDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(SupplierDto dto) throws Exception {
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
    public ArrayList<SupplierDto> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }
}

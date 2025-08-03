package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.SupplyBO;
import lk.ijse.dreambabycareprojectinlayered.dto.SupplyDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplyBOImpl implements SupplyBO {
    @Override
    public ArrayList<SupplyDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(SupplyDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(SupplyDto dto) throws Exception {
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
    public ArrayList<SupplyDto> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }

    @Override
    public ArrayList<String> getAllSupplierIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllMaterial() throws SQLException, ClassNotFoundException {
        return null;
    }
}

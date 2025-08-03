package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.ProductionBO;
import lk.ijse.dreambabycareprojectinlayered.dto.ProductionDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductionBOImpl implements ProductionBO {
    @Override
    public ArrayList<ProductionDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(ProductionDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(ProductionDto dto) throws Exception {
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
    public ArrayList<ProductionDto> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }

    @Override
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}

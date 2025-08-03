package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.ProductionTaskBO;
import lk.ijse.dreambabycareprojectinlayered.dto.ProductionTaskDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductionTaskBOImpl implements ProductionTaskBO {
    @Override
    public ArrayList<ProductionTaskDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(ProductionTaskDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(ProductionTaskDto dto) throws Exception {
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
    public ArrayList<ProductionTaskDto> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }

    @Override
    public ArrayList<String> getAllProductionIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllTaskIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}

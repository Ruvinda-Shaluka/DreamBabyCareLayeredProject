package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.MaterialUsageBO;
import lk.ijse.dreambabycareprojectinlayered.dto.MaterialUsageDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialUsageBOImpl implements MaterialUsageBO {
    @Override
    public ArrayList<MaterialUsageDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(MaterialUsageDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(MaterialUsageDto dto) throws Exception {
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
    public ArrayList<MaterialUsageDto> search(String text) throws Exception {
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
    public ArrayList<String> getAllMaterialIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}

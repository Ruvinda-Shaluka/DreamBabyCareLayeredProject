package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.MaterialUsageDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialUsageBO extends SuperBO {
    public ArrayList<MaterialUsageDto> loadAll() throws Exception;
    public boolean save(MaterialUsageDto dto) throws Exception;
    public boolean update(MaterialUsageDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<MaterialUsageDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
    public ArrayList<String> getAllProductionIds() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllMaterialIds() throws SQLException, ClassNotFoundException;
}

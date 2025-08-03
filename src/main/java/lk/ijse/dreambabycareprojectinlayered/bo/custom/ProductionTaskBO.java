package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.ProductionTaskDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductionTaskBO extends SuperBO {
    public ArrayList<ProductionTaskDto> loadAll() throws Exception;
    public boolean save(ProductionTaskDto dto) throws Exception;
    public boolean update(ProductionTaskDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<ProductionTaskDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
    public ArrayList<String> getAllProductionIds() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllTaskIds() throws SQLException, ClassNotFoundException;
}

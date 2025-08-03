package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.ProductionDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductionBO extends SuperBO {
    public ArrayList<ProductionDto> loadAll() throws Exception;
    public boolean save(ProductionDto dto) throws Exception;
    public boolean update(ProductionDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<ProductionDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException;
}

package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.SupplyDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplyBO extends SuperBO {
    public ArrayList<SupplyDto> loadAll() throws Exception;
    public boolean save(SupplyDto dto) throws Exception;
    public boolean update(SupplyDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<SupplyDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
    public ArrayList<String> getAllSupplierIds() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllMaterial() throws SQLException, ClassNotFoundException;
}

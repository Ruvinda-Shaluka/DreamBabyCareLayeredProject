package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.SupplierDto;

import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    public ArrayList<SupplierDto> loadAll() throws Exception;
    public boolean save(SupplierDto dto) throws Exception;
    public boolean update(SupplierDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<SupplierDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
}

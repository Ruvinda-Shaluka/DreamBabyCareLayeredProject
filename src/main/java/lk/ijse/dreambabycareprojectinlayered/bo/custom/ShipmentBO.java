package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.ShipmentDto;

import java.util.ArrayList;

public interface ShipmentBO extends SuperBO {
    public ArrayList<ShipmentDto> loadAll() throws Exception;
    public boolean save(ShipmentDto dto) throws Exception;
    public boolean update(ShipmentDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<ShipmentDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
}

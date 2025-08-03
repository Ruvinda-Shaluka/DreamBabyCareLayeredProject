package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.ShipmentBO;
import lk.ijse.dreambabycareprojectinlayered.dto.ShipmentDto;

import java.util.ArrayList;

public class ShipmentBOImpl implements ShipmentBO {
    @Override
    public ArrayList<ShipmentDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(ShipmentDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(ShipmentDto dto) throws Exception {
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
    public ArrayList<ShipmentDto> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }
}

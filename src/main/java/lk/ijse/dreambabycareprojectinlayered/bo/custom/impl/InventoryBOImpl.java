package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.InventoryBO;
import lk.ijse.dreambabycareprojectinlayered.dto.InventoryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryBOImpl implements InventoryBO {
    @Override
    public ArrayList<InventoryDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(InventoryDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(InventoryDto dto) throws Exception {
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
    public ArrayList<InventoryDto> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }

    @Override
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean reduceItemQty(String itemId, int cartQty) {
        return false;
    }

    @Override
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getItemNameById(String itemId) {
        return "";
    }

    @Override
    public boolean increaseInventoryQty(String itemId, int amount) {
        return false;
    }
}

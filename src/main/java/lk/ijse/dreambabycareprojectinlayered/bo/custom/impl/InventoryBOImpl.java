package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.InventoryBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.InventoryDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.InventoryDto;
import lk.ijse.dreambabycareprojectinlayered.entity.InventoryEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryBOImpl implements InventoryBO {
    InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INVENTORY);
    @Override
    public ArrayList<InventoryDto> loadAll() throws Exception {
        ArrayList<InventoryEntity> entityArrayList = inventoryDAO.loadAll();
        ArrayList<InventoryDto> list = new ArrayList<>();
        for (InventoryEntity entity : entityArrayList) {
            list.add(new InventoryDto(
               entity.getInventory_id(),
               entity.getItem_name(),
               entity.getPrinted_embroidered(),
               entity.getSize(),
               entity.getUnit_price(),
               entity.getQuantity_available(),
               entity.getStored_location()
            ));
        }
        return list;
    }

    @Override
    public boolean save(InventoryDto dto) throws Exception {
        return inventoryDAO.save(new InventoryEntity(
                dto.getInventory_id(),
                dto.getItem_name(),
                dto.getPrinted_embroidered(),
                dto.getSize(),
                dto.getUnit_price(),
                dto.getQuantity_available(),
                dto.getStored_location()
        ));
    }

    @Override
    public boolean update(InventoryDto dto) throws Exception {
        return inventoryDAO.update(new InventoryEntity(
                dto.getInventory_id(),
                dto.getItem_name(),
                dto.getPrinted_embroidered(),
                dto.getSize(),
                dto.getUnit_price(),
                dto.getQuantity_available(),
                dto.getStored_location()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return inventoryDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return inventoryDAO.exists(id);
    }

    @Override
    public ArrayList<InventoryDto> search(String text) throws Exception {
        ArrayList<InventoryEntity> entityArrayList = inventoryDAO.search(text);
        ArrayList<InventoryDto> list = new ArrayList<>();
        for (InventoryEntity entity : entityArrayList) {
            list.add(new InventoryDto(
                    entity.getInventory_id(),
                    entity.getItem_name(),
                    entity.getPrinted_embroidered(),
                    entity.getSize(),
                    entity.getUnit_price(),
                    entity.getQuantity_available(),
                    entity.getStored_location()
            ));
        }
        return list;
    }

    @Override
    public String generateNewId() throws Exception {
        return inventoryDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        return inventoryDAO.getAllItemIds();
    }

    @Override
    public boolean reduceItemQty(String itemId, int cartQty) {
        return inventoryDAO.reduceItemQty(itemId, cartQty);
    }

    @Override
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException {
        return inventoryDAO.getAllInventoryIds();
    }

    @Override
    public String getItemNameById(String itemId) {
        return inventoryDAO.getItemNameById(itemId);
    }

    @Override
    public boolean increaseInventoryQty(String itemId, int amount) {
        return inventoryDAO.increaseInventoryQty(itemId, amount);
    }
}

package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.SupplyBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SupplyDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.SupplyDto;
import lk.ijse.dreambabycareprojectinlayered.entity.SupplyEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplyBOImpl implements SupplyBO {
    SupplyDAO supplyDAO = (SupplyDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLY);
    @Override
    public ArrayList<SupplyDto> loadAll() throws Exception {
        ArrayList<SupplyEntity> supplyEntities = supplyDAO.loadAll();
        ArrayList<SupplyDto> supplyDtos = new ArrayList<>();
        for (SupplyEntity supplyEntity : supplyEntities) {
            supplyDtos.add(new SupplyDto(
                    supplyEntity.getSupply_id(),
                    supplyEntity.getSupplier_id(),
                    supplyEntity.getMaterial_id(),
                    supplyEntity.getAmount(),
                    supplyEntity.getQuantity()
            ));
        }
        return supplyDtos;
    }

    @Override
    public boolean save(SupplyDto dto) throws Exception {
        return supplyDAO.save(new SupplyEntity(
                dto.getSupply_id(),
                dto.getSupplier_id(),
                dto.getMaterial_id(),
                dto.getAmount(),
                dto.getQuantity()
        ));
    }

    @Override
    public boolean update(SupplyDto dto) throws Exception {
        return supplyDAO.update(new SupplyEntity(
                dto.getSupply_id(),
                dto.getSupplier_id(),
                dto.getMaterial_id(),
                dto.getAmount(),
                dto.getQuantity()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return supplyDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return supplyDAO.exists(id);
    }

    @Override
    public ArrayList<SupplyDto> search(String text) throws Exception {
        ArrayList<SupplyEntity> supplyEntities = supplyDAO.search(text);
        ArrayList<SupplyDto> supplyDtos = new ArrayList<>();
        for (SupplyEntity supplyEntity : supplyEntities) {
            supplyDtos.add(new SupplyDto(
                    supplyEntity.getSupply_id(),
                    supplyEntity.getSupplier_id(),
                    supplyEntity.getMaterial_id(),
                    supplyEntity.getAmount(),
                    supplyEntity.getQuantity()
            ));
        }
        return supplyDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return supplyDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllSupplierIds() throws SQLException, ClassNotFoundException {
        return supplyDAO.getAllSupplierIds();
    }

    @Override
    public ArrayList<String> getAllMaterial() throws SQLException, ClassNotFoundException {
        return supplyDAO.getAllMaterial();
    }
}

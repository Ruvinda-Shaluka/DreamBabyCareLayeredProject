package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.ProductionBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.ProductionDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.ProductionDto;
import lk.ijse.dreambabycareprojectinlayered.entity.ProductionEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductionBOImpl implements ProductionBO {
    ProductionDAO productionDAO = (ProductionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRODUCTION);
    @Override
    public ArrayList<ProductionDto> loadAll() throws Exception {
        ArrayList<ProductionEntity> productionEntities = productionDAO.loadAll();
        ArrayList<ProductionDto> productionDtos = new ArrayList<>();
        for (ProductionEntity productionEntity : productionEntities) {
            productionDtos.add(new ProductionDto(
                    productionEntity.getProduction_id(),
                    productionEntity.getInventory_id(),
                    productionEntity.getDescription(),
                    productionEntity.getStatus()
            ));
        }
        return productionDtos;
    }

    @Override
    public boolean save(ProductionDto dto) throws Exception {
        return productionDAO.save(new ProductionEntity(
                dto.getProduction_id(),
                dto.getInventory_id(),
                dto.getDescription(),
                dto.getStatus()
        ));
    }

    @Override
    public boolean update(ProductionDto dto) throws Exception {
        return productionDAO.update(new ProductionEntity(
                dto.getProduction_id(),
                dto.getInventory_id(),
                dto.getDescription(),
                dto.getStatus()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return productionDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return productionDAO.exists(id);
    }

    @Override
    public ArrayList<ProductionDto> search(String text) throws Exception {
        ArrayList<ProductionEntity> productionEntities = productionDAO.search(text);
        ArrayList<ProductionDto> productionDtos = new ArrayList<>();
        for (ProductionEntity productionEntity : productionEntities) {
            productionDtos.add(new ProductionDto(
                    productionEntity.getProduction_id(),
                    productionEntity.getInventory_id(),
                    productionEntity.getDescription(),
                    productionEntity.getStatus()
            ));
        }
        return productionDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return productionDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException {
        return productionDAO.getAllInventoryIds();
    }
}

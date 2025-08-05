package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.ProductionTaskBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.ProductionTaskDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.ProductionTaskDto;
import lk.ijse.dreambabycareprojectinlayered.entity.ProductionTaskEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductionTaskBOImpl implements ProductionTaskBO {
    ProductionTaskDAO productionTaskDAO = (ProductionTaskDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRODUCTION_TASK);
    @Override
    public ArrayList<ProductionTaskDto> loadAll() throws Exception {
        ArrayList<ProductionTaskEntity> productionTaskEntities = productionTaskDAO.loadAll();
        ArrayList<ProductionTaskDto> productionTaskDtos = new ArrayList<>();
        for (ProductionTaskEntity productionTaskEntity : productionTaskEntities) {
            productionTaskDtos.add(new ProductionTaskDto(
                    productionTaskEntity.getProduction_id(),
                    productionTaskEntity.getProduction_id(),
                    productionTaskEntity.getTask_id()
                    ));
        }
        return productionTaskDtos;
    }

    @Override
    public boolean save(ProductionTaskDto dto) throws Exception {
        return productionTaskDAO.save(new ProductionTaskEntity(
                dto.getProduction_task_id(),
                dto.getProduction_id(),
                dto.getTask_id()
        ));
    }

    @Override
    public boolean update(ProductionTaskDto dto) throws Exception {
        return productionTaskDAO.update(new ProductionTaskEntity(
                dto.getProduction_task_id(),
                dto.getProduction_id(),
                dto.getTask_id()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return productionTaskDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return productionTaskDAO.exists(id);
    }

    @Override
    public ArrayList<ProductionTaskDto> search(String text) throws Exception {
        ArrayList<ProductionTaskEntity> productionTaskEntities = productionTaskDAO.search(text);
        ArrayList<ProductionTaskDto> productionTaskDtos = new ArrayList<>();
        for (ProductionTaskEntity productionTaskEntity : productionTaskEntities) {
            productionTaskDtos.add(new ProductionTaskDto(
                    productionTaskEntity.getProduction_task_id(),
                    productionTaskEntity.getProduction_id(),
                    productionTaskEntity.getTask_id()
            ));
        }
        return productionTaskDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return productionTaskDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllProductionIds() throws SQLException, ClassNotFoundException {
        return productionTaskDAO.getAllProductionIds();
    }

    @Override
    public ArrayList<String> getAllTaskIds() throws SQLException, ClassNotFoundException {
        return productionTaskDAO.getAllTaskIds();
    }
}

package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.MaterialUsageBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.MaterialUsageDAO;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.impl.MaterialUsageDAOImpl;
import lk.ijse.dreambabycareprojectinlayered.dto.MaterialUsageDto;
import lk.ijse.dreambabycareprojectinlayered.entity.MaterialUsageEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialUsageBOImpl implements MaterialUsageBO {
    MaterialUsageDAO materialUsageDAO = (MaterialUsageDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MATERIAL_USAGE);
    @Override
    public ArrayList<MaterialUsageDto> loadAll() throws Exception {
        ArrayList<MaterialUsageEntity> materialUsageEntities = materialUsageDAO.loadAll();
        ArrayList<MaterialUsageDto> materialUsageDtos = new ArrayList<>();
        for (MaterialUsageEntity entity : materialUsageEntities) {
            materialUsageDtos.add(new MaterialUsageDto(
                    entity.getUsage_id(),
                    entity.getProduction_id(),
                    entity.getMaterial_id(),
                    entity.getQuantity_used()
            ));
        }
        return materialUsageDtos;
    }

    @Override
    public boolean save(MaterialUsageDto dto) throws Exception {
        return materialUsageDAO.save(new MaterialUsageEntity(
                dto.getUsage_id(),
                dto.getProduction_id(),
                dto.getMaterial_id(),
                dto.getQuantity_used()
        ));
    }

    @Override
    public boolean update(MaterialUsageDto dto) throws Exception {
        return materialUsageDAO.update(new MaterialUsageEntity(
                dto.getUsage_id(),
                dto.getProduction_id(),
                dto.getMaterial_id(),
                dto.getQuantity_used()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return materialUsageDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return materialUsageDAO.exists(id);
    }

    @Override
    public ArrayList<MaterialUsageDto> search(String text) throws Exception {
        ArrayList<MaterialUsageEntity> materialUsageEntities = materialUsageDAO.search(text);
        ArrayList<MaterialUsageDto> materialUsageDtos = new ArrayList<>();
        for (MaterialUsageEntity entity : materialUsageEntities) {
            materialUsageDtos.add(new MaterialUsageDto(
                    entity.getUsage_id(),
                    entity.getProduction_id(),
                    entity.getMaterial_id(),
                    entity.getQuantity_used()
            ));
        }
        return materialUsageDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return materialUsageDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllProductionIds() throws SQLException, ClassNotFoundException {
        return materialUsageDAO.getAllProductionIds();
    }

    @Override
    public ArrayList<String> getAllMaterialIds() throws SQLException, ClassNotFoundException {
        return materialUsageDAO.getAllMaterialIds();
    }
}

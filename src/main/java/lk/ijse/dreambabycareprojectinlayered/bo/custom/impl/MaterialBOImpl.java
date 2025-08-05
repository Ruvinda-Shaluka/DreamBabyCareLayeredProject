package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.MaterialBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.MaterialDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.MaterialDto;
import lk.ijse.dreambabycareprojectinlayered.entity.MaterialEntity;

import java.util.ArrayList;

public class MaterialBOImpl implements MaterialBO {
    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MATERIAL);
    @Override
    public ArrayList<MaterialDto> loadAll() throws Exception {
        ArrayList<MaterialEntity> materialEntityArrayList = materialDAO.loadAll();
        ArrayList<MaterialDto> materialDtoArrayList = new ArrayList<>();
        for (MaterialEntity materialEntity : materialEntityArrayList) {
            materialDtoArrayList.add(new MaterialDto(
                    materialEntity.getMaterial_id(),
                    materialEntity.getName(),
                    materialEntity.getColor_type(),
                    materialEntity.getQuantity()
            ));
        }
        return materialDtoArrayList;
    }

    @Override
    public boolean save(MaterialDto dto) throws Exception {
        return materialDAO.save(new MaterialEntity(
                dto.getMaterial_id(),
                dto.getName(),
                dto.getColor_type(),
                dto.getQuantity()
        ));
    }

    @Override
    public boolean update(MaterialDto dto) throws Exception {
        return materialDAO.update(new MaterialEntity(
                dto.getMaterial_id(),
                dto.getName(),
                dto.getColor_type(),
                dto.getQuantity()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return materialDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return materialDAO.exists(id);
    }

    @Override
    public ArrayList<MaterialDto> search(String text) throws Exception {
        ArrayList<MaterialEntity> materialEntities = materialDAO.search(text);
        ArrayList<MaterialDto> materialDtos = new ArrayList<>();
        for (MaterialEntity materialEntity : materialEntities) {
            materialDtos.add(new MaterialDto(
                    materialEntity.getMaterial_id(),
                    materialEntity.getName(),
                    materialEntity.getColor_type(),
                    materialEntity.getQuantity()
            ));
        }
        return materialDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return materialDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllMaterialIds() {
        return materialDAO.getAllMaterialIds();
    }

    @Override
    public String getMaterialNameById(String materialId) {
        return materialDAO.getMaterialNameById(materialId);
    }

    @Override
    public String getMaterialColorTypeById(String materialId) {
        return materialDAO.getMaterialColorTypeById(materialId);
    }

    @Override
    public String getMaterialQtyById(String materialId) {
        return materialDAO.getMaterialQtyById(materialId);
    }

    @Override
    public boolean reduceMaterialQty(String materialId, int materialUsage) {
        return materialDAO.reduceMaterialQty(materialId, materialUsage);
    }
}

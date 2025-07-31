package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.MaterialEntity;

import java.util.ArrayList;

public interface MaterialDAO extends CrudDAO<MaterialEntity> {
    public ArrayList<String> getAllMaterialIds();
    public String getMaterialNameById(String materialId);
    public String getMaterialColorTypeById(String materialId);
    public String getMaterialQtyById(String materialId);
    public boolean reduceMaterialQty(String materialId, int materialUsage);

}

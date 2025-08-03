package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.MaterialDto;

import java.util.ArrayList;

public interface MaterialBO extends SuperBO {
    public ArrayList<MaterialDto> loadAll() throws Exception;
    public boolean save(MaterialDto dto) throws Exception;
    public boolean update(MaterialDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<MaterialDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
    public ArrayList<String> getAllMaterialIds();
    public String getMaterialNameById(String materialId);
    public String getMaterialColorTypeById(String materialId);
    public String getMaterialQtyById(String materialId);
    public boolean reduceMaterialQty(String materialId, int materialUsage);

}

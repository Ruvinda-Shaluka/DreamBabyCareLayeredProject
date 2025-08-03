package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.MaterialBO;
import lk.ijse.dreambabycareprojectinlayered.dto.MaterialDto;

import java.util.ArrayList;

public class MaterialBOImpl implements MaterialBO {
    @Override
    public ArrayList<MaterialDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(MaterialDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(MaterialDto dto) throws Exception {
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
    public ArrayList<MaterialDto> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }

    @Override
    public ArrayList<String> getAllMaterialIds() {
        return null;
    }

    @Override
    public String getMaterialNameById(String materialId) {
        return "";
    }

    @Override
    public String getMaterialColorTypeById(String materialId) {
        return "";
    }

    @Override
    public String getMaterialQtyById(String materialId) {
        return "";
    }

    @Override
    public boolean reduceMaterialQty(String materialId, int materialUsage) {
        return false;
    }
}

package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.MaterialUsageEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialUsageDAO extends CrudDAO<MaterialUsageEntity> {
    public ArrayList<String> getAllProductionIds() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllMaterialIds() throws SQLException, ClassNotFoundException;
}

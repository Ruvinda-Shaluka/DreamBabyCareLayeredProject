package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.ProductionEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductionDAO extends CrudDAO<ProductionEntity> {
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException;
}

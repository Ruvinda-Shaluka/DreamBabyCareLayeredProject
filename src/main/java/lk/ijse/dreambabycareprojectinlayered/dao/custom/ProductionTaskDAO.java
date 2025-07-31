package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.ProductionTaskEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductionTaskDAO extends CrudDAO<ProductionTaskEntity> {
    public ArrayList<String> getAllProductionIds() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllTaskIds() throws SQLException, ClassNotFoundException;
}

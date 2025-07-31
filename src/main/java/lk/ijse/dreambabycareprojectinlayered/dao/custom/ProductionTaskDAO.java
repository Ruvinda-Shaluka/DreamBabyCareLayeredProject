package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.ProductionTaskEntity;

import java.util.ArrayList;

public interface ProductionTaskDAO extends CrudDAO<ProductionTaskEntity> {
    public ArrayList<String> getAllProductionIds();
    public ArrayList<String> getAllTaskIds();
}

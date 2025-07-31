package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.SupplyEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplyDAO extends CrudDAO<SupplyEntity> {
    public ArrayList<String> getAllSupplierIds() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllMaterial() throws SQLException, ClassNotFoundException;

}

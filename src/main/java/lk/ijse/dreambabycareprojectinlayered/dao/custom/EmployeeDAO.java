package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.EmployeeEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<EmployeeEntity> {
    public ArrayList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException;
    public String getEmployeeNameById(String employeeId);
    public String getEmployeeRoleById(String employeeId);
}

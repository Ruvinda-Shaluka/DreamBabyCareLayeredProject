package lk.ijse.dreambabycareprojectinlayered.dao.custom;

import lk.ijse.dreambabycareprojectinlayered.dao.CrudDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.TaskEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TaskDAO extends CrudDAO<TaskEntity> {
    public ArrayList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException;
}

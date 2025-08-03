package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.TaskBO;
import lk.ijse.dreambabycareprojectinlayered.dto.TaskDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskBOImpl implements TaskBO {
    @Override
    public ArrayList<TaskDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(TaskDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(TaskDto dto) throws Exception {
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
    public ArrayList<TaskDto> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }

    @Override
    public ArrayList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}

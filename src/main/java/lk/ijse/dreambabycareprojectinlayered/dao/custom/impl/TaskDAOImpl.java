package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.TaskDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.TaskEntity;

import java.util.ArrayList;

public class TaskDAOImpl implements TaskDAO {
    @Override
    public ArrayList<TaskEntity> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(TaskEntity dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(TaskEntity dto) throws Exception {
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
    public ArrayList<TaskEntity> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }
}

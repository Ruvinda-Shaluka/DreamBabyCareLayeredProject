package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.UserDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.UserEntity;

import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<UserEntity> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(UserEntity dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(UserEntity dto) throws Exception {
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
    public ArrayList<UserEntity> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }
}

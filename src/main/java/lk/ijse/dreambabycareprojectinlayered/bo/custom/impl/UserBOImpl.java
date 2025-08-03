package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.UserBO;
import lk.ijse.dreambabycareprojectinlayered.dto.UserDto;

import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    @Override
    public ArrayList<UserDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(UserDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(UserDto dto) throws Exception {
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
    public ArrayList<UserDto> search(String text) throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        return "";
    }
}

package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.UserDto;

import java.util.ArrayList;

public interface UserBO extends SuperBO {
    public ArrayList<UserDto> loadAll() throws Exception;
    public boolean save(UserDto dto) throws Exception;
    public boolean update(UserDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<UserDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
}

package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.UserBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.UserDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.UserDto;
import lk.ijse.dreambabycareprojectinlayered.entity.UserEntity;

import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public ArrayList<UserDto> loadAll() throws Exception {
        ArrayList<UserEntity> userEntities = userDAO.loadAll();
        ArrayList<UserDto> userDtos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userDtos.add(new UserDto(
                    userEntity.getUser_id(),
                    userEntity.getUser_name(),
                    userEntity.getPassword(),
                    userEntity.getEmail(),
                    userEntity.getName(),
                    userEntity.getPhone_number()
            ));
        }
        return userDtos;
    }

    @Override
    public boolean save(UserDto dto) throws Exception {
        return userDAO.save(new UserEntity(
                dto.getUser_id(),
                dto.getUser_name(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getName(),
                dto.getPhone_number()
        ));
    }

    @Override
    public boolean update(UserDto dto) throws Exception {
        return userDAO.update(new UserEntity(
                dto.getUser_id(),
                dto.getUser_name(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getName(),
                dto.getPhone_number()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return userDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return userDAO.exists(id);
    }

    @Override
    public ArrayList<UserDto> search(String text) throws Exception {
        ArrayList<UserEntity> userEntities = userDAO.search(text);
        ArrayList<UserDto> userDtos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userDtos.add(new UserDto(
                    userEntity.getUser_id(),
                    userEntity.getUser_name(),
                    userEntity.getPassword(),
                    userEntity.getEmail(),
                    userEntity.getName(),
                    userEntity.getPhone_number()
            ));
        }
        return userDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return userDAO.generateNewId();
    }
}

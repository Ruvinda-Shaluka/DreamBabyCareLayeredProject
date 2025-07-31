package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.UserDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.UserEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<UserEntity> loadAll() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM user");
        ArrayList<UserEntity> userDto = new ArrayList<>();

        while (rst.next()) {
            userDto.add(new UserEntity(
                    rst.getString("user_id"),
                    rst.getString("user_name"),
                    rst.getString("password"),
                    rst.getString("email"),
                    rst.getString("name"),
                    rst.getString("phone_number")
            ));
        }
        return userDto;
    }

    @Override
    public boolean save(UserEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "INSERT INTO user VALUES (?,?,?,?,?,?)",
                dto.getUser_id(),
                dto.getUser_name(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getName(),
                dto.getPhone_number()
        );
    }

    @Override
    public boolean update(UserEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE user SET user_name = ?, password = ?, email = ?, name = ?, phone_number = ?WHERE user_id= ?",

                dto.getUser_name(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getName(),
                dto.getPhone_number(),
                dto.getUser_id()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate("DELETE FROM user WHERE user_id = ?", id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM user WHERE user_id = ?", id);
        return rst.next();
    }

    @Override
    public ArrayList<UserEntity> search(String text) throws Exception {
        ArrayList<UserEntity> userDto = new ArrayList<>();
        String sql ="SELECT * FROM user WHERE user_id LIKE ? OR user_name LIKE ? OR email LIKE ? OR name LIKE ? OR phone_number LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet rst = SQLUtil.executeQuery(sql, pattern, pattern, pattern, pattern, pattern);
        while (rst.next()) {
            userDto.add(new UserEntity(
                    rst.getString("user_id"),
                    rst.getString("user_name"),
                    rst.getString("password"),
                    rst.getString("email"),
                    rst.getString("name"),
                    rst.getString("phone_number")
            ));
        }
        return userDto;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1");
        String tableCharacter = "U"; // Use any character Ex:- customer table for C, item table for I
        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // "C001"
            String lastIdNumberString = lastId.substring(tableCharacter.length()); // "001"
            int lastIdNumber = Integer.parseInt(lastIdNumberString); // 1
            int nextIdNUmber = lastIdNumber + 1; // 2
            // "C002"
            return String.format(tableCharacter + "%03d", nextIdNUmber);
        }
        // No data recode in table so return initial primary key
        return tableCharacter + "001";
    }
}

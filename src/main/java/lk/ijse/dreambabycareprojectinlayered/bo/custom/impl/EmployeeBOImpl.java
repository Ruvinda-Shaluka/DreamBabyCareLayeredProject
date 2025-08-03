package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.EmployeeBO;
import lk.ijse.dreambabycareprojectinlayered.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    @Override
    public ArrayList<EmployeeDto> loadAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(EmployeeDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(EmployeeDto dto) throws Exception {
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
    public ArrayList<EmployeeDto> search(String text) throws Exception {
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

    @Override
    public String getEmployeeNameById(String employeeId) {
        return "";
    }

    @Override
    public String getEmployeeRoleById(String employeeId) {
        return "";
    }
}

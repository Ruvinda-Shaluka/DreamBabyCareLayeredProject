package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public ArrayList<EmployeeDto> loadAll() throws Exception;
    public boolean save(EmployeeDto dto) throws Exception;
    public boolean update(EmployeeDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<EmployeeDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
    public ArrayList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException;
    public String getEmployeeNameById(String employeeId);
    public String getEmployeeRoleById(String employeeId);

}

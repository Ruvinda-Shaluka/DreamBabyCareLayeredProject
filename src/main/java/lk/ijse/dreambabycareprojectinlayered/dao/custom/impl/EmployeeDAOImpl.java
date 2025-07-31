package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.EmployeeDAO;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.entity.EmployeeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT employee_id FROM employee");
        ArrayList<String> employees = new ArrayList<>();
        while (resultSet.next()) {
            employees.add(resultSet.getString("employee_id"));
        }
        return employees;
    }

    @Override
    public String getEmployeeNameById(String employeeId) {
        try {
            ResultSet resultSet = SQLUtil.executeQuery("SELECT name FROM employee WHERE employee_id = ?", employeeId);
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load employee name..!").show();
        }
        return null;
    }

    @Override
    public String getEmployeeRoleById(String employeeId) {
        try {
            ResultSet resultSet = SQLUtil.executeQuery("SELECT role FROM employee WHERE employee_id = ?", employeeId);
            if (resultSet.next()) {
                return resultSet.getString("role");
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load employee role..!").show();
        }
        return null;
    }

    @Override
    public ArrayList<EmployeeEntity> loadAll() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM employee");
        ArrayList<EmployeeEntity> employees = new ArrayList<>();
        while (resultSet.next()) {
            employees.add(new EmployeeEntity(
                    resultSet.getString("employee_id"),
                    resultSet.getString("name"),
                    resultSet.getString("nic"),
                    resultSet.getString("number"),
                    resultSet.getString("role")
                    ));
        }
        return employees;
    }

    @Override
    public boolean save(EmployeeEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "INSERT INTO employee VALUES (?,?,?,?,?)",
                dto.getEmployee_id(),
                dto.getName(),
                dto.getNic(),
                dto.getNumber(),
                dto.getRole()
        );
    }

    @Override
    public boolean update(EmployeeEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE employee SET name = ?, nic = ?, number = ?, role = ? WHERE employee_id= ?",
                dto.getName(),
                dto.getNic(),
                dto.getNumber(),
                dto.getRole(),
                dto.getEmployee_id()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate(
                "DELETE FROM employee WHERE employee_id = ?",
                id
        );
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM employee WHERE employee_id = ?", id);
        return resultSet.next();
    }

    @Override
    public ArrayList<EmployeeEntity> search(String text) throws Exception {
        ArrayList<EmployeeEntity> employeeDtoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE employee_id LIKE ? OR name LIKE ? OR nic LIKE ? OR number LIKE ? OR role LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet resultSet = SQLUtil.executeQuery(sql, pattern, pattern, pattern, pattern, pattern);

        while (resultSet.next()){
            employeeDtoArrayList.add(new EmployeeEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));

        }
        return employeeDtoArrayList;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT employee_id FROM employee ORDER BY employee_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("employee_id");
            int newCustomerId = Integer.parseInt(id.replace("EMP", "")) + 1;
            return String.format("EMP%03d", newCustomerId);
        } else {
            return "EMP001";
        }
    }
}

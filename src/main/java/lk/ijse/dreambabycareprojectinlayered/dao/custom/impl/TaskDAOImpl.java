package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.TaskDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.TaskEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskDAOImpl implements TaskDAO {

    @Override
    public ArrayList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT employee_id FROM employee");
        ArrayList<String> employeeIds = new ArrayList<>();
        while (resultSet.next()) {
            employeeIds.add(resultSet.getString("employee_id"));
        }
        return employeeIds;
    }
    @Override
    public ArrayList<TaskEntity> loadAll() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM task");
        ArrayList<TaskEntity> taskDto = new ArrayList<>();

        while (rst.next()) {
            taskDto.add(new TaskEntity(
                    rst.getString("task_id"),
                    rst.getString("employee_id"),
                    rst.getString("description"),
                    rst.getString("status")
            ));
        }
        return taskDto;
    }

    @Override
    public boolean save(TaskEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "INSERT INTO task VALUES (?,?,?,?)",
                dto.getTask_id(),
                dto.getEmployee_id(),
                dto.getDescription(),
                dto.getStatus()
        );
    }

    @Override
    public boolean update(TaskEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE task SET employee_id = ?, description = ?, status = ?WHERE task_id= ?",
                dto.getEmployee_id(),
                dto.getDescription(),
                dto.getStatus(),
                dto.getTask_id()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate("DELETE FROM task WHERE task_id = ?", id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM task WHERE task_id = ?", id);
        return resultSet.next();
    }

    @Override
    public ArrayList<TaskEntity> search(String text) throws Exception {
        ArrayList<TaskEntity> taskDtoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM task WHERE task_id LIKE ? OR employee_id LIKE ? OR description LIKE ? OR status LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet rst = SQLUtil.executeQuery(sql, pattern, pattern, pattern, pattern);

        while(rst.next()){
            taskDtoArrayList.add(new TaskEntity(
                    rst.getString("task_id"),
                    rst.getString("employee_id"),
                    rst.getString("description"),
                    rst.getString("status")
            ));
        }
        return taskDtoArrayList;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT task_id FROM task ORDER BY task_id DESC LIMIT 1");
        String tableCharacter = "TASK"; // Use any character Ex:- customer table for C, item table for I
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

package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.ProductionTaskDAO;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.entity.ProductionTaskEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductionTaskDAOImpl implements ProductionTaskDAO {
    @Override
    public ArrayList<String> getAllProductionIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> productionIds = new ArrayList<>();
        ResultSet rst = SQLUtil.executeQuery("SELECT production_id FROM production");
        while (rst.next()) {
            productionIds.add(rst.getString("production_id"));
        }
        return productionIds;
    }

    @Override
    public ArrayList<String> getAllTaskIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> taskIds = new ArrayList<>();
        ResultSet rst = SQLUtil.executeQuery("SELECT task_id FROM task");
        while (rst.next()) {
            taskIds.add(rst.getString("task_id"));
        }
        return taskIds;
    }

    @Override
    public ArrayList<ProductionTaskEntity> loadAll() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM production_task");
        ArrayList<ProductionTaskEntity> productionTaskDto = new ArrayList<>();

        while (rst.next()) {
            productionTaskDto.add(new ProductionTaskEntity(
                    rst.getString("production_task_id"),
                    rst.getString("production_id"),
                    rst.getString("task_id")
            ));
        }
        return productionTaskDto;
    }

    @Override
    public boolean save(ProductionTaskEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "INSERT INTO production_task VALUES (?,?,?)",
                dto.getProduction_task_id(),
                dto.getProduction_id(),
                dto.getTask_id()
        );
    }

    @Override
    public boolean update(ProductionTaskEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE production_task SET production_id = ?, task_id = ?WHERE production_task_id= ?",
                dto.getProduction_id(),
                dto.getTask_id(),
                dto.getProduction_task_id()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate("DELETE FROM production_task WHERE production_task_id = ?", id);

    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM production_task WHERE production_task_id = ?", id);
        return resultSet.next();
    }

    @Override
    public ArrayList<ProductionTaskEntity> search(String text) throws Exception {
        ArrayList<ProductionTaskEntity> productionTaskDtoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM production_task WHERE production_task_id LIKE ? OR production_id LIKE ? OR task_id LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet rst = SQLUtil.executeQuery(sql, pattern, pattern, pattern);

        while (rst.next()){
            productionTaskDtoArrayList.add(new ProductionTaskEntity(
                    rst.getString("production_task_id"),
                    rst.getString("production_id"),
                    rst.getString("task_id")
            ));
        }
        return productionTaskDtoArrayList;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT production_task_id FROM production_task ORDER BY production_task_id DESC LIMIT 1");
        String tableCharacter = "PTASK"; // Use any character Ex:- customer table for C, item table for I
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

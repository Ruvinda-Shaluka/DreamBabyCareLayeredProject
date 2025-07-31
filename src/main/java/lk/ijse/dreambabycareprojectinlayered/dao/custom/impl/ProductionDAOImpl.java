package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.ProductionDAO;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.entity.ProductionEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductionDAOImpl implements ProductionDAO {
    @Override
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT inventory_id FROM inventory");
        ArrayList<String> inventoryIds = new ArrayList<>();
        while (resultSet.next()) {
            inventoryIds.add(resultSet.getString("inventory_id"));
        }
        return inventoryIds;
    }

    @Override
    public ArrayList<ProductionEntity> loadAll() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM production");
        ArrayList<ProductionEntity> productionDto = new ArrayList<>();

        while (rst.next()) {
            productionDto.add(new ProductionEntity(
                    rst.getString("production_id"),
                    rst.getString("inventory_id"),
                    rst.getString("description"),
                    rst.getString("status")
            ));
        }
        return productionDto;
    }

    @Override
    public boolean save(ProductionEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "INSERT INTO production VALUES (?,?,?,?)",
                dto.getProduction_id(),
                dto.getInventory_id(),
                dto.getDescription(),
                dto.getStatus()
        );
    }

    @Override
    public boolean update(ProductionEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE production SET inventory_id = ?, description = ?, status = ?WHERE production_id= ?",
                dto.getInventory_id(),
                dto.getDescription(),
                dto.getStatus(),
                dto.getProduction_id()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate("DELETE FROM production WHERE production_id = ?", id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM production WHERE production_id = ?", id);
        return resultSet.next();
    }

    @Override
    public ArrayList<ProductionEntity> search(String text) throws Exception {
        ArrayList<ProductionEntity> productionDtoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM production WHERE production_id LIKE ? OR inventory_id LIKE ? OR description LIKE ? OR status LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet rst = SQLUtil.executeQuery(sql, pattern, pattern, pattern, pattern);

        while (rst.next()){
            productionDtoArrayList.add(new ProductionEntity(
                    rst.getString("production_id"),
                    rst.getString("inventory_id"),
                    rst.getString("description"),
                    rst.getString("status")
            ));
        }
        return productionDtoArrayList;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT production_id FROM production ORDER BY production_id DESC LIMIT 1");
        String tableCharacter = "PROD"; // Use any character Ex:- customer table for C, item table for I
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

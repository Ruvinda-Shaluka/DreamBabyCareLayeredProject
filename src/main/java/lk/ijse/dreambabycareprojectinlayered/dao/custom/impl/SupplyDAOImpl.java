package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SupplyDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.SupplyEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplyDAOImpl implements SupplyDAO {
    @Override
    public ArrayList<String> getAllSupplierIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT supplier_id FROM supplier");
        ArrayList<String> supplierIds = new ArrayList<>();

        while (rst.next()) {
            supplierIds.add(rst.getString("supplier_id"));
        }
        return supplierIds;
    }

    @Override
    public ArrayList<String> getAllMaterial() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT material_id FROM material");
        ArrayList<String> materialIds = new ArrayList<>();

        while (rst.next()) {
            materialIds.add(rst.getString("material_id"));
        }
        return materialIds;
    }

    @Override
    public ArrayList<SupplyEntity> loadAll() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM supply");
        ArrayList<SupplyEntity> supplyDto = new ArrayList<>();

        while (rst.next()) {
            supplyDto.add(new SupplyEntity(
                    rst.getString("supply_id"),
                    rst.getString("supplier_id"),
                    rst.getString("material_id"),
                    rst.getDouble("amount"),
                    rst.getInt("quantity")
            ));
        }
        return supplyDto;
    }

    @Override
    public boolean save(SupplyEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "INSERT INTO supply VALUES (?,?,?,?,?)",
                dto.getSupply_id(),
                dto.getSupplier_id(),
                dto.getMaterial_id(),
                dto.getAmount(),
                dto.getQuantity()
        );

    }

    @Override
    public boolean update(SupplyEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE supply SET supplier_id = ?, material_id = ?, amount = ?, quantity = ?WHERE supply_id= ?",
                dto.getSupplier_id(),
                dto.getMaterial_id(),
                dto.getAmount(),
                dto.getQuantity(),
                dto.getSupply_id()

        );

    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate("DELETE FROM supply WHERE supply_id = ?", id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM supply WHERE supply_id = ?", id);
        return resultSet.next();
    }

    @Override
    public ArrayList<SupplyEntity> search(String text) throws Exception {

        ArrayList<SupplyEntity> supplyDtoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM supply WHERE supply_id LIKE ? OR supplier_id LIKE ? OR material_id LIKE ? OR amount LIKE ? OR quantity LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet rst = SQLUtil.executeQuery(sql, pattern, pattern, pattern, pattern, pattern);

        while(rst.next()){
            supplyDtoArrayList.add(new SupplyEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            ));
        }
        return supplyDtoArrayList;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT supply_id FROM supply ORDER BY supply_id DESC LIMIT 1");
        String tableCharacter = "SUPPLY"; // Use any character Ex:- customer table for C, item table for I
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

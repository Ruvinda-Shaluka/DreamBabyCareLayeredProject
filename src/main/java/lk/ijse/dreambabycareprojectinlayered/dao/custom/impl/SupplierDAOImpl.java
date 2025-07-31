package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SupplierDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.SupplierEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<SupplierEntity> loadAll() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM supplier");
        ArrayList<SupplierEntity> supplierDto = new ArrayList<>();

        while (rst.next()) {
            supplierDto.add(new SupplierEntity(
                    rst.getString("supplier_id"),
                    rst.getString("name"),
                    rst.getString("contact"),
                    rst.getString("account_details")
            ));
        }
        return supplierDto;
    }

    @Override
    public boolean save(SupplierEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "INSERT INTO supplier VALUES (?,?,?,?)",
                dto.getSupplier_id(),
                dto.getName(),
                dto.getContact(),
                dto.getAccount_details()
        );
    }

    @Override
    public boolean update(SupplierEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE supplier SET name = ?, contact = , account_details = ? WHERE supplier_id= ?",
                dto.getName(),
                dto.getContact(),
                dto.getAccount_details(),
                dto.getSupplier_id()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate("DELETE FROM supplier WHERE supplier_id = ?", id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM supplier WHERE supplier_id = ?", id);
        return resultSet.next();
    }

    @Override
    public ArrayList<SupplierEntity> search(String text) throws Exception {
        ArrayList<SupplierEntity> supplierDtoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM supplier WHERE supplier_id LIKE ? OR name LIKE ? OR contact LIKE ? OR account_details LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet rst = SQLUtil.executeQuery(sql, pattern, pattern, pattern, pattern);

        while(rst.next()){
            supplierDtoArrayList.add(new SupplierEntity(
                    rst.getString("supplier_id"),
                    rst.getString("name"),
                    rst.getString("contact"),
                    rst.getString("account_details")
            ));
        }
        return supplierDtoArrayList;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT supplier_id FROM supplier ORDER BY supplier_id DESC LIMIT 1");
        String tableCharacter = "SUP"; // Use any character Ex:- customer table for C, item table for I
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

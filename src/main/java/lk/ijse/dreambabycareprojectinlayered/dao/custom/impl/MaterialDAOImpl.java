package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.MaterialDAO;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.entity.MaterialEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialDAOImpl implements MaterialDAO {
    @Override
    public ArrayList<String> getAllMaterialIds() {
        ArrayList<String> materialIds = new ArrayList<>();
        try {
            ResultSet rst = SQLUtil.executeQuery("SELECT material_id FROM material");
            while (rst.next()) {
                materialIds.add(rst.getString("material_id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load material IDs..!").show();
        }
        return materialIds;
    }

    @Override
    public String getMaterialNameById(String materialId) {
        try {
            ResultSet resultSet = SQLUtil.executeQuery("SELECT name FROM material WHERE material_id = ?", materialId);
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load material name..!").show();
        }
        return null;
    }

    @Override
    public String getMaterialColorTypeById(String materialId) {
        try {
            ResultSet resultSet = SQLUtil.executeQuery("SELECT color_type FROM material WHERE material_id = ?", materialId);
            if (resultSet.next()) {
                return resultSet.getString("color_type");
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load material color type..!").show();
        }
        return null;
    }

    @Override
    public String getMaterialQtyById(String materialId) {
        try {
            ResultSet resultSet = SQLUtil.executeQuery("SELECT quantity FROM material WHERE material_id = ?", materialId);
            if (resultSet.next()) {
                return String.valueOf(resultSet.getInt("quantity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load material quantity..!").show();
        }
        return null;
    }

    @Override
    public boolean reduceMaterialQty(String materialId, int materialUsage) {
        try {
            ResultSet resultSet = SQLUtil.executeQuery(
                    "SELECT quantity FROM material WHERE material_id = ?",
                    materialId
            );
            if (resultSet.next()) {
                int currentQty = resultSet.getInt("quantity");
                if (currentQty >= materialUsage) {
                    int newQty = currentQty - materialUsage;
                    return SQLUtil.executeUpdate(
                            "UPDATE material SET quantity = ? WHERE material_id = ?",
                            newQty,
                            materialId);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Insufficient stock for Material ID: " + materialId).show();
                    return false;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error reducing material quantity...").show();
        }
        return false;
    }

    @Override
    public ArrayList<MaterialEntity> loadAll() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM material");
        ArrayList<MaterialEntity> materialDto = new ArrayList<>();

        while (rst.next()) {
            materialDto.add(new MaterialEntity(
                    rst.getString("material_id"),
                    rst.getString("name"),
                    rst.getString("color_type"),
                    rst.getInt("quantity")
            ));
        }
        return materialDto;
    }

    @Override
    public boolean save(MaterialEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "INSERT INTO material VALUES (?,?,?,?)",
                dto.getMaterial_id(),
                dto.getName(),
                dto.getColor_type(),
                dto.getQuantity()
        );
    }

    @Override
    public boolean update(MaterialEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE material SET name = ?, color_type = ?, quantity = ? WHERE material_id= ?",
                dto.getName(),
                dto.getColor_type(),
                dto.getQuantity(),
                dto.getMaterial_id()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate(
                "DELETE FROM material WHERE material_id = ?",id
        );
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM material WHERE material_id = ?",id);
        return resultSet.next();
    }

    @Override
    public ArrayList<MaterialEntity> search(String text) throws Exception {
        ArrayList<MaterialEntity> materialDtoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM material WHERE material_id LIKE ? OR name LIKE ? OR color_type LIKE ? OR quantity LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet rst = SQLUtil.executeQuery(sql, pattern, pattern, pattern, pattern);

        while (rst.next()){
            materialDtoArrayList.add(new MaterialEntity(
                    rst.getString("material_id"),
                    rst.getString("name"),
                    rst.getString("color_type"),
                    rst.getInt("quantity")
            ));
        }
        return materialDtoArrayList;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT material_id FROM material ORDER BY material_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("material_id");
            int newCustomerId = Integer.parseInt(id.replace("MAT", "")) + 1;
            return String.format("MAT%03d", newCustomerId);
        } else {
            return "MAT001";
        }
    }

}

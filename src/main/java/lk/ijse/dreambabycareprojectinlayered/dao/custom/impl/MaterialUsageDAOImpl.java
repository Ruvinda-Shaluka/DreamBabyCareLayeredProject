package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.MaterialUsageDAO;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.entity.MaterialUsageEntity;
import net.sf.jasperreports.engine.virtualization.SqlDateSerializer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialUsageDAOImpl implements MaterialUsageDAO {
    @Override
    public ArrayList<String> getAllProductionIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> productionIds = new ArrayList<>();
        ResultSet rst = SQLUtil.executeQuery("SELECT production_id FROM production");
        while (rst.next()) {
            productionIds.add(rst.getString(1));
        }
        return productionIds;
    }

    @Override
    public ArrayList<String> getAllMaterialIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> materialIds = new ArrayList<>();
        ResultSet rst = SQLUtil.executeQuery("SELECT material_id FROM material");
        while (rst.next()) {
            materialIds.add(rst.getString(1));
        }
        return materialIds;
    }

    @Override
    public ArrayList<MaterialUsageEntity> loadAll() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM material_usage");
        ArrayList<MaterialUsageEntity> materialUsageDto = new ArrayList<>();

        while (rst.next()) {
            materialUsageDto.add(new MaterialUsageEntity(
                    rst.getString("usage_id"),
                    rst.getString("production_id"),
                    rst.getString("material_id"),
                    rst.getInt("quantity_used")
            ));
        }
        return materialUsageDto;
    }

    @Override
    public boolean save(MaterialUsageEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "INSERT INTO material_usage VALUES (?,?,?,?)",
                dto.getUsage_id(),
                dto.getProduction_id(),
                dto.getMaterial_id(),
                dto.getQuantity_used()
        );
    }

    @Override
    public boolean update(MaterialUsageEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE material_usage SET production_id = ?, material_id = ?, quantity_used = ?WHERE usage_id= ?",
                dto.getProduction_id(),
                dto.getMaterial_id(),
                dto.getQuantity_used(),
                dto.getUsage_id()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate("DELETE FROM material_usage WHERE usage_id = ?", id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM material_usage WHERE usage_id = ?", id);
        return resultSet.next();
    }

    @Override
    public ArrayList<MaterialUsageEntity> search(String text) throws Exception {
        ArrayList<MaterialUsageEntity> materialUsageDtoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM material_usage WHERE usage_id LIKE ? OR production_id LIKE ? OR material_id LIKE ? OR quantity_used LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet rst = SQLUtil.executeQuery(sql,pattern, pattern, pattern, pattern);

        while(rst.next()){
            materialUsageDtoArrayList.add(new MaterialUsageEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            ));
        }
        return materialUsageDtoArrayList;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT usage_id FROM material_usage ORDER BY usage_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("usage_id");
            int newCustomerId = Integer.parseInt(id.replace("MU", "")) + 1;
            return String.format("MU%03d", newCustomerId);
        } else {
            return "MU001";
        }
    }

}

package lk.ijse.dreambabycareprojectinlayered.dao.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.SQLUtil;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.ShipmentDAO;
import lk.ijse.dreambabycareprojectinlayered.entity.ShipmentEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ShipmentDAOImpl implements ShipmentDAO {
    @Override
    public ArrayList<ShipmentEntity> loadAll() throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM shipment");
        ArrayList<ShipmentEntity> shipmentDto = new ArrayList<>();

        while (rst.next()) {
            shipmentDto.add(new ShipmentEntity(
                    rst.getString("shipment_id"),
                    rst.getString("tracking_number"),
                    rst.getDate("shipment_date").toLocalDate()
            ));
        }
        return shipmentDto;
    }

    @Override
    public boolean save(ShipmentEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "INSERT INTO shipment VALUES (?,?,?)",
                dto.getShipment_id(),
                dto.getTracking_number(),
                dto.getShipment_date()
        );
    }

    @Override
    public boolean update(ShipmentEntity dto) throws Exception {
        return SQLUtil.executeUpdate(
                "UPDATE shipment SET tracking_number = ?, shipment_date = ? WHERE shipment_id= ?",
                dto.getTracking_number(),
                dto.getShipment_date(),
                dto.getShipment_id()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.executeUpdate("DELETE FROM shipment WHERE shipment_id = ?", id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM shipment WHERE shipment_id = ?", id);
        return rst.next();
    }

    @Override
    public ArrayList<ShipmentEntity> search(String text) throws Exception {
        ArrayList<ShipmentEntity> shipmentDtoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM shipment WHERE shipment_id LIKE ? OR tracking_number LIKE ? OR shipment_date LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet rst = SQLUtil.executeQuery(sql, pattern, pattern, pattern);

        while(rst.next()){
            shipmentDtoArrayList.add(new ShipmentEntity(
                    rst.getString("shipment_id"),
                    rst.getString("tracking_number"),
                    rst.getDate("shipment_date").toLocalDate()
            ));
        }
        return shipmentDtoArrayList;
    }

    @Override
    public String generateNewId() throws Exception {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT shipment_id FROM shipment ORDER BY shipment_id DESC LIMIT 1");
        String tableCharacter = "SHP"; // Use any character Ex:- customer table for C, item table for I
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

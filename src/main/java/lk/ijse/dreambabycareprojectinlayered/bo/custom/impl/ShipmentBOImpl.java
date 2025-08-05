package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.ShipmentBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.ShipmentDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.ShipmentDto;
import lk.ijse.dreambabycareprojectinlayered.entity.ShipmentEntity;

import java.util.ArrayList;

public class ShipmentBOImpl implements ShipmentBO {
    ShipmentDAO shipmentDAO = (ShipmentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SHIPMENT);

    @Override
    public ArrayList<ShipmentDto> loadAll() throws Exception {
        ArrayList<ShipmentEntity> shipmentEntities = shipmentDAO.loadAll();
        ArrayList<ShipmentDto> shipmentDtos = new ArrayList<>();
        for (ShipmentEntity shipmentEntity : shipmentEntities) {
            shipmentDtos.add(new ShipmentDto(
                    shipmentEntity.getShipment_id(),
                    shipmentEntity.getTracking_number(),
                    shipmentEntity.getShipment_date()
            ));
        }
        return shipmentDtos;
    }

    @Override
    public boolean save(ShipmentDto dto) throws Exception {
        return shipmentDAO.save(new ShipmentEntity(
                dto.getShipment_id(),
                dto.getTracking_number(),
                dto.getShipment_date()
        ));
    }

    @Override
    public boolean update(ShipmentDto dto) throws Exception {
        return shipmentDAO.update(new ShipmentEntity(
                dto.getShipment_id(),
                dto.getTracking_number(),
                dto.getShipment_date()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return shipmentDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return shipmentDAO.exists(id);
    }

    @Override
    public ArrayList<ShipmentDto> search(String text) throws Exception {
        ArrayList<ShipmentEntity> shipmentEntities = shipmentDAO.search(text);
        ArrayList<ShipmentDto> shipmentDtos = new ArrayList<>();
        for (ShipmentEntity shipmentEntity : shipmentEntities) {
            shipmentDtos.add(new ShipmentDto(
                    shipmentEntity.getShipment_id(),
                    shipmentEntity.getTracking_number(),
                    shipmentEntity.getShipment_date()
            ));
        }
        return shipmentDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return shipmentDAO.generateNewId();
    }
}

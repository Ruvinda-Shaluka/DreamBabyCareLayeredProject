package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.SupplierBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.SupplierDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.SupplierDto;
import lk.ijse.dreambabycareprojectinlayered.entity.SupplierEntity;

import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public ArrayList<SupplierDto> loadAll() throws Exception {
        ArrayList<SupplierEntity> supplierEntities = supplierDAO.loadAll();
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        for (SupplierEntity supplierEntity : supplierEntities) {
            supplierDtos.add(new SupplierDto(
                    supplierEntity.getSupplier_id(),
                    supplierEntity.getName(),
                    supplierEntity.getContact(),
                    supplierEntity.getAccount_details()
            ));
        }
        return supplierDtos;
    }

    @Override
    public boolean save(SupplierDto dto) throws Exception {
        return supplierDAO.save(new SupplierEntity(
                dto.getSupplier_id(),
                dto.getName(),
                dto.getContact(),
                dto.getAccount_details()
        ));
    }

    @Override
    public boolean update(SupplierDto dto) throws Exception {
        return supplierDAO.update(new SupplierEntity(
                dto.getSupplier_id(),
                dto.getName(),
                dto.getContact(),
                dto.getAccount_details()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return supplierDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return supplierDAO.exists(id);
    }

    @Override
    public ArrayList<SupplierDto> search(String text) throws Exception {
        ArrayList<SupplierEntity> supplierEntities = supplierDAO.search(text);
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        for (SupplierEntity supplierEntity : supplierEntities) {
            supplierDtos.add(new SupplierDto(
                    supplierEntity.getSupplier_id(),
                    supplierEntity.getName(),
                    supplierEntity.getContact(),
                    supplierEntity.getAccount_details()
            ));
        }
        return supplierDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return supplierDAO.generateNewId();
    }
}

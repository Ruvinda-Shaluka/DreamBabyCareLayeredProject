package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.OrdersBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.OrdersDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.OrdersDto;
import lk.ijse.dreambabycareprojectinlayered.entity.OrdersEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersBOImpl implements OrdersBO {
    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERS);
    @Override
    public ArrayList<OrdersDto> loadAll() throws Exception {
        ArrayList<OrdersEntity> ordersEntities = ordersDAO.loadAll();
        ArrayList<OrdersDto> ordersDtos = new ArrayList<>();
        for (OrdersEntity ordersEntity : ordersEntities) {
            ordersDtos.add(new OrdersDto(
                    ordersEntity.getOrder_id(),
                    ordersEntity.getOrder_date(),
                    ordersEntity.getCustomer_id(),
                    ordersEntity.getShipment_id(),
                    ordersEntity.getStatus()
            ));
        }
        return ordersDtos;
    }

    @Override
    public boolean save(OrdersDto dto) throws Exception {
        return ordersDAO.save(new OrdersEntity(
                dto.getOrder_id(),
                dto.getOrder_date(),
                dto.getCustomer_id(),
                dto.getShipment_id(),
                dto.getStatus()
        ));
    }

    @Override
    public boolean update(OrdersDto dto) throws Exception {
        return ordersDAO.update(new OrdersEntity(
                dto.getOrder_id(),
                dto.getOrder_date(),
                dto.getCustomer_id(),
                dto.getShipment_id(),
                dto.getStatus()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return ordersDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return ordersDAO.exists(id);
    }

    @Override
    public ArrayList<OrdersDto> search(String text) throws Exception {
        ArrayList<OrdersEntity> ordersEntities = ordersDAO.search(text);
        ArrayList<OrdersDto> ordersDtos = new ArrayList<>();
        for (OrdersEntity ordersEntity : ordersEntities) {
            ordersDtos.add(new OrdersDto(
                    ordersEntity.getOrder_id(),
                    ordersEntity.getOrder_date(),
                    ordersEntity.getCustomer_id(),
                    ordersEntity.getShipment_id(),
                    ordersEntity.getStatus()
            ));
        }
        return ordersDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return ordersDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        return ordersDAO.getAllCustomerIds();
    }

    @Override
    public ArrayList<String> getAllShipmentIds() throws SQLException, ClassNotFoundException {
        return ordersDAO.getAllShipmentIds();
    }
}

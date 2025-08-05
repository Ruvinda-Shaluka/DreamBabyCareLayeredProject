package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.OrderItemBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.OrderItemDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.OrderItemDto;
import lk.ijse.dreambabycareprojectinlayered.entity.OrderItemEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemBOImpl implements OrderItemBO {
    OrderItemDAO orderItemDAO = (OrderItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_ITEM);
    @Override
    public ArrayList<OrderItemDto> loadAll() throws Exception {
        ArrayList<OrderItemEntity> orderItemEntities = orderItemDAO.loadAll();
        ArrayList<OrderItemDto> orderItemDtos = new ArrayList<>();
        for (OrderItemEntity orderItemEntity : orderItemEntities) {
            orderItemDtos.add(new OrderItemDto(
                    orderItemEntity.getOrder_item_id(),
                    orderItemEntity.getOrder_id(),
                    orderItemEntity.getInventory_id(),
                    orderItemEntity.getQuantity(),
                    orderItemEntity.getAmount()
            ));
        }
        return orderItemDtos;
    }

    @Override
    public boolean save(OrderItemDto dto) throws Exception {
        return orderItemDAO.save(new OrderItemEntity(
                dto.getOrder_item_id(),
                dto.getOrder_id(),
                dto.getInventory_id(),
                dto.getQuantity(),
                dto.getAmount()
        ));
    }

    @Override
    public boolean update(OrderItemDto dto) throws Exception {
        return orderItemDAO.update(new OrderItemEntity(
                dto.getOrder_item_id(),
                dto.getOrder_id(),
                dto.getInventory_id(),
                dto.getQuantity(),
                dto.getAmount()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return orderItemDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return orderItemDAO.exists(id);
    }

    @Override
    public ArrayList<OrderItemDto> search(String text) throws Exception {
        ArrayList<OrderItemEntity> orderItemEntities = orderItemDAO.search(text);
        ArrayList<OrderItemDto> orderItemDtos = new ArrayList<>();
        for (OrderItemEntity orderItemEntity : orderItemEntities) {
            orderItemDtos.add(new OrderItemDto(
                    orderItemEntity.getOrder_item_id(),
                    orderItemEntity.getOrder_id(),
                    orderItemEntity.getInventory_id(),
                    orderItemEntity.getQuantity(),
                    orderItemEntity.getAmount()
            ));
        }
        return orderItemDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return orderItemDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllOrderIds() throws SQLException, ClassNotFoundException {
        return orderItemDAO.getAllOrderIds();
    }

    @Override
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException {
        return orderItemDAO.getAllInventoryIds();
    }
}

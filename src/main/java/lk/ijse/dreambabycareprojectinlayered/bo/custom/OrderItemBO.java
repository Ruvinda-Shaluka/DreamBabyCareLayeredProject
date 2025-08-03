package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.OrderItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderItemBO extends SuperBO {
    public ArrayList<OrderItemDto> loadAll() throws Exception;
    public boolean save(OrderItemDto dto) throws Exception;
    public boolean update(OrderItemDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<OrderItemDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
    public ArrayList<String> getAllOrderIds() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException;

}

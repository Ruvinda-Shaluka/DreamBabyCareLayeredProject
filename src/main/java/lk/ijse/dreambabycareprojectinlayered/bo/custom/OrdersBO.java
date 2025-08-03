package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.OrdersDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersBO extends SuperBO {
    public ArrayList<OrdersDto> loadAll() throws Exception;
    public boolean save(OrdersDto dto) throws Exception;
    public boolean update(OrdersDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<OrdersDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllShipmentIds() throws SQLException, ClassNotFoundException;
}

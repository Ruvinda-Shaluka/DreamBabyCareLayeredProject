package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    public ArrayList<PaymentDto> loadAll() throws Exception;
    public boolean save(PaymentDto dto) throws Exception;
    public boolean update(PaymentDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<PaymentDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
    public ArrayList<String> getAllOrderIds() throws SQLException, ClassNotFoundException;
}

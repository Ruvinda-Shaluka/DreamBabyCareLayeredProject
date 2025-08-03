package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.DiscountDto;

import java.util.ArrayList;

public interface DiscountBO extends SuperBO {
    public ArrayList<DiscountDto> loadAll() throws Exception;
    public boolean save(DiscountDto dto) throws Exception;
    public boolean update(DiscountDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<DiscountDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
    public ArrayList<String> getAllPaymentIds()throws Exception;
}

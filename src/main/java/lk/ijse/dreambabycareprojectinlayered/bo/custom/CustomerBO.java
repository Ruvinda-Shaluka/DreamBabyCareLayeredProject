package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.CustomerDto;

import java.util.ArrayList;


public interface CustomerBO extends SuperBO {

    public ArrayList<CustomerDto> loadAll() throws Exception;
    public boolean save(CustomerDto dto) throws Exception;
    public boolean update(CustomerDto dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<CustomerDto> search(String text) throws Exception;
    public String generateNewId() throws Exception;
    public String getCustomerIdByContact(String contact) throws Exception;
    public ArrayList<String> getCustomerIdByPartialContact(String email) throws Exception;
}

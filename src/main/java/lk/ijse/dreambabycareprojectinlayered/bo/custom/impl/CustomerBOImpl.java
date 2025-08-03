package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.CustomerBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.CustomerDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.CustomerDto;
import lk.ijse.dreambabycareprojectinlayered.entity.CustomerEntity;

import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public ArrayList<CustomerDto> loadAll() throws Exception {
        ArrayList<CustomerEntity> customerEntities = customerDAO.loadAll();
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        for (CustomerEntity entity : customerEntities) {
            customerDtos.add(new CustomerDto(
                    entity.getCustomerId(),
                    entity.getCustomerName(),
                    entity.getCustomerPhone(),
                    entity.getCustomerAddress(),
                    entity.getOrderPlatForm()
            ));
        }
        return customerDtos;
    }

    @Override
    public boolean save(CustomerDto dto) throws Exception {
        return customerDAO.save(new CustomerEntity(
                dto.getCustomerId(),
                dto.getCustomerName(),
                dto.getCustomerPhone(),
                dto.getCustomerAddress(),
                dto.getOrderPlatForm()
        ));
    }

    @Override
    public boolean update(CustomerDto dto) throws Exception {
        return customerDAO.update(new CustomerEntity(
                dto.getCustomerId(),
                dto.getCustomerName(),
                dto.getCustomerPhone(),
                dto.getCustomerAddress(),
                dto.getOrderPlatForm()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return customerDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return customerDAO.exists(id);
    }

    @Override
    public ArrayList<CustomerDto> search(String text) throws Exception {
        ArrayList<CustomerEntity> customerEntities = customerDAO.search(text);
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        for (CustomerEntity entity : customerEntities) {
            customerDtos.add(new CustomerDto(
                    entity.getCustomerId(),
                    entity.getCustomerName(),
                    entity.getCustomerPhone(),
                    entity.getCustomerAddress(),
                    entity.getOrderPlatForm()
            ));
        }
        return customerDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return customerDAO.generateNewId();
    }

    @Override
    public String getCustomerIdByContact(String contact) throws Exception {
        return customerDAO.getCustomerIdByContact(contact);
    }

    @Override
    public ArrayList<String> getCustomerIdByPartialContact(String contact) throws Exception {
        ArrayList<String> customerIds = customerDAO.getCustomerIdByPartialContact(contact);
        if (customerIds == null) {
            return new ArrayList<>();
        }else {
            return customerIds;
        }

    }
}

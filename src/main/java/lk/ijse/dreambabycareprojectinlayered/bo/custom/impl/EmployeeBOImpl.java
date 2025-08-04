package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.EmployeeBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.EmployeeDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.EmployeeDto;
import lk.ijse.dreambabycareprojectinlayered.entity.EmployeeEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public ArrayList<EmployeeDto> loadAll() throws Exception {
        ArrayList<EmployeeEntity> employeeEntities = employeeDAO.loadAll();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntities) {
            employeeDtos.add(new EmployeeDto(
                    employeeEntity.getEmployee_id(),
                    employeeEntity.getName(),
                    employeeEntity.getNic(),
                    employeeEntity.getNumber(),
                    employeeEntity.getRole()
                    ));
        }
        return employeeDtos;
    }

    @Override
    public boolean save(EmployeeDto dto) throws Exception {
        return employeeDAO.save(new EmployeeEntity(
                dto.getEmployee_id(),
                dto.getName(),
                dto.getNic(),
                dto.getNumber(),
                dto.getRole()
        ));
    }

    @Override
    public boolean update(EmployeeDto dto) throws Exception {
        return employeeDAO.update(new EmployeeEntity(
                dto.getEmployee_id(),
                dto.getName(),
                dto.getNic(),
                dto.getNumber(),
                dto.getRole()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return employeeDAO.exists(id);
    }

    @Override
    public ArrayList<EmployeeDto> search(String text) throws Exception {
        ArrayList<EmployeeEntity> employeeEntities = employeeDAO.search(text);
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntities) {
            employeeDtos.add(new EmployeeDto(
                    employeeEntity.getEmployee_id(),
                    employeeEntity.getName(),
                    employeeEntity.getNic(),
                    employeeEntity.getNumber(),
                    employeeEntity.getRole()
            ));
        }
        return employeeDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return employeeDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAllEmployeeIds();
    }

    @Override
    public String getEmployeeNameById(String employeeId) {
        return employeeDAO.getEmployeeNameById(employeeId);
    }

    @Override
    public String getEmployeeRoleById(String employeeId) {
        return employeeDAO.getEmployeeRoleById(employeeId);
    }
}

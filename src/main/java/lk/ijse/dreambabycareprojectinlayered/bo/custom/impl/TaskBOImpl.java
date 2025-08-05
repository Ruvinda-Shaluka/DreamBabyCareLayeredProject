package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.TaskBO;
import lk.ijse.dreambabycareprojectinlayered.dao.DAOFactory;
import lk.ijse.dreambabycareprojectinlayered.dao.custom.TaskDAO;
import lk.ijse.dreambabycareprojectinlayered.dto.TaskDto;
import lk.ijse.dreambabycareprojectinlayered.entity.TaskEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskBOImpl implements TaskBO {
    TaskDAO taskDAO = (TaskDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.TASK);
    @Override
    public ArrayList<TaskDto> loadAll() throws Exception {
        ArrayList<TaskEntity> taskEntities = taskDAO.loadAll();
        ArrayList<TaskDto> taskDtos = new ArrayList<>();
        for (TaskEntity taskEntity : taskEntities) {
            taskDtos.add(new TaskDto(
                    taskEntity.getTask_id(),
                    taskEntity.getEmployee_id(),
                    taskEntity.getDescription(),
                    taskEntity.getStatus()
            ));
        }
        return taskDtos;
    }

    @Override
    public boolean save(TaskDto dto) throws Exception {
        return taskDAO.save(new TaskEntity(
                dto.getTask_id(),
                dto.getEmployee_id(),
                dto.getDescription(),
                dto.getStatus()
        ));
    }

    @Override
    public boolean update(TaskDto dto) throws Exception {
        return taskDAO.update(new TaskEntity(
                dto.getTask_id(),
                dto.getEmployee_id(),
                dto.getDescription(),
                dto.getStatus()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return taskDAO.delete(id);
    }

    @Override
    public boolean exists(String id) throws Exception {
        return taskDAO.exists(id);
    }

    @Override
    public ArrayList<TaskDto> search(String text) throws Exception {
        ArrayList<TaskEntity> taskEntities = taskDAO.search(text);
        ArrayList<TaskDto> taskDtos = new ArrayList<>();
        for (TaskEntity taskEntity : taskEntities) {
            taskDtos.add(new TaskDto(
                    taskEntity.getTask_id(),
                    taskEntity.getEmployee_id(),
                    taskEntity.getDescription(),
                    taskEntity.getStatus()
            ));
        }
        return taskDtos;
    }

    @Override
    public String generateNewId() throws Exception {
        return taskDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException {
        return taskDAO.getAllEmployeeIds();
    }
}

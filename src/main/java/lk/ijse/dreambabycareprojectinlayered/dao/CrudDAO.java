package lk.ijse.dreambabycareprojectinlayered.dao;

import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    public ArrayList<T> loadAll() throws Exception;
    public boolean save(T dto) throws Exception;
    public boolean update(T dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public boolean exists(String id) throws Exception;
    public ArrayList<T> search(String text) throws Exception;
    public String generateNewId() throws Exception;

}

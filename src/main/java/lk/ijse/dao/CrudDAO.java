package lk.ijse.dao;

import lk.ijse.entity.User;

import java.io.IOException;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO {


    boolean save(T object) throws IOException;

    List<T> getAll() throws IOException;

    boolean delete(T object) throws IOException;

    boolean update(T object) throws IOException;
}

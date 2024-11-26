package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserDAO extends CrudDAO{

    String getCurrentId() throws IOException;


    String getUserRole(String userName) throws IOException;

    boolean checkCredentials(String username, String password) throws IOException;


    List<User> getSelectAll(String useName) throws IOException;

    List<User> getSelectAllID(String userId) throws IOException;
}

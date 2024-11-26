package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
   UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOType.USER);

    @Override
    public boolean save(UserDto userDto) {
      //  System.out.println(userDto.toString());
        try {
            return userDAO.save(new User(userDto.getUser_id(), userDto.getUserName(), userDto.getUserPassword(), userDto.getUserRole(), userDto.getUserPhoneNumber(), userDto.getUserEmailAddress()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCurrentId() {

        try {
            return userDAO.getCurrentId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> userDtos = new ArrayList<>();
        List<User> all = null;
        try {
            all = userDAO.getAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (User user : all){
            userDtos.add(new UserDto(user.getUser_id(),user.getUserName(),user.getUserPassword(),user.getUserRole(),user.getUserPhoneNumber(),user.getUserEmailAddress()));
        }
        return userDtos;
    }

    @Override
    public boolean delete(UserDto userDto) {
        try {
            return userDAO.delete(new User(userDto.getUser_id(), userDto.getUserName(), userDto.getUserPassword(),  userDto.getUserRole(),userDto.getUserPhoneNumber(), userDto.getUserEmailAddress()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(UserDto userDto) {
        try {
            return userDAO.update( new User(userDto.getUser_id(), userDto.getUserName(), userDto.getUserPassword(), userDto.getUserRole(), userDto.getUserPhoneNumber(), userDto.getUserEmailAddress()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getIds() {
        try {
            return userDAO.getAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getUserRole(String username) {
        try {
            return userDAO.getUserRole(username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        try {
            return userDAO.checkCredentials(username,  password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll_detail(String useName) {
        try {
            return userDAO.getSelectAll(useName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> getAll_detailID(String userId) {
        try {
            return userDAO.getSelectAllID(userId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

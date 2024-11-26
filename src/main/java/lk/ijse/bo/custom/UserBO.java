package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;

import java.util.List;

public interface UserBO extends SuperBo {

    boolean save(UserDto userDto);

    String getCurrentId();

    List<UserDto> getAll();

    boolean delete(UserDto userDto);


    boolean update(UserDto userDto);


    List<String> getIds();

    String getUserRole(String username);

    boolean checkCredentials(String username, String password);

    List<User> getAll_detail(String useName);


    List<User> getAll_detailID(String userId);
}

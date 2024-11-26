package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.StudentDto;
import lk.ijse.entity.User;

import java.util.List;

public interface StudentBO extends SuperBo {


    List<User> getUserIds();

    boolean save(StudentDto studentDto);

    List<StudentDto> getAll();

    boolean update(StudentDto studentDto);

    boolean delete(StudentDto studentDto);

    String getCurrentId();

    List<StudentDto> getAllCouseStudent();
}

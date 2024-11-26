package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.StudentDto;
import lk.ijse.entity.Student;
import lk.ijse.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOType.STUDENT);


    @Override
    public List<User> getUserIds() {
        return studentDAO.getid();




    }

    @Override
    public boolean save(StudentDto studentDto) {
        try {
            return studentDAO.save(new Student(studentDto.getStudentId(), studentDto.getStudentFirstName(),studentDto.getStudentLastName(),studentDto.getStudentPhone(),studentDto.getStudentEmail(),studentDto.getUserID()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StudentDto> getAll() {
        List<StudentDto> studentDtos = new ArrayList<>();
        List<Student> all = null;
        try {
            all = studentDAO.getAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Student student : all){
            studentDtos.add(new StudentDto(student.getStudentId(),student.getStudentFirstName(),student.getStudentLastName(),student.getStudentPhone(),student.getStudentEmail(),student.getUser()));
        }
        return studentDtos;
    }

    @Override
    public boolean update(StudentDto studentDto) {
        try {
            return studentDAO.update(new Student(studentDto.getStudentId(), studentDto.getStudentFirstName(),studentDto.getStudentLastName(),studentDto.getStudentPhone(),studentDto.getStudentEmail(),studentDto.getUserID()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(StudentDto studentDto) {
        try {
            return studentDAO.delete(new Student(studentDto.getStudentId(), studentDto.getStudentFirstName(),studentDto.getStudentLastName(),studentDto.getStudentPhone(),studentDto.getStudentEmail(),studentDto.getUserID()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCurrentId() {
        try {
            return studentDAO.getCurrentID();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StudentDto> getAllCouseStudent() {
        List<StudentDto> studentDtos = new ArrayList<>();
        List<StudentDto> all = null;
        try {
            all = studentDAO.getAllRegistrStudent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (StudentDto student : all){
            studentDtos.add(new StudentDto(student.getStudentId(),student.getStudentFirstName()));
        }
        return studentDtos;
    }
}
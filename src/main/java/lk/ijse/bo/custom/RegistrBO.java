package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.RegistrDto;
import lk.ijse.entity.Program;
import lk.ijse.entity.Registration;
import lk.ijse.entity.Student;

import java.io.IOException;
import java.util.List;

public interface RegistrBO extends SuperBo {


    List<Program> getProgramIds();

    List<RegistrDto> getAll();

    boolean save(RegistrDto registrDto);

    boolean update(RegistrDto registrDto);


    String getCurrentId();

    List<Student> getStudentIds();

    boolean delete(RegistrDto registrDto) throws IOException;

    boolean deleteRegistration(String id) throws IOException;
}

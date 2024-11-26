//package lk.ijse.dto;
//
//import lk.ijse.entity.Program;
//import lk.ijse.entity.Student;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//
//public class RegistrDto {
//    private String registrationId;
//    private double advanced;
//    private LocalDate date;
//    private Program courses;
//    private Student student;
//
//    public RegistrDto(String registrationId, Student student, Program courses, double advanced, LocalDate date) {
//        this.registrationId = registrationId;
//        this.student = student;
//        this.courses = courses;
//        this.advanced = advanced;
//        this.date = date;
//
//    }
//
//    public RegistrDto(String registrationId, Student value) {
//        this.registrationId = registrationId;
//        this.student = value;
//    }
//
//}
package lk.ijse.dto;

import lk.ijse.entity.Program;
import lk.ijse.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrDto {
    private String registrationId;
    private double advanced;
    private LocalDate date;
    private Program courses;
    private Student student;

    public RegistrDto(String registrationId, Student student, Program courses, double advanced, LocalDate date) {
        this.registrationId = registrationId;
        this.student = student;
        this.courses = courses;
        this.advanced = advanced;
        this.date = date;
    }

    // Updated constructor to include registrationId
    public RegistrDto(String registrationId, Student student) {
        this.registrationId = registrationId;
        this.student = student;
    }
}

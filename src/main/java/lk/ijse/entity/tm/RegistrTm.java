//package lk.ijse.entity.tm;
//
//import jakarta.persistence.ManyToOne;
//import lk.ijse.entity.Program;
//import lk.ijse.entity.Student;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//
//public class RegistrTm {
//    private String registrationId;
//    private double advanced;
//    private LocalDate date;
//    private Program courses;
//    private Student student;
//
//    public RegistrTm(String registrationId, Student student, Program courses, double advanced, LocalDate date) {
//        this.registrationId = registrationId;
//        this.student = student;
//        this.courses = courses;
//        this.advanced = advanced;
//        this.date = date;
//    }
//}
package lk.ijse.entity.tm;

import jakarta.persistence.ManyToOne;
import lk.ijse.entity.Program;
import lk.ijse.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrTm {
    private String registrationId;
    private double advanced;
    private LocalDate date;
    private Program courses;
    private Student student;

    public RegistrTm(String registrationId, Student student, Program courses, double advanced, LocalDate date) {
        this.registrationId = registrationId;
        this.student = student;
        this.courses = courses;
        this.advanced = advanced;
        this.date = date;
    }
}

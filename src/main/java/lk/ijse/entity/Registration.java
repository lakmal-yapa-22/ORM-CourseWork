//package lk.ijse.entity;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDate;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Entity
//
//public class Registration {
//    @Id
//    private String registrationId;
//
//    private double advanced;
//
//    private LocalDate date;
//
//    @ManyToOne
//    private Program courses;
//
//    @ManyToOne(cascade = CascadeType.ALL) // Cascade all operations
//    private Student student;
//
//    public Registration(String registrationId, Student student, Program courses, double advanced, LocalDate date) {
//        this.registrationId = registrationId;
//        this.student = student;
//        this.courses = courses;
//        this.advanced = advanced;
//        this.date = date;
//    }
//}
package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Registration {
    @Id
    private String registrationId;

    private double advanced;
    private LocalDate date;

    @ManyToOne
    private Program courses;

    @ManyToOne(cascade = CascadeType.ALL) // Cascade all operations
    private Student student;


    public Registration(String registrationId, Student student, Program courses, double advanced, LocalDate date) {
        this.registrationId = registrationId;
        this.student = student;
        this.courses = courses;
        this.advanced = advanced;
        this.date = date;
    }

    @Override
    public String toString() {
        return this.registrationId; //
    }
}

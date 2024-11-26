package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Program {

    @Id
    private String courseId;
    private String courseName;
    @Setter
    private String duration;
    private double courseFee;

    @ManyToOne
    @JoinColumn(name = "courses_courseId", referencedColumnName = "courseId") // Maps to Program's courseId
    private Program courses;


    public Program(String courseId, String courseName, String duration, double courseFee) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.duration = duration;
        this.courseFee = courseFee;
    }
    @Override
    public String toString() {
        return this.courseId.toString(); //
    }


}

package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data

public class ProgramDto {
    private String courseId;
    private String courseName;
    private String duration;
    private double courseFee;

    public ProgramDto(String courseId, String courseName, String duration, double courseFee) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.duration = duration;
        this.courseFee = courseFee;

    }
}

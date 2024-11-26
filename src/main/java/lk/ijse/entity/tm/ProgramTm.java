package lk.ijse.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProgramTm {
    private String courseId;
    private String courseName;

    private String duration;
    private double courseFee;
}

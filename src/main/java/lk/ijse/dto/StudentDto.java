package lk.ijse.dto;

import lk.ijse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {

    private String studentId;
    private String studentFirstName;
    private String studentLastName;

    private String studentPhone;
    private String studentEmail;

    private User userID;


    public StudentDto(String studentId, String studentFirstName) {
        this.studentId = studentId;
        this.studentFirstName = studentFirstName;
    }
}

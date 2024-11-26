package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@NoArgsConstructor
@Data

@Entity
public class Student {
    @Id
    private String studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentPhone;
    private String studentEmail;

    @ManyToOne
    private User user; // Reference to User entity
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL) // Cascade all operations
    private List<Registration> registrations;



    public Student(String studentId, String studentFirstName, String studentLastName,String studentPhone, String studentEmail, User userID) {
        this.studentId = studentId;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentPhone = studentPhone;
        this.studentEmail=studentEmail;
        this.user = userID;


    }


    @Override
    public String toString() {
        return this.studentId; //
    }


}

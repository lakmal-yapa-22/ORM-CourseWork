package lk.ijse.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lk.ijse.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    private String user_id;
    private String userName;
    private String userPassword;
    private String userRole;
    private String userPhoneNumber;
    private String userEmailAddress;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Cascade all operations
    private List<Student> students;

    public User(String userId, String userName, String userPassword, String userRole, String userPhoneNumber, String userEmailAddress) {
        this.user_id = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmailAddress = userEmailAddress;

    }


    @Override
    public String toString() {
        return this.user_id; //
    }

}



package lk.ijse.entity.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserTm {
    private String user_id;
    private String userName;
    private String userPassword;
    private String userRole;
    private String userPhoneNumber;
    private String userEmailAddress;


}

package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data

public class UserDto {
    private String user_id;
    private String userName;
    private String userPassword;
    private String userRole;
    private String userPhoneNumber;
    private String userEmailAddress;

    // Constructor, getters and setters
    public UserDto(String user_id, String userName, String userPassword, String userRole, String userPhoneNumber, String userEmailAddress) {
        this.user_id = user_id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmailAddress = userEmailAddress;
    }

    public UserDto(String text, String text1, String text2) {
        this.user_id = text;
        this.userName = text1;
        this.userPassword = text2;
    }

    public String getUser_id() { return user_id; }
    public String getUserName() { return userName; }
    public String getUserPassword() { return userPassword; }
    public String getUserRole() { return userRole; }
    public String getUserPhoneNumber() { return userPhoneNumber; }
    public String getUserEmailAddress() { return userEmailAddress; }

    public void setUser_id(String user_id) { this.user_id = user_id; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
    public void setUserPhoneNumber(String userPhoneNumber) { this.userPhoneNumber = userPhoneNumber; }
    public void setUserEmailAddress(String userEmailAddress) { this.userEmailAddress = userEmailAddress; }
}





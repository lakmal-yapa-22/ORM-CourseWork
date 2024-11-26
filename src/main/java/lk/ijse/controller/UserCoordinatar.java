package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;


public class UserCoordinatar {
    UserBO userBo = (UserBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.USER);


    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtPasswordNew;

    @FXML
    private TextField txtUseName;

    @FXML
    private AnchorPane userPane;
    private AnchorPane centerNode2;

//    @FXML
//    void UpdateOnAction(ActionEvent event) {
//
//        String password = txtPassword.getText();
//        String passwordNew = txtPasswordNew.getText();
//        String userId= txtUseName.getText();
//
//        boolean isValid = userBo.checkCredentials(userId, password);
//
//        if (isValid) {
//            List<User> alluser = userBo.getAll_detailID(userId);
//            for (User user : alluser) {
//                boolean isUpdated = userBo.update(new UserDto(user.getUser_id(), user.getUserName(),passwordNew,user.getUserRole(), user.getUserPhoneNumber(),  user.getUserEmailAddress()));
//                if (isUpdated){
//                    clear_all();
//                    new Alert(Alert.AlertType.INFORMATION, "Successful update! Your new password is: " + passwordNew).show();
//
//                }
//                else {
//                    clear_all();
//                    new Alert(Alert.AlertType.ERROR, "un-successful your password, try Again").show();
//
//                }
//
//            }
// }
//        else {
//            clear_all();
//            new Alert(Alert.AlertType.ERROR, "Invalid credentials!").show();
//        }
//
//
//
//
//
//
//    }
@FXML
void UpdateOnAction(ActionEvent event) {
    try {
        String password = txtPassword.getText();
        String passwordNew = txtPasswordNew.getText();
        String userId = txtUseName.getText();

        // Regex pattern for password validation
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        // Validate new password format
        if (!passwordNew.matches(passwordPattern)) {
            new Alert(Alert.AlertType.WARNING,
                    "Password must have at least:\n- One uppercase letter\n- One lowercase letter\n- One digit\n- One special character\n- Minimum 8 characters in length").show();
            return;
        }

        // Check user credentials
        boolean isValid = userBo.checkCredentials(userId, password);

        if (isValid) {
            // Retrieve all user details for the provided ID
            List<User> allUser = userBo.getAll_detailID(userId);
            for (User user : allUser) {
                // Update the user with the new password
                boolean isUpdated = userBo.update(new UserDto(
                        user.getUser_id(),
                        user.getUserName(),
                        passwordNew, // Store encrypted password if encryption is added
                        user.getUserRole(),
                        user.getUserPhoneNumber(),
                        user.getUserEmailAddress()
                ));

                if (isUpdated) {
                    clear_all();
                    new Alert(Alert.AlertType.INFORMATION,
                            "Successful update! Your new password is: " + passwordNew).show();
                } else {
                    clear_all();
                    new Alert(Alert.AlertType.ERROR,
                            "Password update unsuccessful. Please try again.").show();
                }
            }
        } else {
            clear_all();
            new Alert(Alert.AlertType.ERROR, "Invalid credentials!").show();
        }
    } catch (Exception e) {
        // Log the exception (optional)
        e.printStackTrace();

        // Show an error alert for unexpected exceptions
        new Alert(Alert.AlertType.ERROR,
                "An error occurred during the update process: " + e.getMessage()).show();
    }
}





    private void clear_all() {
        txtPassword.setText("");
        txtPasswordNew.setText("");
        txtUseName.setText("");
    }

    public void setCenterNode(AnchorPane centerNode2) {
        this.centerNode2=centerNode2;
    }
}

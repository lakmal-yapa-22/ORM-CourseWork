package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;

import java.io.IOException;

public class SignUpOnAction {
    @FXML
    private Text text_header;


    @FXML
    private Label tittle;
    @FXML
    private ComboBox<String> cmbUserType;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword1;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserID;



    UserBO userBO = (UserBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.USER);

    @FXML
    void btnlogin__onClick(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    public void initialize() {
        generateUserId();
        setUsers();
    }

    private String generateUserId() {
        try {
            String currentId = userBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("U00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "U00" + ++idNum;
                txtUserID.setText(availableId);
                return availableId;
            } else {
                txtUserID.setText("U001");
                return "U001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setUsers() {
        cmbUserType.getItems().addAll("Admin", "Admission coordinator");
    }

    public void singn_uP_onAction(ActionEvent actionEvent) {
        String userId = txtUserID.getText().trim();
        String userType = cmbUserType.getValue();
        String userName = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String password2 = txtPassword1.getText().trim();
        String email = txtEmail.getText().trim();
        String mobile = txtPhoneNumber.getText().trim();

        UserDto userDTO = new UserDto(userId, userName, password, userType, mobile, email);

        // Check for empty fields
        if (userId.isEmpty() || userType == null || userType.isEmpty() || userName.isEmpty() || password.isEmpty() || password2.isEmpty() || email.isEmpty() || mobile.isEmpty()) {
            new Alert(Alert.AlertType.INFORMATION, "Please fill in all fields!").show();
            return;
        }

        // Check if passwords match
        if (!password.equals(password2)) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
            return;
        }

        // Validate password format
        if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            new Alert(Alert.AlertType.ERROR, "Password must be at least 8 characters long, include an uppercase letter, a lowercase letter, a digit, and a special character.").show();
            return;
        }

        // Validate email format
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid email format!").show();
            return;
        }

        // Validate mobile number format (10 digits)
        if (!mobile.matches("^[0-9]{10}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid mobile number! Must be 10 digits.").show();
            return;
        }

        // Attempt to save user
        boolean isSaved = userBO.save(userDTO);
        if (isSaved) {
            clear_signUp();
            new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save user. Please try again.").show();
        }
    }


    private void clear_signUp() {
        txtUsername.clear();
        txtPassword.clear();
        txtPassword1.clear();
        txtEmail.clear();
        txtPhoneNumber.clear();
        txtUserID.clear();
        cmbUserType.getSelectionModel().clearSelection();

    }
}

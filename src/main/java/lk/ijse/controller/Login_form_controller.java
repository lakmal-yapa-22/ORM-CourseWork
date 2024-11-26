package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;

import java.io.IOException;

public class Login_form_controller {

    @FXML
    private AnchorPane root;
    @FXML
    private Button btn;

    @FXML
    private Label tittle;

    @FXML
    private Text txtForgotPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;
    UserBO userBo = (UserBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.USER);

    @FXML
    void login_btn_onClick(ActionEvent event) throws IOException {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter both userID and password.").show();
            return;
        }

        String userType = userBo.getUserRole(username);
        boolean isValid = userBo.checkCredentials(username, password);

        if (isValid) {
            if (userType.equalsIgnoreCase("Admin")) {
                AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form_admin.fxml"));
                Scene scene = new Scene(rootNode);
                Stage stage = (Stage) this.root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Admin Dashboard");
                stage.centerOnScreen();
            } else {
                AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard.fxml"));
                Scene scene = new Scene(rootNode);
                Stage stage = (Stage) this.root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("User Dashboard");
                stage.centerOnScreen();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid userID or password. Please try again.").show();
        }
    }

    public void btnSignup__onClick(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/signUp.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage =(Stage) this.root.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("sign Up Form");
        stage.centerOnScreen();

    }
}





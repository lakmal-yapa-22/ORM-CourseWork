package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dashbord_02Controller {

    @FXML
    private Button btn;

    @FXML
    private Button btn1;

    @FXML
    private AnchorPane centerNode2;

    @FXML
    private AnchorPane rootNode2;
    @FXML
    private DatePicker datePicker;



    @FXML
    void dashboard_btn_onclick(ActionEvent event) throws IOException {
        AnchorPane rootNode2 = FXMLLoader.load(this.getClass().getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(rootNode2);
        Stage stage =(Stage) this.rootNode2.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();


    }
    public void initialize() {
//        // Set the default date to today
//        datePicker.setValue(java.time.LocalDate.now());
//
//        // Example: Handle date selection
//        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println("Selected date: " + newValue);
//        });
//       lordCurrentTime();


        // Set the default date to today
        datePicker.setValue(LocalDate.now());

        // Example: Handle date selection
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {

        });


    }



    @FXML
    void logout_btn_onclick(ActionEvent event) throws IOException {
        AnchorPane rootNode2 = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode2);
        Stage stage =(Stage) this.rootNode2.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("LogOut Form");
        stage.centerOnScreen();

    }

    @FXML
    void password_btn_onClick(ActionEvent event) {

    }

    @FXML
    void program_btn_onclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/program.fxml"));

        try {
            AnchorPane programPane = loader.load();
            Program_controller controller = loader.getController();
            controller.setCenterNode(centerNode2);
            centerNode2.getChildren().clear();
            centerNode2.getChildren().add(programPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registr_btn_onclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/registr.fxml"));

        try {
            AnchorPane registrPane = loader.load();
            RegistrController controller = loader.getController();
            controller.setCenterNode(centerNode2);
            centerNode2.getChildren().clear();
            centerNode2.getChildren().add(registrPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void student_btn_onclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/student_form.fxml"));

        try {
            AnchorPane studentPane = loader.load();
            Student_form controller = loader.getController();
            controller.setCenterNode(centerNode2);
            centerNode2.getChildren().clear();
            centerNode2.getChildren().add(studentPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void userroles_btn_onClick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user_change_password.fxml"));

        try {
            AnchorPane userPane = loader.load();
           UserCoordinatar controller = loader.getController();
            controller.setCenterNode(centerNode2);
            centerNode2.getChildren().clear();
            centerNode2.getChildren().add(userPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Payment_btn_onClick(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/payment.fxml"));

        try {
            AnchorPane paymentPane = loader.load();
            PaymentFormController controller = loader.getController();
            controller.setCenterNode(centerNode2);
            centerNode2.getChildren().clear();
            centerNode2.getChildren().add(paymentPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

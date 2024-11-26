package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dashboard_admin_form_controller {

    @FXML
    private AnchorPane centerNode;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Text txtTime;

    @FXML
    private Button btn;

    @FXML
    private Button btn1;
    @FXML
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
            txtTime.setText("Selected Date: " + newValue); // Display the selected date
        });

        lordCurrentTime();
    }

    private void lordCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime now = LocalDateTime.now();
            txtTime.setText(dtf.format(now)); // Update label with current date and time
        }));

        clock.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        clock.play(); // Start the clock
    }


    @FXML
    void dashboard_btn_onclick(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form_admin.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage =(Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();


    }

    @FXML
    void logout_btn_onclick(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage =(Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("LogOut Form");
        stage.centerOnScreen();

    }



    @FXML
    void program_btn_onclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/program.fxml"));

        try {
            AnchorPane programPane = loader.load();
            Program_controller controller = loader.getController();
            controller.setCenterNode(centerNode);
            centerNode.getChildren().clear();
            centerNode.getChildren().add(programPane);
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
            controller.setCenterNode(centerNode);
            centerNode.getChildren().clear();
            centerNode.getChildren().add(registrPane);
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
            controller.setCenterNode(centerNode);
            centerNode.getChildren().clear();
            centerNode.getChildren().add(studentPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void userroles_btn_onClick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/userForm.fxml"));

        try {
            AnchorPane userPane = loader.load();
            UserF0rmController controller = loader.getController();
            controller.setCenterNode(centerNode);
            centerNode.getChildren().clear();
            centerNode.getChildren().add(userPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Payment_btn_onClick(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/payment.fxml"));

        try {
            AnchorPane paymentPane = loader.load();
           PaymentFormController controller = loader.getController();
            controller.setCenterNode(centerNode);
            centerNode.getChildren().clear();
            centerNode.getChildren().add(paymentPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

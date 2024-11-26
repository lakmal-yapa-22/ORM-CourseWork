package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;


import javafx.scene.control.Alert;
import lk.ijse.entity.tm.UserTm;

import java.util.List;

public class UserF0rmController {
    UserBO userBo = (UserBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.USER);


    @FXML
    private Button clear;

    @FXML
    private TableColumn<?, ?> col_Email_Address;

    @FXML
    private TableColumn<?, ?> col_ContactNumber;

    @FXML
    private TableColumn<?, ?> col_Coordinator_ID;

    @FXML
    private TableColumn<?, ?> col_userRole;

    @FXML
    private TableColumn<?, ?> col_Name;

    @FXML
    private TableColumn<?, ?> col_Password;

    @FXML
    private Button delete;

    @FXML
    private Button save;

    @FXML
    private Button search;

    @FXML
    private TableView<UserTm> tblCoordinator;

    @FXML
    private Button update;


    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtContact;



    @FXML
    private TextField txtUserID;

    @FXML
    private TextField txtUser_name;

    @FXML
    private TextField txt_password;

    @FXML
    private AnchorPane userPane;
    private AnchorPane centerNode;

    String userType="Admission coordinator";

    @FXML
    void btn_Delete_onClick(ActionEvent event) {
        try {
            if (txtUserID.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please select a user to delete.").show();
                return;
            }

            // Confirm before deleting the user
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to delete this user?",
                    ButtonType.YES, ButtonType.NO);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText(null);

            ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.NO);
            if (result == ButtonType.YES) {
                boolean isDeleted = userBo.delete(new UserDto(
                        txtUserID.getText(),
                        txtUser_name.getText(),
                        txt_password.getText(),
                        txtContact.getText(),
                        txtEmail.getText(),
                        userType
                ));
                if (isDeleted) {
                    setTable();
                    tblCoordinator.refresh();
                    ClearTextField();
                    new Alert(Alert.AlertType.CONFIRMATION, "User deleted successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "User deletion unsuccessful").show();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while deleting the user: " + e.getMessage()).show();
            e.printStackTrace(); // Log the exception for debugging purposes
        }
    }

//    @FXML
//    void btn_Save_onClick(ActionEvent event) {
//
//
//
//        boolean isSaved = userBo.save(new UserDto(txtUserID.getText(), txtUser_name.getText(), txt_password.getText(), userType, txtContact.getText(), txtEmail.getText()));
//        if (isSaved) {
//            setTable();
//            tblCoordinator.refresh();
//            ClearTextField();
//            new Alert(Alert.AlertType.CONFIRMATION, "user save successfully").show();
//            generateUserId();
//
//        } else {
//
//            new Alert(Alert.AlertType.ERROR, "user save unsuccessfully").show();
//        }
//
//    }



//    @FXML
//    void btn_Update_onClick(ActionEvent event) {
//
//        boolean isUpdated = userBo.update(new UserDto(txtUserID.getText(), txtUser_name.getText(),txt_password.getText(),userType, txtContact.getText(),  txtEmail.getText()));
//        if (isUpdated){
//ClearTextField();
//setTable();
//            setCellValueFactory();
//           tblCoordinator.refresh();
//            txtUserID.setText(generateUserId());
//            new Alert(Alert.AlertType.CONFIRMATION,"user update successfully").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR,"user update unsuccessfully").show();
//        }
//    }


    @FXML
    void btn_Save_onClick(ActionEvent event) {
        try {
            if (validateInputs()) {
                boolean isSaved = userBo.save(new UserDto(
                        txtUserID.getText(),
                        txtUser_name.getText(),
                        txt_password.getText(),
                        userType,
                        txtContact.getText(),
                        txtEmail.getText()
                ));
                if (isSaved) {
                    setTable();
                    tblCoordinator.refresh();
                    ClearTextField();
                    new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully").show();
                    generateUserId();
                } else {
                    new Alert(Alert.AlertType.ERROR, "User save unsuccessful").show();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while saving the user: " + e.getMessage()).show();
            e.printStackTrace(); // Log the exception for debugging purposes
        }
    }

    @FXML
    void btn_Update_onClick(ActionEvent event) {
        try {
            if (validateInputs()) {
                boolean isUpdated = userBo.update(new UserDto(
                        txtUserID.getText(),
                        txtUser_name.getText(),
                        txt_password.getText(),
                        userType,
                        txtContact.getText(),
                        txtEmail.getText()
                ));
                if (isUpdated) {
                    ClearTextField();
                    setTable();
                    setCellValueFactory();
                    tblCoordinator.refresh();
                    txtUserID.setText(generateUserId());
                    new Alert(Alert.AlertType.CONFIRMATION, "User updated successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "User update unsuccessful").show();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while updating the user: " + e.getMessage()).show();
            e.printStackTrace(); // Log the exception for debugging purposes
        }
    }

    private boolean validateInputs() {
        boolean isValid = true;
        StringBuilder errorMessages = new StringBuilder("Please correct the following errors:\n");

        // Email validation
        if (!txtEmail.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            txtEmail.setStyle("-fx-border-color: red;");
            isValid = false;
            errorMessages.append("- Invalid Email Address\n");
        } else {
            txtEmail.setStyle(null);
        }

        // Contact number validation
        if (!txtContact.getText().matches("^\\d{10}$")) {
            txtContact.setStyle("-fx-border-color: red;");
            isValid = false;
            errorMessages.append("- Invalid Contact Number (should be 10 digits)\n");
        } else {
            txtContact.setStyle(null);
        }

        // User name validation
        if (!txtUser_name.getText().matches("^[a-zA-Z\\s]{3,30}$")) {
            txtUser_name.setStyle("-fx-border-color: red;");
            isValid = false;
            errorMessages.append("- Invalid User Name (should be 3-30 alphabetic characters)\n");
        } else {
            txtUser_name.setStyle(null);
        }

        // Password validation
        if (!txt_password.getText().matches("^[a-zA-Z0-9@#$%^&+=]{8,}$")) {
            txt_password.setStyle("-fx-border-color: red;");
            isValid = false;
            errorMessages.append("- Invalid Password (minimum 8 characters, include special characters)\n");
        } else {
            txt_password.setStyle(null);
        }

        // Show alert if there are validation errors
        if (!isValid) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Errors");
            alert.setHeaderText(null);
            alert.setContentText(errorMessages.toString());
            alert.showAndWait();
        }

        return isValid;
    }



    public void setCenterNode(AnchorPane centerNode) {
        this.centerNode = centerNode;
    }


    void ClearTextField() {
        txtEmail.clear();
        txtContact.clear();

        txtUser_name.clear();
        txtUserID.clear();
        txt_password.clear();
        generateUserId();

    }

    public void btn_clear_onClick(ActionEvent actionEvent) {
        ClearTextField();
    }

    public void initialize() {
        setTable();
        generateUserId();
        setCellValueFactory();
        selectTableRow();

    }

    private void setTable() {
        ObservableList<UserTm> userTms = FXCollections.observableArrayList();
        List<UserDto> all = userBo.getAll();
        for (UserDto userDto : all) {
            UserTm userTm = new UserTm(userDto.getUser_id(), userDto.getUserName(), userDto.getUserPassword(),  userDto.getUserRole(), userDto.getUserPhoneNumber(),userDto.getUserEmailAddress());
            userTms.add(userTm);
        }
        tblCoordinator.setItems(userTms);
    }

    private void selectTableRow() {
        tblCoordinator.setOnMouseClicked(event -> {
            // Ensure a row is actually selected
            if (!tblCoordinator.getSelectionModel().isEmpty()) {
                int focusedIndex = tblCoordinator.getSelectionModel().getSelectedIndex();
                UserTm userTm = (UserTm) tblCoordinator.getItems().get(focusedIndex);

                // Populate the text fields with values from the selected UserTm
                txtUserID.setText(userTm.getUser_id());
                txtUser_name.setText(userTm.getUserName());
                txt_password.setText(userTm.getUserPassword());

                txtContact.setText(String.valueOf(userTm.getUserPhoneNumber()));
                txtEmail.setText(userTm.getUserEmailAddress());
            }
        });
    }


    private void setCellValueFactory() {
        col_Coordinator_ID.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        col_Name.setCellValueFactory(new PropertyValueFactory<>("userName"));
        col_Password.setCellValueFactory(new PropertyValueFactory<>("userPassword"));

        col_userRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));
        col_ContactNumber.setCellValueFactory(new PropertyValueFactory<>("userPhoneNumber"));
       col_Email_Address.setCellValueFactory(new PropertyValueFactory<>("userEmailAddress"));





    }

    private String generateUserId() {
        try {
            String currentId = userBo.getCurrentId();
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





    @FXML
    void txtEmailAddress_onAction(ActionEvent event) {
txtEmail.requestFocus();
    }

    @FXML
    void txtContact_onAction(ActionEvent event) {
        txtContact.requestFocus();

    }


    @FXML
    void txtUserID_onAction(ActionEvent event) {
        txtUserID.requestFocus();

    }

    @FXML
    void txtUser_name_onAction(ActionEvent event) {
        txtUser_name.requestFocus();

    }

    @FXML
    void txt_password_onAction(ActionEvent event) {
        txt_password.requestFocus();


    }

}
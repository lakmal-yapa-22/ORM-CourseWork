package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RegistrBO;
import lk.ijse.dto.RegistrDto;
import lk.ijse.dto.StudentDto;
import lk.ijse.entity.Program;
import lk.ijse.entity.Registration;
import lk.ijse.entity.Student;
import lk.ijse.entity.User;
import lk.ijse.entity.tm.RegistrTm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;



public class RegistrController {

    @FXML
    private ComboBox<Program> cmb_program_id;

    @FXML
    private ComboBox<Student> cmb_student_id;

    @FXML
    private TableColumn<?, ?> col_program_id;

    @FXML
    private TableColumn<?, ?> col_registr_date;

    @FXML
    private TableColumn<?, ?> col_registr_fee;

    @FXML
    private TableColumn<?, ?> col_registr_id;

    @FXML
    private TableColumn<?, ?> col_student_id;

    @FXML
    private AnchorPane registrPane;

    @FXML
    private TableView<RegistrTm> tbl_registr;

    @FXML
    private DatePicker txt_reg_date;

    @FXML
    private TextField txt_registr_fee;

    @FXML
    private TextField txt_registr_id;
    private AnchorPane centerNode;
  RegistrBO registrBO = (RegistrBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.REGISTR);

    @FXML
    void btn_clear_onAction(ActionEvent event) {
       clear();


    }
    public void clear(){
        txt_reg_date.setValue(null);
        txt_registr_fee.setText(null);
        txt_registr_id.setText(null);
        cmb_program_id.setValue(null);
        cmb_student_id.setValue(null);
        generateRedistrId();
    }


    @FXML
    void btn_delete_onAction(ActionEvent event) throws IOException {
        String id = txt_registr_id.getText();



        if (id.isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Missing Registration ID");
            errorAlert.setContentText("Please enter a registration ID to delete.");
            errorAlert.showAndWait();
            return;
        }

        boolean success = registrBO.deleteRegistration(id);

        if (success) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText("Deletion Successful");
            successAlert.setContentText("Registration with ID: " + id + " has been successfully deleted.");
            successAlert.showAndWait();

            clear(); // Clear input fields
            generateRedistrId(); // Generate a new registration ID
            setItemTable(); // Refresh table data
            setCellValueFactory(); // Set cell value factory
            tbl_registr.refresh(); // Refresh table view

        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Deletion Failed");
            errorAlert.setContentText("Failed to delete the registration. Please check the ID and try again.");
            errorAlert.showAndWait();
        }
    }





//    @FXML
//    void btn_save_onAction(ActionEvent event) {
//       Student selectStudent = this.cmb_student_id.getSelectionModel().getSelectedItem();
//        boolean isSaved = registrBO.save(new RegistrDto(txt_registr_id.getText(),selectStudent,cmb_program_id.getValue(),Double.parseDouble(txt_registr_fee.getText()), txt_reg_date.getValue()));
//        if (isSaved) {
//           clear();;
//generateRedistrId();
//            setItemTable();
//            setCellValueFactory();
//            tbl_registr.refresh();
//            generateRedistrId();
//            new Alert(Alert.AlertType.CONFIRMATION, "Item saved successfully").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Item saved unsuccessfully");
//        }
//    }

    @FXML
    void btn_save_onAction(ActionEvent event) {
        // Validate input fields
        if (txt_registr_id.getText().isEmpty() ||
                cmb_student_id.getValue() == null ||
                cmb_program_id.getValue() == null ||
                txt_registr_fee.getText().isEmpty() ||
                txt_reg_date.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "All fields are required!").show();
            return;
        }

        // Validate registration fee as a positive number
        try {
            double fee = Double.parseDouble(txt_registr_fee.getText());
            if (fee <= 0) {
                new Alert(Alert.AlertType.ERROR, "Registration fee must be a positive number!").show();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid registration fee! Enter a valid number.").show();
            return;
        }

        // Validate registration date (not a future date)
        if (txt_reg_date.getValue().isAfter(java.time.LocalDate.now())) {
            new Alert(Alert.AlertType.ERROR, "Registration date cannot be in the future.").show();
            return;
        }

        // Perform save operation
        Student selectStudent = this.cmb_student_id.getSelectionModel().getSelectedItem();
        boolean isSaved = registrBO.save(new RegistrDto(
                txt_registr_id.getText(),
                selectStudent,
                cmb_program_id.getValue(),
                Double.parseDouble(txt_registr_fee.getText()),
                txt_reg_date.getValue()
        ));

        if (isSaved) {
            clear(); // Clear input fields
            generateRedistrId(); // Generate a new registration ID
            setItemTable(); // Refresh table data
            setCellValueFactory(); // Set cell value factory
            tbl_registr.refresh(); // Refresh table view
            new Alert(Alert.AlertType.CONFIRMATION, "Item saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Item saved unsuccessfully").show();
        }
    }

//
//    @FXML
//    void btn_update_onAction(ActionEvent event) {
//        boolean isUpdated =  registrBO.update(new RegistrDto(txt_registr_id.getText(),cmb_student_id.getValue(),cmb_program_id.getValue(),Double.parseDouble(txt_registr_fee.getText()), txt_reg_date.getValue()));
//        if (isUpdated) {
//           clear();;
//            setItemTable();
//            setCellValueFactory();
//           tbl_registr.refresh();
//           generateRedistrId();
//            new Alert(Alert.AlertType.CONFIRMATION, "Item updated successfully").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Item updated unsuccessfully");
//        }
//    }
@FXML
void btn_update_onAction(ActionEvent event) {
    // Validate input fields
    if (txt_registr_id.getText().isEmpty() ||
            cmb_student_id.getValue() == null ||
            cmb_program_id.getValue() == null ||
            txt_registr_fee.getText().isEmpty() ||
            txt_reg_date.getValue() == null) {
        new Alert(Alert.AlertType.ERROR, "All fields are required!").show();
        return;
    }

    // Validate registration fee as a positive number
    try {
        double fee = Double.parseDouble(txt_registr_fee.getText());
        if (fee <= 0) {
            new Alert(Alert.AlertType.ERROR, "Registration fee must be a positive number!").show();
            return;
        }
    } catch (NumberFormatException e) {
        new Alert(Alert.AlertType.ERROR, "Invalid registration fee! Enter a valid number.").show();
        return;
    }

    // Validate registration date (not a future date)
    if (txt_reg_date.getValue().isAfter(java.time.LocalDate.now())) {
        new Alert(Alert.AlertType.ERROR, "Registration date cannot be in the future.").show();
        return;
    }

    // Perform update operation
    boolean isUpdated = registrBO.update(new RegistrDto(
            txt_registr_id.getText(),
            cmb_student_id.getValue(),
            cmb_program_id.getValue(),
            Double.parseDouble(txt_registr_fee.getText()),
            txt_reg_date.getValue()
    ));

    if (isUpdated) {
        clear(); // Clear the input fields
        setItemTable(); // Refresh the table
        setCellValueFactory(); // Set cell value factory
        tbl_registr.refresh(); // Refresh the table view
        generateRedistrId(); // Generate a new registration ID
        new Alert(Alert.AlertType.CONFIRMATION, "Item updated successfully").show();
    } else {
        new Alert(Alert.AlertType.ERROR, "Item update failed.").show();
    }
}


    public void setCenterNode(AnchorPane centerNode) {
        this.centerNode=centerNode;
    }

    public  void initialize() {
     setCombo_student_ID();
        setCombo_program_ID();
        setItemTable();
        setCellValueFactory();
        tableSelection();
        generateRedistrId();

    }

    private void setCombo_student_ID() {

        List<Student> students= registrBO.getStudentIds(); // Adjust this method to return List<User>
        cmb_student_id.getItems().addAll(students);
    }

    private String generateRedistrId() {
        try {
            String currentId = registrBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("R00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "R00" + ++idNum;
                txt_registr_id.setText(availableId);
                return availableId;
            } else {
               txt_registr_id.setText("R001");
                return "R001";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private void tableSelection() {
        tbl_registr.setOnMouseClicked(event -> {
            int index = tbl_registr.getSelectionModel().getSelectedIndex();

            if (index >= 0) { // Check if an item is selected
                txt_registr_id.setText(String.valueOf(col_registr_id.getCellData(index)));
                txt_reg_date.setValue((LocalDate) col_registr_date.getCellData(index));
                txt_registr_fee.setText(String.valueOf(col_registr_fee.getCellData(index)));
                cmb_program_id.setValue((Program) col_program_id.getCellData(index));
                cmb_student_id.setValue((Student) col_student_id.getCellData(index));
            }
        });
    }


    private void setCellValueFactory() {
        col_registr_id.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
       col_program_id.setCellValueFactory(new PropertyValueFactory<>("courses"));
       col_registr_date.setCellValueFactory(new PropertyValueFactory<>("date"));
       col_registr_fee.setCellValueFactory(new PropertyValueFactory<>("advanced"));
       col_student_id.setCellValueFactory(new PropertyValueFactory<>("student"));







    }

    private void setItemTable() {
        ObservableList<RegistrTm> obList = FXCollections.observableArrayList();
        List<RegistrDto> items = registrBO.getAll();
        for (RegistrDto registrDto : items){
           RegistrTm registrTm = new RegistrTm(
                    registrDto.getRegistrationId(),
                    registrDto.getStudent(),
                   registrDto.getCourses(),
                    registrDto.getAdvanced(),
                  registrDto.getDate()
            );
            obList.add(registrTm);
        }
        tbl_registr.setItems(obList);
    }

    private void setCombo_program_ID() {
        List<Program> programs = registrBO.getProgramIds(); // Adjust this method to return List<User>
        cmb_program_id.getItems().addAll(programs);
    }


}

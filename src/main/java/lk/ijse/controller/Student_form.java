package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dto.StudentDto;
import lk.ijse.entity.User;
import lk.ijse.entity.tm.StudentTm;

import java.util.List;

public class Student_form {




    @FXML
    private ComboBox<User> cmb_Codinator_ID;
    @FXML
    private TableColumn<?, ?> col_Coor_Name;

    @FXML
    private TableColumn<?, ?> col_L_name_OnAction;

    @FXML
    private TableColumn<?, ?> col_S_Email;

    @FXML
    private TableColumn<?, ?> col_S_F_name;

    @FXML
    private TableColumn<?, ?> col_S_ID;

    @FXML
    private TableColumn<?, ?> col_s_Contact;

    @FXML
    private AnchorPane studentPane;

    @FXML
    private TextField txt_student_Contact;

    @FXML
    private TextField txt_student_D;

    @FXML
    private TextField txt_student_Email;

    @FXML
    private TextField txt_student_First_name;

    @FXML
    private TextField txt_student_last_name;
    private AnchorPane centerNode;

    @FXML
    private TableView<StudentTm> tblStudent;


    @FXML
    private TableView<StudentTm> tblAllCourses;
    @FXML
    private TableColumn<?, ?> colStudent_ID;

    @FXML
    private TableColumn<?, ?> col_Student_name;
    @FXML
    private Button clear_btn;



    @FXML
    private Button delete_btn;

    @FXML
    private Button save_btn;



    @FXML
    private Label tittle;



    @FXML
    private Button update_btn;


    StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.STUDENT);



    @FXML
    void Clear_on_Action(ActionEvent event) {
        Clear();
        setComboUser();


    }

    @FXML
    void Cmb_Codinator_onAction(ActionEvent event) {
        cmb_Codinator_ID.getValue();
        cmb_Codinator_ID.requestFocus();


    }
    @FXML
    void delete_onAction(ActionEvent event) {
        try {
            User selectedCoordinator = this.cmb_Codinator_ID.getSelectionModel().getSelectedItem();

            boolean isDeleted = studentBO.delete(new StudentDto(
                    txt_student_D.getText(),
                    txt_student_First_name.getText(),
                    txt_student_last_name.getText(),
                    txt_student_Contact.getText(),
                    txt_student_Email.getText(),
                    selectedCoordinator
            ));

            if (isDeleted) {
                // Update UI and refresh the table
                setTable();
                setCellValueFactory();
                tblStudent.refresh();
                setComboUser();
                Clear();
                generateStudentId();

                // Show success alert
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted successfully").show();
            } else {
                // Show error alert if deletion was not successful
                new Alert(Alert.AlertType.ERROR, "Student deletion unsuccessful").show();
            }
        } catch (Exception e) {
            // Log the exception (optional)
            e.printStackTrace();

            // Show an alert with the error message
            new Alert(Alert.AlertType.ERROR, "Error occurred during deletion: " + e.getMessage()).show();
        }
    }

//
//@FXML
//void saveOnAction(ActionEvent event) {
//    if (validateFields()) {
//        User selectedCoordinator = this.cmb_Codinator_ID.getSelectionModel().getSelectedItem();
//        boolean isSaved = studentBO.save(new StudentDto(
//                txt_student_D.getText(),
//                txt_student_First_name.getText(),
//                txt_student_last_name.getText(),
//
//                txt_student_Contact.getText(),
//                txt_student_Email.getText(),
//                selectedCoordinator
//        ));
//        if (isSaved) {
//            setTable();
//            Clear();
//            setComboUser();
//            new Alert(Alert.AlertType.CONFIRMATION, "Student saved successfully").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Student save unsuccessful").show();
//        }
//    }
//}

//    @FXML
//    void updateOnAction(ActionEvent event) {
//        if (validateFields()) {
//            User selectedCoordinator = this.cmb_Codinator_ID.getSelectionModel().getSelectedItem();
//            boolean isUpdated = studentBO.update(new StudentDto(
//                    txt_student_D.getText(),
//                    txt_student_First_name.getText(),
//                    txt_student_last_name.getText(),
//                    txt_student_Email.getText(),
//                    txt_student_Contact.getText(),
//
//
//                    selectedCoordinator
//            ));
//            if (isUpdated) {
//                setTable();
//                setCellValueFactory();
//                tblStudent.refresh();
//                Clear();
//                setComboUser();
//                new Alert(Alert.AlertType.CONFIRMATION, "Student updated successfully").show();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Student update unsuccessful").show();
//            }
//        }
//    }

//    private boolean validateFields() {
//        // Define regular expressions
//        String idRegex = "^S\\d{3}$";
//        String nameRegex = "^[A-Za-z ]+$";
//        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
//        String contactRegex = "^(?:\\+94|0)?[7-9]\\d{8}$"; // Matches 10-digit Sri Lankan mobile numbers
//
//        if (!txt_student_D.getText().matches(idRegex)) {
//            new Alert(Alert.AlertType.ERROR, "Invalid Student ID. Format: S001").show();
//            txt_student_D.requestFocus();
//            return false;
//        }
//        if (!txt_student_First_name.getText().matches(nameRegex)) {
//            new Alert(Alert.AlertType.ERROR, "Invalid First Name. Only alphabetic characters allowed.").show();
//            txt_student_First_name.requestFocus();
//            return false;
//        }
//        if (!txt_student_last_name.getText().matches(nameRegex)) {
//            new Alert(Alert.AlertType.ERROR, "Invalid Last Name. Only alphabetic characters allowed.").show();
//            txt_student_last_name.requestFocus();
//            return false;
//        }
//        if (!txt_student_Email.getText().matches(emailRegex)) {
//            new Alert(Alert.AlertType.ERROR, "Invalid Email. Please enter a valid email address.").show();
//            txt_student_Email.requestFocus();
//            return false;
//        }
//        if (!txt_student_Contact.getText().matches(contactRegex)) {
//            new Alert(Alert.AlertType.ERROR, "Invalid Contact Number. Use a valid 10-digit Sri Lankan phone number.").show();
//            txt_student_Contact.requestFocus();
//            return false;
//        }
//
//        return true;
//    }

    @FXML
    void txt_student_Contact_ONAction(ActionEvent event) {
        txt_student_Contact.requestFocus();

    }

    @FXML
    void txt_student_Email_OnAction(ActionEvent event) {
        txt_student_Email.requestFocus();

    }

    @FXML
    void txt_student_ID_onAction(ActionEvent event) {
        txt_student_D.requestFocus();

    }

    @FXML
    void txt_student_first_name_OnAction(ActionEvent event) {
        txt_student_First_name.requestFocus();

    }

    @FXML
    void txt_student_last_name_OnAction(ActionEvent event) {
        txt_student_last_name.requestFocus();

    }
    public void setCenterNode(AnchorPane centerNode) {
        this.centerNode=centerNode;
    }
    public void initialize() {
        setComboUser();
        setTable();
        setCellValueFactory();
        selectTableRow();
        generateStudentId();
       // setTableRowAllCourses();
        lordAllCourseTable();

        try {
            ObservableList<StudentTm> studentTms = FXCollections.observableArrayList();

            // Fetch all students who have registered for all culinary courses
            List<StudentDto> allStudents = studentBO.getAllCouseStudent();

            for (StudentDto studentDto : allStudents) {
                StudentTm studentTm = new StudentTm(
                        studentDto.getStudentId(),
                        studentDto.getStudentFirstName()
                );

                // Optional: print each studentTm object
                System.out.println("Student: " + studentTm);

                studentTms.add(studentTm);
            }

            // Print the complete list of studentTms once
            System.out.println("All Students: " + studentTms);

            // Set data to the table and bind columns
            tblAllCourses.setItems(studentTms);
            setCellAllCourseValueFactory();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading student data.").show();
            e.printStackTrace();
        }

    }

    private void setTableRowAllCourses() {

    }

    private void lordAllCourseTable() {





    }

    private String generateStudentId() {
        try {
            String currentId = studentBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("S00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "S00" + ++idNum;
                txt_student_D.setText(availableId);
                return availableId;
            } else {
                txt_student_D.setText("S001");
                return "S001";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private void selectTableRow() {
        tblStudent.setOnMouseClicked(event -> {
            int focusedIndex = tblStudent.getFocusModel().getFocusedIndex();
            StudentTm studentTm = tblStudent.getItems().get(focusedIndex);
            txt_student_D.setText(studentTm.getStudentId());
            txt_student_First_name.setText(studentTm.getStudentFirstName());
            txt_student_last_name.setText(studentTm.getStudentLastName());

            txt_student_Contact.setText(studentTm.getStudentPhone());
            txt_student_Email.setText(studentTm.getStudentEmail());

            cmb_Codinator_ID.setValue(studentTm.getUserID());


        });
    }

    private void setCellValueFactory() {
        col_S_ID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        col_S_F_name.setCellValueFactory(new PropertyValueFactory<>("studentFirstName"));
        col_L_name_OnAction.setCellValueFactory(new PropertyValueFactory<>("studentLastName"));
        col_s_Contact.setCellValueFactory(new PropertyValueFactory<>("studentPhone"));
        col_S_Email.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));

        col_Coor_Name.setCellValueFactory(new PropertyValueFactory<>("userID"));


    }

    private void setTable() {
        try {
            ObservableList<StudentTm> studentTms = FXCollections.observableArrayList();
            List<StudentDto> allStudents = studentBO.getAll();

            for (StudentDto studentDto : allStudents) {
                StudentTm studentTm = new StudentTm(
                        studentDto.getStudentId(),
                        studentDto.getStudentFirstName(),
                        studentDto.getStudentLastName(),
                        studentDto.getStudentPhone(),
                        studentDto.getStudentEmail(),


                        studentDto.getUserID() // Ensure this matches the expected field
                );
                studentTms.add(studentTm);
            }

            tblStudent.setItems(studentTms);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading student data.").show();
            e.printStackTrace();
        }
    }


    private void setComboUser() {
        List<User> users = studentBO.getUserIds(); // Adjust this method to return List<User>
        cmb_Codinator_ID.getItems().addAll(users);

    }
//    public  void Clear(){
//        txt_student_D.setText("");
//        txt_student_First_name.setText("");
//        txt_student_last_name.setText("");
//        txt_student_Email.setText("");
//        txt_student_Contact.setText("");
//       cmb_Codinator_ID.getItems().clear();
//    }
    @FXML
    void show_btn_onClick(ActionEvent event) {

    }

    private void setCellAllCourseValueFactory() {
        colStudent_ID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        col_Student_name.setCellValueFactory(new PropertyValueFactory<>("studentFirstName"));
    }


    @FXML
    void saveOnAction(ActionEvent event) {
        try {
            if (validateFields()) {
                User selectedCoordinator = this.cmb_Codinator_ID.getSelectionModel().getSelectedItem();
                boolean isSaved = studentBO.save(new StudentDto(
                        txt_student_D.getText(),
                        txt_student_First_name.getText(),
                        txt_student_last_name.getText(),
                        txt_student_Contact.getText(),
                        txt_student_Email.getText(),
                        selectedCoordinator
                ));
                if (isSaved) {
                    setTable();
                    Clear();
                    setComboUser();
                    new Alert(Alert.AlertType.CONFIRMATION, "Student saved successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Student save unsuccessful").show();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error saving student: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        try {
            if (validateFields()) {
                User selectedCoordinator = this.cmb_Codinator_ID.getSelectionModel().getSelectedItem();
                boolean isUpdated = studentBO.update(new StudentDto(
                        txt_student_D.getText(),
                        txt_student_First_name.getText(),
                        txt_student_last_name.getText(),
                        txt_student_Contact.getText(),
                        txt_student_Email.getText(),

                        selectedCoordinator
                ));
                if (isUpdated) {
                    setTable();
                    setCellValueFactory();
                    tblStudent.refresh();
                    Clear();
                    setComboUser();
                    new Alert(Alert.AlertType.CONFIRMATION, "Student updated successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Student update unsuccessful").show();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error updating student: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private boolean validateFields() {
        // Define regular expressions
        String idRegex = "^S\\d{3}$";
        String nameRegex = "^[A-Za-z ]+$";
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        String contactRegex = "^(?:\\+94|0)?[7-9]\\d{8}$"; // Matches 10-digit Sri Lankan mobile numbers

        boolean valid = true;

        // Validate Student ID
        if (!txt_student_D.getText().matches(idRegex)) {
            valid = false;
            txt_student_D.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Student ID. Format: S001").show();
        } else {
            txt_student_D.setStyle("-fx-border-color: green;");
        }

        // Validate First Name
        if (!txt_student_First_name.getText().matches(nameRegex)) {
            valid = false;
            txt_student_First_name.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid First Name. Only alphabetic characters allowed.").show();
        } else {
            txt_student_First_name.setStyle("-fx-border-color: green;");
        }

        // Validate Last Name
        if (!txt_student_last_name.getText().matches(nameRegex)) {
            valid = false;
            txt_student_last_name.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Last Name. Only alphabetic characters allowed.").show();
        } else {
            txt_student_last_name.setStyle("-fx-border-color: green;");
        }

        // Validate Email
        if (!txt_student_Email.getText().matches(emailRegex)) {
            valid = false;
            txt_student_Email.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Email. Please enter a valid email address.").show();
        } else {
            txt_student_Email.setStyle("-fx-border-color: green;");
        }

        // Validate Contact Number
        if (!txt_student_Contact.getText().matches(contactRegex)) {
            valid = false;
            txt_student_Contact.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Contact Number. Use a valid 10-digit Sri Lankan phone number.").show();
        } else {
            txt_student_Contact.setStyle("-fx-border-color: green;");
        }

        return valid;
    }

    private void highlightError(TextField field, String message) {
        field.setStyle("-fx-border-color: red; -fx-background-color: #f8d7da;"); // Red border and light red background
        new Alert(Alert.AlertType.ERROR, message).show();
    }

    private void resetFieldStyle(TextField field) {
        field.setStyle(""); // Reset the field to default style
    }

    // Method for clearing the form fields
    public void Clear() {
        txt_student_D.setText("");
        txt_student_First_name.setText("");
        txt_student_last_name.setText("");
        txt_student_Email.setText("");
        txt_student_Contact.setText("");
        cmb_Codinator_ID.getItems().clear();
        resetAllFields(); // Reset the styles for all fields
        generateStudentId();
    }

    private void resetAllFields() {
        txt_student_D.setStyle("");
        txt_student_First_name.setStyle("");
        txt_student_last_name.setStyle("");
        txt_student_Email.setStyle("");
        txt_student_Contact.setStyle("");
    }

// You can implement other methods like deleteOnAction similarly...



}

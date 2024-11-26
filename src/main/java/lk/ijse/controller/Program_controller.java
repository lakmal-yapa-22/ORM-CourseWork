package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ProgramBO;
import lk.ijse.dto.ProgramDto;
import lk.ijse.entity.tm.ProgramTm;
import lombok.Setter;

import java.util.List;

public class Program_controller {
    ProgramBO programBO = (ProgramBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.PROGRAM);

    @FXML
    private TableColumn<?, ?> col_program_duration;

    @FXML
    private TableColumn<?, ?> col_program_fee;

    @FXML
    private TableColumn<?, ?> col_program_iD;

    @FXML
    private TableColumn<?, ?> col_program_name;

    @FXML
    private AnchorPane program_root;

    @FXML
    private TableView<ProgramTm> tblProgram;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txt_Program_fee;

    @FXML
    private TextField txt_program_id;

    @FXML
    private ComboBox<String> cmbProgramName;

    private AnchorPane centerNode;

    public void initialize() {
        setTable();
        setCellValueFactory();
        selectTableRow();
        generateProgramID();
        setPrograms();
    }

    private void setPrograms() {
        cmbProgramName.getItems().addAll(
                "Professional Cooking",
                "Baking & Pastry Arts",
                "International Cuisine",
                "Culinary Management",
                "Food Safety and Hygiene"
        );
    }

    private String generateProgramID() {
        try {
            String currentId = programBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("CA");
                int idNum = Integer.parseInt(split[1]);
                String availableId = String.format("CA%04d", ++idNum);
                txt_program_id.setText(availableId);
                return availableId;
            } else {
                txt_program_id.setText("CA1001");
                return "CA1001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void selectTableRow() {
        tblProgram.setOnMouseClicked(event -> {
            int focusedIndex = tblProgram.getFocusModel().getFocusedIndex();
            ProgramTm programTm = tblProgram.getItems().get(focusedIndex);
            txt_program_id.setText(programTm.getCourseId());
            cmbProgramName.setValue(programTm.getCourseName());
            txtDuration.setText(programTm.getDuration());
            txt_Program_fee.setText(String.valueOf(programTm.getCourseFee()));
        });
    }

    private void setCellValueFactory() {
        col_program_iD.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        col_program_name.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        col_program_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        col_program_fee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
    }

    private void setTable() {
        ObservableList<ProgramTm> programTms = FXCollections.observableArrayList();
        List<ProgramDto> all = programBO.getAll();
        for (ProgramDto programDto : all) {
            ProgramTm programTm = new ProgramTm(programDto.getCourseId(), programDto.getCourseName(), programDto.getDuration(), programDto.getCourseFee());
            programTms.add(programTm);
        }
        tblProgram.setItems(programTms);
    }

    @FXML
    void btn_clear_onAction(ActionEvent event) {
        Clear();
    }
//
//    @FXML
//    void btn_delete_onAction(ActionEvent event) {
//        // Validate that a program is selected before trying to delete
//        if (txt_program_id.getText().isEmpty() || cmbProgramName.getValue() == null || cmbProgramName.getValue().isEmpty()) {
//            new Alert(Alert.AlertType.ERROR, "Please select a program to delete.").show();
//            return;
//        }
//
//        boolean isDeleted = programBO.delete(new ProgramDto(
//                txt_program_id.getText(),
//                cmbProgramName.getValue(),
//                txtDuration.getText(),
//                Double.parseDouble(txt_Program_fee.getText())
//        ));
//
//        if (isDeleted) {
//            Clear();
//            setTable();
//            setCellValueFactory();
//            tblProgram.refresh();
//            generateProgramID();
//            new Alert(Alert.AlertType.CONFIRMATION, "Program deleted successfully.").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Program deletion failed. Please try again.").show();
//        }
//    }
@FXML
void btn_delete_onAction(ActionEvent event) {
    try {
        // Validate that a program is selected before trying to delete
        if (txt_program_id.getText().isEmpty() || cmbProgramName.getValue() == null || cmbProgramName.getValue().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a program to delete.").show();
            return;
        }

        // Confirm before deleting the program
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this program?",
                ButtonType.YES, ButtonType.NO);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText(null);

        ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.NO);
        if (result == ButtonType.YES) {
            // Attempt to delete the program
            boolean isDeleted = programBO.delete(new ProgramDto(
                    txt_program_id.getText(),
                    cmbProgramName.getValue(),
                    txtDuration.getText(),
                    Double.parseDouble(txt_Program_fee.getText())
            ));

            if (isDeleted) {
                Clear();
                setTable();
                setCellValueFactory();
                tblProgram.refresh();
                generateProgramID();
                new Alert(Alert.AlertType.CONFIRMATION, "Program deleted successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Program deletion failed. Please try again.").show();
            }
        }
    } catch (NumberFormatException e) {
        new Alert(Alert.AlertType.ERROR, "Invalid program fee. Please enter a valid number.").show();
    } catch (Exception e) {
        new Alert(Alert.AlertType.ERROR, "An error occurred while deleting the program: " + e.getMessage()).show();
        e.printStackTrace(); // Log exception for debugging
    }
}


    @FXML
    void btn_save_on_action(ActionEvent event) {
        // Validate inputs
        if (txt_program_id.getText().isEmpty() || cmbProgramName.getValue() == null || cmbProgramName.getValue().isEmpty() ||
                txtDuration.getText().isEmpty() || txt_Program_fee.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields before saving the program.").show();
            return; // Stop further processing if validation fails
        }

        try {
            // Try to parse the fee as a double
            double courseFee = Double.parseDouble(txt_Program_fee.getText());

            // Validate course fee
            if (courseFee <= 0) {
                new Alert(Alert.AlertType.ERROR, "Course fee must be a positive number.").show();
                return;
            }

            // Proceed with saving the program
            boolean isSaved = programBO.save(new ProgramDto(
                    txt_program_id.getText(),
                    cmbProgramName.getValue(),
                    txtDuration.getText(),
                    courseFee
            ));

            if (isSaved) {
                Clear();
                setTable();
                setCellValueFactory();
                tblProgram.refresh();
                txt_program_id.setText(generateProgramID());
                new Alert(Alert.AlertType.CONFIRMATION, "Program saved successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Program save failed. Please try again.").show();
            }
        } catch (NumberFormatException e) {
            // Catch invalid fee format
            new Alert(Alert.AlertType.ERROR, "Invalid program fee format. Please enter a valid number.").show();
        }
    }

    @FXML
    void btn_update_onAction(ActionEvent event) {
        // Validate inputs
        if (txt_program_id.getText().isEmpty() || cmbProgramName.getValue() == null || cmbProgramName.getValue().isEmpty() ||
                txtDuration.getText().isEmpty() || txt_Program_fee.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields before updating the program.").show();
            return; // Stop further processing if validation fails
        }

        try {
            // Try to parse the fee as a double
            double courseFee = Double.parseDouble(txt_Program_fee.getText());

            // Validate course fee
            if (courseFee <= 0) {
                new Alert(Alert.AlertType.ERROR, "Course fee must be a positive number.").show();
                return;
            }

            // Proceed with updating the program
            boolean isUpdated = programBO.update(new ProgramDto(
                    txt_program_id.getText(),
                    cmbProgramName.getValue(),
                    txtDuration.getText(),
                    courseFee
            ));

            if (isUpdated) {
                Clear();
                setTable();
                setCellValueFactory();
                tblProgram.refresh();
                txt_program_id.setText(generateProgramID());
                new Alert(Alert.AlertType.CONFIRMATION, "Program updated successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Program update failed. Please try again.").show();
            }
        } catch (NumberFormatException e) {
            // Catch invalid fee format
            new Alert(Alert.AlertType.ERROR, "Invalid program fee format. Please enter a valid number.").show();
        }
    }

    public void setCenterNode(AnchorPane centerNode) {
        this.centerNode = centerNode;
    }

    public void Clear() {
        txtDuration.setText("");
        txt_Program_fee.setText("");
        txt_program_id.setText("");
        cmbProgramName.setValue("Program Name");
        generateProgramID();
    }
}

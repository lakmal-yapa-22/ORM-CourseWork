//package lk.ijse.controller;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.AnchorPane;
//import lk.ijse.bo.BOFactory;
//import lk.ijse.bo.custom.PaymentBO;
//import lk.ijse.dto.PaymentDto;
//import lk.ijse.entity.Registration;
//import lk.ijse.entity.tm.PayementTm;
//
//import java.util.List;
//
//public class PaymentFormController {
//    PaymentBO paymentBO = (PaymentBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.PAYMENT);
//
//    @FXML
//    private ComboBox<Registration> cmb_Registr_ID;
//
//    @FXML
//    private TableColumn<?, ?> col_Amount;
//
//    @FXML
//    private TableColumn<?, ?> col_FullCourse_fee;
//
//    @FXML
//    private TableColumn<?, ?> col_balance;
//
//    @FXML
//    private TableColumn<?, ?> col_paid_Amount;
//
//    @FXML
//    private TableColumn<?, ?> col_payment_ID;
//
//    @FXML
//    private TableColumn<?, ?> col_registr_ID;
//
//    @FXML
//    private AnchorPane registrPane;
//
//    @FXML
//    private TableView<PayementTm>tbl_Payment;
//
//    @FXML
//    private TextField txtAmount;
//
//    @FXML
//    private TextField txtPaidAmount;
//
//    @FXML
//    private TextField txt_Full_Course_Fee;
//
//    @FXML
//    private TextField txt_payment_ID;
//
//
//    @FXML
//    private TableColumn<?, ?> col_pay;
//
//    private AnchorPane centerNode;
//
//    @FXML
//    private TextField txtCash;
//
//
//    @FXML
//    void btn_Payment_onAction(ActionEvent event) {
//        String paymentId=txt_payment_ID.getText();
////        double amount = Double.parseDouble(txtAmount.getText());
//
//       double paidAmount=Double.parseDouble(txtPaidAmount.getText());
//      double fullPayment=Double.parseDouble(txt_Full_Course_Fee.getText());
//      double pay=Double.parseDouble(txtCash.getText());
//      double amount=fullPayment-paidAmount;
//        double balance=pay-amount;
//      Registration selecteRegistr = this.cmb_Registr_ID.getSelectionModel().getSelectedItem();
//
//      if(pay>=amount){
//        boolean isSaved = paymentBO.save(new PaymentDto(paymentId,amount,paidAmount,fullPayment,pay,balance,selecteRegistr));
//        if (isSaved){
//            clearValue();
//
//tbl_Payment.refresh();
//setCellValueFactory();
//generatePaymentId();
//            new Alert(Alert.AlertType.CONFIRMATION,"Payment successfully").show();
//        } else {
//            clearValue();
//            new Alert(Alert.AlertType.ERROR,"Payment unsuccessfully").show();
//        }
//
//
//
//
//    }else {
//          clearValue();
//          new Alert(Alert.AlertType.CONFIRMATION,"invalid payment please try again").show();
//      }
//    }
//
//
//
//
//    @FXML
//    void btn_clear_onAction(ActionEvent event) {
//  clearValue();
//
//
//
//    }
//
//    private void clearValue() {
//        txtAmount.clear();
//        txtPaidAmount.clear();
//        txtCash.clear();
//        txt_Full_Course_Fee.clear();
//        txt_payment_ID.clear();
//        cmb_Registr_ID.getSelectionModel().clearSelection();
//        generatePaymentId();
//    }
//
//    public void initialize() {
//        setComboRegistr();
//        setTable();
//        setCellValueFactory();
//        generatePaymentId();
//    }
//
//    private String generatePaymentId() {
//        try {
//            String currentId = paymentBO.getCurrentId();
//            if (currentId != null) {
//                String[] split = currentId.split("Pay00");
//                int idNum = Integer.parseInt(split[1]);
//                String availableId = "Pay00" + ++idNum;
//                txt_payment_ID.setText(availableId);
//                return availableId;
//            } else {
//                txt_payment_ID.setText("Pay001");
//                return "Pay001";
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//
//    private void setCellValueFactory() {
//       col_payment_ID.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
//       col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
//
//       col_paid_Amount.setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
//        col_FullCourse_fee.setCellValueFactory(new PropertyValueFactory<>("fullPayment"));
//       col_balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
//       col_pay.setCellValueFactory(new PropertyValueFactory<>("pay"));
//       col_registr_ID.setCellValueFactory(new PropertyValueFactory<>("registration"));
//
//
//
//
//    }
//
//    private void setTable() {
//        ObservableList<PayementTm> payementTms = FXCollections.observableArrayList();
//        List<PaymentDto> all = paymentBO.getAll();
//        for (PaymentDto paymentDtoo : all){
//           PayementTm payementTm = new PayementTm(paymentDtoo.getPaymentId(),paymentDtoo.getAmount(),paymentDtoo.getPaidAmount(),paymentDtoo.getFullPayment(),paymentDtoo.getPay(),paymentDtoo.getBalance(),paymentDtoo.getRegistration());
//            payementTms.add(payementTm);
//        }
//      tbl_Payment.setItems(payementTms);
//    }
//
//    private void setComboRegistr() {
//        List<Registration> registrationIds = paymentBO.getRegistrationIds();
//        cmb_Registr_ID.getItems().addAll(registrationIds);
//
//
//        cmb_Registr_ID.setOnAction(event -> {
//            Registration selectedRegistrationId = cmb_Registr_ID.getValue();
//
//            if (selectedRegistrationId != null) {
//                double paidAmount = paymentBO.getPaidAmountByRegistrationId(selectedRegistrationId);
//                txtPaidAmount.setText(String.valueOf(paidAmount));
//
//
//                double fullFee = paymentBO.getFullFeeRegistrationId(selectedRegistrationId);
//             txt_Full_Course_Fee.setText(String.valueOf(fullFee));
//
//                double amount = paymentBO.getAmounteRegistrationId(selectedRegistrationId);
//                txtAmount.setText(String.valueOf(amount));
//
//
//
//
//            }
//
//        });
//    }
//
//
//    public void setCenterNode(AnchorPane centerNode) {
//        this.centerNode=centerNode;
//    }
//}
package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dto.PaymentDto;
import lk.ijse.entity.Registration;
import lk.ijse.entity.tm.PayementTm;

import java.util.List;

public class PaymentFormController {
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.PAYMENT);

    @FXML
    private ComboBox<Registration> cmb_Registr_ID;

    @FXML
    private TextField txtAmount, txtPaidAmount, txt_Full_Course_Fee, txt_payment_ID, txtCash;

    @FXML
    private TableView<PayementTm> tbl_Payment;

    @FXML
    private TableColumn<?, ?> col_payment_ID, col_Amount, col_paid_Amount, col_FullCourse_fee, col_balance, col_pay, col_registr_ID;
    private AnchorPane centerNode2;

    @FXML
    void btn_Payment_onAction(ActionEvent event) {
        try {
            if (!validateInputs()) {
                new Alert(Alert.AlertType.ERROR, "Please correct the highlighted fields.").show();
                return;
            }

            String paymentId = txt_payment_ID.getText();
            double paidAmount = Double.parseDouble(txtPaidAmount.getText());
            double fullPayment = Double.parseDouble(txt_Full_Course_Fee.getText());
            double pay = Double.parseDouble(txtCash.getText());
            double amount = fullPayment - paidAmount;
            double balance = pay - amount;

            Registration selectedRegistr = cmb_Registr_ID.getSelectionModel().getSelectedItem();

            if (pay >= amount) {
                boolean isSaved = paymentBO.save(new PaymentDto(paymentId, amount, paidAmount, fullPayment, pay, balance, selectedRegistr));

                if (isSaved) {
                    clearValue();
                    refreshTable();
                    generatePaymentId();
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment successfully saved.").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Payment saving failed.").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid payment amount. Please try again.").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }

    @FXML
    void btn_clear_onAction(ActionEvent event) {
        clearValue();
    }

    public void initialize() {
        setComboRegistr();
        refreshTable();
        setCellValueFactory();
        generatePaymentId();
        addValidationListeners();
    }

    private void addValidationListeners() {
        String numberPattern = "\\d+(\\.\\d+)?"; // Regex for a valid number

        addTextValidation(txtPaidAmount, numberPattern);
        addTextValidation(txt_Full_Course_Fee, numberPattern);
        addTextValidation(txtCash, numberPattern);
    }

    private void addTextValidation(TextField textField, String pattern) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(pattern)) {
                textField.setStyle("-fx-text-fill: red;"); // Invalid input
            } else {
                textField.setStyle("-fx-text-fill: black;"); // Valid input
            }
        });
    }

    private boolean validateInputs() {
        boolean isValid = true;
        String numberPattern = "\\d+(\\.\\d+)?"; // Regex for a valid number

        if (!txtPaidAmount.getText().matches(numberPattern)) isValid = false;
        if (!txt_Full_Course_Fee.getText().matches(numberPattern)) isValid = false;
        if (!txtCash.getText().matches(numberPattern)) isValid = false;

        return isValid;
    }

    private void clearValue() {
        txtAmount.clear();
        txtPaidAmount.clear();
        txtCash.clear();
        txt_Full_Course_Fee.clear();
        txt_payment_ID.clear();
        cmb_Registr_ID.getSelectionModel().clearSelection();
        generatePaymentId();
    }

    private void refreshTable() {
        ObservableList<PayementTm> payementTms = FXCollections.observableArrayList();
        List<PaymentDto> all = paymentBO.getAll();
        for (PaymentDto paymentDto : all) {
            PayementTm payementTm = new PayementTm(
                    paymentDto.getPaymentId(),
                    paymentDto.getAmount(),
                    paymentDto.getPaidAmount(),
                    paymentDto.getFullPayment(),
                    paymentDto.getPay(),
                    paymentDto.getBalance(),
                    paymentDto.getRegistration()
            );
            payementTms.add(payementTm);
        }
        tbl_Payment.setItems(payementTms);
    }

    private void generatePaymentId() {
        try {
            String currentId = paymentBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("Pay00");
                int idNum = Integer.parseInt(split[1]);
                txt_payment_ID.setText("Pay00" + ++idNum);
            } else {
                txt_payment_ID.setText("Pay001");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setComboRegistr() {
        List<Registration> registrationIds = paymentBO.getRegistrationIds();
        cmb_Registr_ID.getItems().addAll(registrationIds);

        cmb_Registr_ID.setOnAction(event -> {
            Registration selectedRegistration = cmb_Registr_ID.getValue();

            if (selectedRegistration != null) {
                double paidAmount = paymentBO.getPaidAmountByRegistrationId(selectedRegistration);
                txtPaidAmount.setText(String.valueOf(paidAmount));

                double fullFee = paymentBO.getFullFeeRegistrationId(selectedRegistration);
                txt_Full_Course_Fee.setText(String.valueOf(fullFee));

                double amount = paymentBO.getAmounteRegistrationId(selectedRegistration);
                txtAmount.setText(String.valueOf(amount));
            }
        });
    }

    private void setCellValueFactory() {
        col_payment_ID.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_paid_Amount.setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
        col_FullCourse_fee.setCellValueFactory(new PropertyValueFactory<>("fullPayment"));
        col_balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        col_pay.setCellValueFactory(new PropertyValueFactory<>("pay"));
        col_registr_ID.setCellValueFactory(new PropertyValueFactory<>("registration"));
    }

    public void setCenterNode(AnchorPane centerNode2) {
        this.centerNode2=centerNode2;
    }
}

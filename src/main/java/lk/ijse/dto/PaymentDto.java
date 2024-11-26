package lk.ijse.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lk.ijse.entity.Registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentDto {
    private String paymentId;
    private double amount;
    private double paidAmount;
    private double fullPayment;
    private double pay;
    private double balance;
    private Registration registration;

    public PaymentDto(String text, Registration selectRegistrID, double fullFee,double pay, double payedAmount, double amount) {
        this.paymentId = text;
        this.registration = selectRegistrID;
        this.fullPayment = fullFee;
        this.pay = pay;
        this.paidAmount = payedAmount;
        this.amount = amount;

    }
}

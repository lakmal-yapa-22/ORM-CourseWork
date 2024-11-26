package lk.ijse.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
@Entity
public class Payment {

    @Id
    private String paymentId;

    private double amount;
    private double paidAmount;
    private double fullPayment;
    private double pay;
    private double balance;


    @OneToOne(cascade = CascadeType.ALL)
    private Registration registration;
public Payment(String paymentId, double amount, double paidAmount, double fullPayment,double pay, double balance, Registration registration) {
    this.paymentId = paymentId;
    this.amount = amount;
    this.paidAmount = paidAmount;
    this.fullPayment = fullPayment;
    this.pay = pay;
    this.balance = balance;
    this.registration = registration;
}


}

package lk.ijse.entity.tm;

import lk.ijse.entity.Registration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayementTm {
    private String paymentId;
    private double amount;
    private double paidAmount;
    private double fullPayment;
    private double pay;
    private double balance;
    private Registration registration;
}

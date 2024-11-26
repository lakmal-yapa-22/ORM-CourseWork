package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.PaymentDto;
import lk.ijse.entity.Registration;

import java.util.List;

public interface PaymentBO extends SuperBo {


    List<Registration> getRegistrationIds();

    double getPaidAmountByRegistrationId(Registration selectedRegistrationId);

    double getFullFeeRegistrationId(Registration selectedRegistrationId);

    double getAmounteRegistrationId(Registration selectedRegistrationId);

    boolean save(PaymentDto paymentDto);

    List<PaymentDto> getAll();

    String getCurrentId();
}

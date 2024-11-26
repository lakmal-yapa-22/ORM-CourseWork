package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Registration;

import java.io.IOException;
import java.util.List;

public interface PaymentDAO extends CrudDAO {


    List<Registration> getRegistrationIds();

    double getPaidAmountByRegistrationId(Registration registrationId);

    double getFullFeeByRegistrationId(Registration selectedRegistrationId);

    double getAmountRegistrationId(Registration selectedRegistrationId);

    String getCurrentId() throws IOException;
}

package lk.ijse.bo.custom.impl;

import lk.ijse.bo.SuperBo;
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dao.custom.ProgramDAO;
import lk.ijse.dto.PaymentDto;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Program;
import lk.ijse.entity.Registration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOType.PAYMENT);


    @Override
    public List<Registration> getRegistrationIds() {
        return paymentDAO.getRegistrationIds();
    }
    @Override
    public double getPaidAmountByRegistrationId(Registration registrationId) {
        return paymentDAO.getPaidAmountByRegistrationId(registrationId);
    }

    @Override
    public double getFullFeeRegistrationId(Registration selectedRegistrationId) {
        return paymentDAO.getFullFeeByRegistrationId(selectedRegistrationId);
    }

    @Override
    public double getAmounteRegistrationId(Registration selectedRegistrationId) {
        return paymentDAO.getAmountRegistrationId(selectedRegistrationId);
    }

    @Override
    public boolean save(PaymentDto paymentDto) {
        try {
            return paymentDAO.save(new Payment(paymentDto.getPaymentId(),paymentDto.getAmount(),paymentDto.getPaidAmount(),paymentDto.getFullPayment(),paymentDto.getPay(),paymentDto.getBalance(),paymentDto.getRegistration()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<PaymentDto> getAll() {
        List<PaymentDto> paymentDtos = new ArrayList<>();
        List<Payment> all = null;
        try {
            all = paymentDAO.getAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Payment payment : all){
           paymentDtos.add(new PaymentDto(payment.getPaymentId(),payment.getAmount(),payment.getPaidAmount(),payment.getFullPayment(),payment.getPay(),payment.getBalance(),payment.getRegistration()));
        }
        return paymentDtos;
    }

    @Override
    public String getCurrentId() {
        try {
            return paymentDAO.getCurrentId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

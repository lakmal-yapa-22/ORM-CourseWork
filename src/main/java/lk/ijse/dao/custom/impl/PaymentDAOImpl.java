package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Registration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Object object) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT * FROM Payment");
        query.addEntity(Payment.class);
        List<Payment> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }


    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }


    @Override
    public List<Registration> getRegistrationIds() {
        List<Registration> registrationIds = new ArrayList<>();
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            String hql = "FROM Registration ";
            Query<Registration> query = session.createQuery(hql, Registration.class);
            registrationIds = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registrationIds;
    }
    double paidAmount = 0;
    @Override
    public double getPaidAmountByRegistrationId(Registration registrationId) {

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            String hql = "SELECT r.advanced FROM Registration r WHERE r.registrationId = :registrationId";
            Query<Double> query = session.createQuery(hql, Double.class);
            query.setParameter("registrationId", registrationId.getRegistrationId()); // Assuming Registration has a getRegistrationId() method
            paidAmount = query.uniqueResult(); // This retrieves only the specific result
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paidAmount;
    }
    double fullFee = 0;
    @Override
    public double getFullFeeByRegistrationId(Registration selectedRegistrationId) {

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            String hql = "SELECT prog.courseFee " +
                    "FROM Registration reg " +
                    "JOIN reg.courses prog " + // Use 'reg.courses' to reference the Program
                    "WHERE reg.registrationId = :registrationId";

            Query<Double> query = session.createQuery(hql, Double.class);
            query.setParameter("registrationId", selectedRegistrationId.getRegistrationId());
            fullFee = query.uniqueResult(); // Retrieves only the specific result
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullFee;
    }

    @Override
    public double getAmountRegistrationId(Registration selectedRegistrationId) {
       double amount=fullFee-paidAmount;
       return amount;
    }

    @Override
    public String getCurrentId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select paymentId from Payment order by paymentId desc limit 1");
        String id = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        return id;
    }


}

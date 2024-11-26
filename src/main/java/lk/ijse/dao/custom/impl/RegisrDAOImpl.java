package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.RegistrDAo;
import lk.ijse.entity.Program;
import lk.ijse.entity.Registration;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisrDAOImpl implements RegistrDAo {
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
    public List<Registration> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT * FROM Registration");
        query.addEntity(Registration.class);
        List<Registration> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public boolean delete(Object object) throws IOException {
        try {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction tx = session.beginTransaction();
            session.delete(session.get(Registration.class, object));
            tx.commit();
            session.close();
            return true;
        }catch (Exception e){
            return false;
        }

    }


    @Override
    public boolean update(Object object) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Program> getProgramid() {
        List<Program> programs = new ArrayList<>();
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Use HQL to get all User objects
            String hql = "FROM Program "; // This will retrieve all User entities
            Query<Program> query = session.createQuery(hql, Program.class);
           programs = query.list(); // This will now contain User objects
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logger instead of printStackTrace
        }
        return programs;
    }

    @Override
    public String getCurrentId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select registrationId from Registration order by registrationId desc limit 1");
        String id = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public List<Student> getStudentID() {
        List<Student> students = new ArrayList<>();
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Use HQL to get all User objects
            String hql = "FROM Student "; // This will retrieve all User entities
            Query<Student> query = session.createQuery(hql, Student.class);
            students = query.list(); // This will now contain User objects
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logger instead of printStackTrace
        }
        return students;
    }
}

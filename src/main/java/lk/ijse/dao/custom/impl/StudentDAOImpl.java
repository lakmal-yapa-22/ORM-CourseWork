package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.StudentDto;
import lk.ijse.entity.Registration;
import lk.ijse.entity.Student;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {



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
    public List<Student> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT * FROM Student");
        query.addEntity(Student.class);
        List<Student> resultList = query.getResultList();
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
    public List<User> getid() {
        List<User> users = new ArrayList<>();
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Use HQL to get all User objects
            String hql = "FROM User"; // This will retrieve all User entities
            Query<User> query = session.createQuery(hql, User.class);
            users = query.list(); // This will now contain User objects
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logger instead of printStackTrace
        }
        return users;
    }

    @Override
    public String getCurrentID() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select studentId from Student order by studentId desc limit 1");
        String id = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public List<StudentDto> getAllRegistrStudent() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<Object[]> query = session.createQuery(
                "SELECT DISTINCT s.studentId, s.studentFirstName " +
                        "FROM Student s " +
                        "JOIN s.registrations r " +
                        "JOIN r.courses c",
                Object[].class
        );

        List<Object[]> results = query.getResultList();
        List<StudentDto> studentDTOs = new ArrayList<>();

        for (Object[] result : results) {
            String studentId = (String) result[0];
            String studentFirstName = (String) result[1];
            studentDTOs.add(new StudentDto(studentId, studentFirstName));
        }

        transaction.commit();
        session.close();

        return studentDTOs; // Return the list of StudentDTOs
    }


}

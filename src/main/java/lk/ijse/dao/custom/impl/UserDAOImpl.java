package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl  implements UserDAO {
//    @Override
//    public boolean save(Object object) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(object);
//        transaction.commit();
//        session.close();
//        return true;
//    }
    @Override
    public boolean save(Object object) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // Hash the password before saving
        User user = (User) object;
        String hashedPassword = BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt());
        user.setUserPassword(hashedPassword);  // Set the hashed password

        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<User> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT * FROM user");
        query.addEntity(User.class);
        List<User> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public boolean delete(Object object) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
        session.close();
        return true;
    }

//    @Override
//    public boolean update(Object object) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        session.update(object);
//        transaction.commit();
//        session.close();
//        return true;
//    }
@Override
public boolean update(Object object) throws IOException {
    Session session = FactoryConfiguration.getInstance().getSession();
    Transaction transaction = session.beginTransaction();

    // Hash the password before updating
    User user = (User) object;
    String hashedPassword = BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt());
    user.setUserPassword(hashedPassword);  // Set the hashed password

    session.update(user);
    transaction.commit();
    session.close();
    return true;
}


    @Override
    public String getCurrentId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        String id = null;

        try {
            transaction = session.beginTransaction();
            Query<String> query = session.createQuery("SELECT u.user_id FROM User u ORDER BY u.user_id DESC", String.class);
            id = query.setMaxResults(1).uniqueResult(); // Get the latest userId
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            e.printStackTrace(); // Print exception for debugging
        } finally {
            session.close(); // Ensure session is closed
        }

        return id;
    }

    @Override
    public String getUserRole(String userName) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        String userRole = null;

        try {
            transaction = session.beginTransaction();

            // HQL query to retrieve userRole where userName matches
            Query<String> query = session.createQuery("SELECT u.userRole FROM User u WHERE u.user_id = :userName", String.class);
            query.setParameter("userName", userName);

            // Get the userRole
            userRole = query.setMaxResults(1).uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            e.printStackTrace(); // Print exception for debugging
        } finally {
            session.close(); // Ensure session is closed
        }

        return userRole;
    }

//    @Override
//    public boolean checkCredentials(String username, String password) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = null;
//        boolean isValid = false;
//
//        try {
//            transaction = session.beginTransaction();
//
//            // HQL query to check if a user with the specified username and password exists
//            Query<Long> query = session.createQuery(
//                    "SELECT COUNT(u) FROM User u WHERE u.user_id = :username AND u.userPassword = :password",
//                    Long.class
//            );
//            query.setParameter("username", username);
//            query.setParameter("password", password);
//
//            // Check if count is greater than 0, indicating a matching user exists
//            Long count = query.uniqueResult();
//            isValid = count > 0;
//
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback(); // Rollback on error
//            e.printStackTrace(); // Print exception for debugging
//        } finally {
//            session.close(); // Ensure session is closed
//        }
//
//        return isValid;
//    }
    @Override
    public boolean checkCredentials(String username, String password) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        boolean isValid = false;

        try {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery("FROM User u WHERE u.user_id = :username", User.class);
            query.setParameter("username", username);
            User user = query.uniqueResult();

            if (user != null && BCrypt.checkpw(password, user.getUserPassword())) {
                isValid = true;  // Password matches the hashed password
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return isValid;
    }


    @Override
    public List<User> getSelectAll(String userName) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        List<User> resultList = new ArrayList<>();

        try {
            transaction = session.beginTransaction();

            // Use HQL to directly map to the User entity
            Query<User> query = session.createQuery("FROM User u WHERE u.userName = :userName", User.class);
            query.setParameter("userName", userName);

            resultList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            e.printStackTrace(); // Print exception for debugging
        } finally {
            session.close(); // Ensure session is closed
        }

        return resultList;
    }

    @Override
    public List<User> getSelectAllID(String userId) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        List<User> resultList = new ArrayList<>();

        try {
            transaction = session.beginTransaction();

            // Use HQL to directly map to the User entity
            Query<User> query = session.createQuery("FROM User u WHERE u.user_id = :user_id", User.class);
            query.setParameter("user_id", userId);

            resultList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            e.printStackTrace(); // Print exception for debugging
        } finally {
            session.close(); // Ensure session is closed
        }

        return resultList;
    }

}

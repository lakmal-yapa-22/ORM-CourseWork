package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}

    public enum DAOType{
      USER,STUDENT,PROGRAM,REGISTR,PAYMENT
    }
    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }
    public SuperDAO getDAOType(DAOType boType){
        switch (boType){
            case USER:
                return new UserDAOImpl();
                case STUDENT:
                    return  new StudentDAOImpl();
                    case PROGRAM:
                        return  new ProgramDAOImpl();
                        case REGISTR:
                            return new RegisrDAOImpl();
                            case PAYMENT:
                                return new PaymentDAOImpl();

            default:
                return null;
        }
    }
}

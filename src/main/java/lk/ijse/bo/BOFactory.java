package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory  {
    private static BOFactory boFactory;
    private BOFactory(){}

    public enum BOType{
      USER,STUDENT,PROGRAM,REGISTR,PAYMENT
    }

    public static BOFactory getBOFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }
    public SuperBo getBOType(BOType boType){
        switch (boType){
            case USER:
               return new UserBOImpl();
               case STUDENT:
                   return new StudentBOImpl();
                   case PROGRAM:
                       return new ProgramBOImpl();
                       case REGISTR:
                           return new RegistrBOImpl();
                           case PAYMENT:
                               return new PaymentBOImpl();


            default:
                return null;
        }
    }
}

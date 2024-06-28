package lk.ijse.CinnaCraft.dao;

import lk.ijse.CinnaCraft.dao.custom.impl.*;

public class DAOFactory {
    //hadanwa singaton usw karala private instance ekak
    private static DAOFactory daoFactory;

    //Constructor eka private kara
    private DAOFactory(){}

    public static DAOFactory getInstance(){
        //check kara if instance eka null da kiyala- if so create a new instance  or return kara exisiting instance
       return(daoFactory == null) ? new DAOFactory() : daoFactory;
    }


    public enum DAOTypes{
        ATTENDANCE,CUSTOMER,EMPLOYEE, FERTILIZER,PACKAGING, FERTILIZER_ORDER,FERTILIZER_ORDER_DETAIL,PACKAGING_DETAILS, PAYMENTS, SALARY, SUPPLIER,CINNAMON_ORDER,CINNAMON_BOOK,CINNAMON_BOOK_TYPE,CINNAMON_BARK_STOCK, CINNAMON_TYPE,CINNAMON_ORDER_DETAIL,CINNACRAFT_DETAIL, USER;
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case ATTENDANCE:
                return  new AttendanceDAOImpl();
            case CUSTOMER:
                return  new CustomerDAOImpl();
            case EMPLOYEE:
                return  new EmployeeDAOImpl();
            case FERTILIZER:
                return new FertilizerDAOImpl();
            case PACKAGING:
                return  new PackagingDAOImpl();
            case FERTILIZER_ORDER:
                return  new FertilizerOrderDAOImpl();
            case FERTILIZER_ORDER_DETAIL:
                return  new FertilizerOrderDetailDAOImpl();
            case PACKAGING_DETAILS:
                return  new PackagingDetailsDAOImpl();
            case PAYMENTS:
                return  new PaymentsDAOImpl();
            case SALARY:
                return  new SalaryDAOImpl();
            case SUPPLIER:
                return  new SupplierDAOImpl();
            case CINNAMON_ORDER:
                return  new CinnamonOrderDAOImpl();
            case CINNAMON_BOOK:
                return  new CinnamonBookDAOImpl();
            case CINNAMON_BOOK_TYPE:
                return  new CinnamonBookTypeDAOImpl();
            case CINNAMON_BARK_STOCK:
                return  new CinnamonBarkStockDAOImpl();
            case CINNAMON_TYPE:
                return  new CinnamonTypeDAOImpl();
            case CINNAMON_ORDER_DETAIL:
                return  new CinnamonOrderDetailDAOImpl();
            case CINNACRAFT_DETAIL:
                return  new CinnaCraftDetailDAOImpl();
            case USER:
                return  new UserDAOImpl();
            default:
               return null;

        }

    }
}

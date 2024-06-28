package lk.ijse.CinnaCraft.bo;

import lk.ijse.CinnaCraft.bo.custom.Impl.*;

public class BOFactory {


    private static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
      return   (boFactory== null) ?boFactory = new BOFactory():boFactory;
    }

    public enum BoTypes{
        CUSTOMER,USER,ATTENDANCE,EMPLOYEE,SUPPLIER,SALARY,FERTILIZER,PACKAGING,FERTILIZER_ORDER,PAYMENTS,CINNAMON_ORDER,CINNAMON_BARK_STOCK,CINNAMON_BOOK,CINNAMON_BOOK_TYPE,PROCESSING,CINNAMON_TYPE,PACKAGING_DETAILS,CINNACRAFT_DETAIL;
    }
    public SuperBo getBO(BoTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
            return new CustomerBOImpl();
            case USER:
                return new UserBOImpl();
            case ATTENDANCE:
                return new AttendanceBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case SUPPLIER:
                return new SupplierBoImpl();
            case SALARY:
                return  new SalaryBOImpl();
            case FERTILIZER:
                return  new FertilizerBOImpl();
            case PACKAGING:
                return new PackagingBoImpl();
            case FERTILIZER_ORDER:
                return new FertilizerOrderBOImpl();
            case PAYMENTS:
                return  new PaymentsBOImpl();
            case CINNAMON_ORDER:
                return  new CinnamonOrderBoImpl();
            case CINNAMON_BARK_STOCK:
                return new CinnamonBarkStockBOImpl();
            case CINNAMON_BOOK:
                return  new CinnamonBookBOImpl();
            case CINNAMON_BOOK_TYPE:
                return new CinnamonBookTypeBoImpl();
            case PROCESSING:
                return  new ProcessingBOImpl();
            case CINNAMON_TYPE:
                return  new CinnamonTypeBOImpl();
            case PACKAGING_DETAILS:
                return new PackagingDetailsBOImpl();
            case CINNACRAFT_DETAIL:
                return  new CinnaCraftDetailBOImpl();
            default:
                return null;

        }

    }
























}

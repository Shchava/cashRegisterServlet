package ua.training.cashregister.dao;

import ua.training.cashregister.dao.impl.JDBCDaoFactory;
import ua.training.cashregister.entity.Receipt;

public abstract class DaoFactory {
    private static  DaoFactory daoFactory;

    public abstract UserDao createUserDao();

    public abstract GoodsDao createGoodsDao();

    public abstract ReceiptDao createReceiptDao();

    public static synchronized DaoFactory getInstance(){
        if( daoFactory == null){
            synchronized (DaoFactory.class) {
                if(daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}

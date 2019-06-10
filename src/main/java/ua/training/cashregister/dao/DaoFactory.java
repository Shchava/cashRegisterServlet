package ua.training.cashregister.dao;

import ua.training.cashregister.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static  DaoFactory daoFactory;

    public abstract UserDao createUserDao();

    public abstract GoodsDao createGoodsDao();

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

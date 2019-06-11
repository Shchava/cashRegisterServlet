package ua.training.cashregister.dao.impl;

import ua.training.cashregister.dao.DaoFactory;
import ua.training.cashregister.dao.GoodsDao;
import ua.training.cashregister.dao.ReceiptDao;
import ua.training.cashregister.dao.UserDao;
import ua.training.cashregister.entity.Receipt;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public GoodsDao createGoodsDao() {
        return new JDBCGoodsDao(getConnection());
    }

    @Override
    public ReceiptDao createReceiptDao() {
        return new JDBCReceiptDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

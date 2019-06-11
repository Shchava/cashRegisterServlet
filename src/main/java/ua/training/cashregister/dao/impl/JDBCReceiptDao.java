package ua.training.cashregister.dao.impl;

import ua.training.cashregister.dao.ReceiptDao;
import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.entity.ReceiptEntry;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JDBCReceiptDao implements ReceiptDao {
    private Connection connection;

    public JDBCReceiptDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Receipt entity) {
        boolean created = false;
        final String query = "INSERT INTO receipt(created,cashier,senior_cashier) VALUES(?,?,?)";
        try(PreparedStatement statement =  connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setTimestamp(1,Timestamp.valueOf(entity.getCreated()));
            statement.setLong(2,entity.getCashier().getId());
            statement.setLong(3,entity.getSeniorCashier().getId());

            int affected = statement.executeUpdate();
            if(affected == 1){
                setId(entity,statement);
                created = true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return created;
    }

    @Override
    public List<Receipt> findAll() {
        return null;
    }

    @Override
    public Optional<Receipt> findById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean update(Receipt entity) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public void close() {

    }

    private void createEntries(List<ReceiptEntry> entries) throws SQLException {
        final String query = "INSERT INTO receipt_entry (id_receipt,id_goods,amount,price) VALUES(?,?,? ?)";
        try(PreparedStatement statement =  connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            for(ReceiptEntry entry : entries) {
                createEntry(entry,statement);
            }
        }
    }


    private void createEntry(ReceiptEntry entry, PreparedStatement statement) throws SQLException {
        statement.setLong(1,entry.getReceipt().getId_receipt());
        statement.setLong(2,entry.getGoods().getId());
        statement.setInt(3,entry.getAmount());
        statement.setInt(4,entry.getPrice());

        statement.execute();
    }


    private void setId(Receipt receipt, Statement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            receipt.setId_receipt(generatedKeys.getLong(1));
        }
    }
}

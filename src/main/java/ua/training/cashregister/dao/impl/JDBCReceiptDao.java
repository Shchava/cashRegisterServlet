package ua.training.cashregister.dao.impl;

import ua.training.cashregister.dao.ReceiptDao;
import ua.training.cashregister.dao.mapper.ReceiptEntryMapper;
import ua.training.cashregister.dao.mapper.ReceiptMapper;
import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.entity.ReceiptEntry;

import java.sql.*;
import java.util.ArrayList;
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
        final String query = "INSERT INTO receipt(created,cashier) VALUES(?,?)";
        try(PreparedStatement statement =  connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            if(entity.getCreated() != null) {
                statement.setTimestamp(1, Timestamp.valueOf(entity.getCreated()));
            }else{
                statement.setTimestamp(1, null);
            }
            statement.setLong(2,entity.getCashier().getId());

            int affected = statement.executeUpdate();
            if(affected == 1){
                setId(entity,statement);
                created = true;
            }
            createEntries(entity.getEntries());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return created;
    }

    @Override
    public List<Receipt> find(int start, int count) {
        List<Receipt> receiptList = new ArrayList<>();
        final String query = "SELECT SQL_CALC_FOUND_ROWS * FROM receipt " +
                "LEFT JOIN user ON(receipt.cashier = user.id_user)"+
                "LIMIT " + start + "," + count;
        return selectAllFromQuery(receiptList,query);
    }

    @Override
    public List<Receipt> findAll() {
        List<Receipt> receiptList = new ArrayList<>();

        final String query = "SELECT * FROM receipt " +
                             "LEFT JOIN user ON(receipt.cashier = user.id_user);";
        return selectAllFromQuery(receiptList,query);
    }

    @Override
    public Optional<Receipt> findById(long id) {

        final String query = "SELECT * FROM receipt " +
                             "LEFT JOIN user ON(receipt.cashier = user.id_user) " +
                             "WHERE receipt.id_receipt = " + id +";";
        Receipt found = selectFromQuery(query);
        return Optional.ofNullable(found);
    }

    @Override
    public int getNumberOfRows() {
        int count = 0;
        final String query = "SELECT COUNT(*)FROM receipt";
        try(Statement statement = connection.createStatement()){
            count = statement.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Receipt> findByUserId(long userId) {
        List<Receipt> receiptList = new ArrayList<>();

        final String query = "SELECT * FROM receipt " +
                "LEFT JOIN user ON(receipt.cashier = user.id_user) " +
                "WHERE user.id_user = " + userId + ";";
        return selectAllFromQuery(receiptList,query);
    }

    @Override
    public Optional<Receipt> findNotClosedByUserId(long userId) {
        final String query = "SELECT * FROM receipt " +
                "LEFT JOIN user ON(receipt.cashier = user.id_user) " +
                "WHERE user.id_user = " + userId + " AND receipt.created IS NULL";

        Receipt found = selectFromQuery(query);
        return Optional.ofNullable(found);
    }

    @Override
    public boolean createReceiptEntry(ReceiptEntry entry) {
        boolean created = false;
        final String query = "INSERT INTO receipt_entry (id_receipt,id_goods,amount,price) VALUES(?,?,?,?)";
        try(PreparedStatement statement =  connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            created = createEntry(entry,statement);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return created;
    }

    @Override
    public boolean update(Receipt entity) {
        boolean created = false;
        final String query = "UPDATE receipt SET created = ?, cashier = ? WHERE id_receipt = ?";
        try(PreparedStatement statement =  connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            if(entity.getCreated() != null) {
                statement.setTimestamp(1, Timestamp.valueOf(entity.getCreated()));
            }else{
                statement.setTimestamp(1,null);
            }
            statement.setLong(2,entity.getCashier().getId());
            statement.setLong(3,entity.getId());

            int affected = statement.executeUpdate();
            created = affected == 1;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return created;
    }

    @Override
    public boolean updateReceiptEntry(ReceiptEntry entry){
        boolean created = false;
        final String query = "UPDATE receipt_entry SET amount = ?, price = ? WHERE id_receipt = ? AND id_goods = ?";
        try(PreparedStatement statement =  connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1,entry.getAmount());
            statement.setInt(2,entry.getPrice());
            statement.setLong(3,entry.getReceipt().getId());
            statement.setLong(4,entry.getGoods().getId());

            int affected = statement.executeUpdate();
            created = affected == 1;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return created;
    }

    @Override
    public boolean delete(long id) {
        boolean affected = false;
        final String query = "DELETE FROM receipt WHERE id_receipt = " + id + ";";
        try(Statement statement =  connection.createStatement()){
            statement.execute(query);
            affected = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return affected;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ReceiptEntry> findEntriesFromReceipt(Receipt receipt) {
        List<ReceiptEntry> entryList = new ArrayList<>();
        ReceiptEntryMapper mapper = new ReceiptEntryMapper();

        final String query = "SELECT * " +
                "FROM receipt_entry " +
                "LEFT JOIN goods ON (receipt_entry.id_goods = goods.id_goods)" +
                " where receipt_entry.id_receipt = ?";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, receipt.getId());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ReceiptEntry entry = mapper.extractFromResultSet(rs);
                entry.setReceipt(receipt);
                entryList.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entryList;
    }

    private Receipt selectFromQuery(String query){
        ReceiptMapper mapper = new ReceiptMapper();
        Receipt found = null;

        try(Statement statement =  connection.createStatement()){
            ResultSet result = statement.executeQuery(query);
            if(result.next()){
                found = mapper.extractFromResultSet(result);
                found.setEntries(findEntriesFromReceipt(found));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return found;
    }

    private List<Receipt> selectAllFromQuery(List<Receipt> receiptList, String query){
        ReceiptMapper mapper = new ReceiptMapper();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Receipt receipt = mapper.extractFromResultSet(rs);
                receipt.setEntries(findEntriesFromReceipt(receipt));
                receiptList.add(receipt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receiptList;
    }

    private void createEntries(List<ReceiptEntry> entries) throws SQLException {
        final String query = "INSERT INTO receipt_entry (id_receipt,id_goods,amount,price) VALUES(?,?,?,?)";
        try(PreparedStatement statement =  connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            for(ReceiptEntry entry : entries) {
                createEntry(entry,statement);
            }
        }
    }


    private boolean createEntry(ReceiptEntry entry, PreparedStatement statement) throws SQLException {
        statement.setLong(1,entry.getReceipt().getId());
        statement.setLong(2,entry.getGoods().getId());
        statement.setInt(3,entry.getAmount());
        statement.setInt(4,entry.getPrice());

        return statement.execute();
    }


    private void setId(Receipt receipt, Statement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            receipt.setId(generatedKeys.getLong(1));
        }
    }


}

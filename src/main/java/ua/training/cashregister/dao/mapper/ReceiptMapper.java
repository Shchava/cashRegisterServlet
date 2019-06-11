package ua.training.cashregister.dao.mapper;

import ua.training.cashregister.entity.Receipt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReceiptMapper implements ObjectMapper<Receipt> {

    @Override
    public Receipt extractFromResultSet(ResultSet rs) throws SQLException {
        UserMapper cashier = new UserMapper();
        Receipt receipt = new Receipt();
        receipt.setId_receipt(rs.getInt("id_receipt"));
        receipt.setCreated (LocalDateTime.from(rs.getTimestamp ("created").toLocalDateTime()));
        receipt.setCashier(cashier.extractFromResultSet(rs));
        return receipt;
    }
}

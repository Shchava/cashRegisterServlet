package ua.training.cashregister.dao.mapper;

import ua.training.cashregister.entity.Receipt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ReceiptMapper implements ObjectMapper<Receipt> {

    @Override
    public Receipt extractFromResultSet(ResultSet rs) throws SQLException {
        UserMapper cashier = new UserMapper();
        Receipt receipt = new Receipt();
        receipt.setId_receipt(rs.getInt("id_receipt"));
        Timestamp stamp = rs.getTimestamp ("created");
        if(stamp != null) {
            receipt.setCreated(LocalDateTime.from(stamp.toLocalDateTime()));
        }
        receipt.setCashier(cashier.extractFromResultSet(rs));
        return receipt;
    }
}

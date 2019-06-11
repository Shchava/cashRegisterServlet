package ua.training.cashregister.dao.mapper;

import ua.training.cashregister.entity.ReceiptEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptEntryMapper implements ObjectMapper<ReceiptEntry>{
    @Override
    public ReceiptEntry extractFromResultSet(ResultSet rs) throws SQLException {
        GoodsMapper goodsMapper = new GoodsMapper();
        ReceiptEntry entry = new ReceiptEntry();
        entry.setGoods(goodsMapper.extractFromResultSet(rs));
        entry.setAmount(rs.getInt("amount"));
        entry.setPrice(rs.getInt("price"));
        return entry;
    }
}

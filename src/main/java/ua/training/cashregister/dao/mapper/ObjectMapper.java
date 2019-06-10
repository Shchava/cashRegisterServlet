package ua.training.cashregister.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface ObjectMapper<T> {

    public T extractFromResultSet(ResultSet rs) throws SQLException;
}

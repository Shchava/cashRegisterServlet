package ua.training.cashregister.dao.mapper;

import ua.training.cashregister.entity.Roles;
import ua.training.cashregister.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id_user"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password_hash"));
        user.setRole(Roles.values()[rs.getInt("role")]);
        return user;
    }
}

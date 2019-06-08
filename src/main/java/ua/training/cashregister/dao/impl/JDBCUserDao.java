package ua.training.cashregister.dao.impl;

import ua.training.cashregister.dao.UserDao;
import ua.training.cashregister.dao.mapper.UserMapper;
import ua.training.cashregister.entity.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(User entity) {
        boolean created = false;
        String query = "INSERT INTO user(username, email, password_hash, role) VALUES(?,?,?,?)";
        try(PreparedStatement statement =  connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,entity.getUsername());
            statement.setString(2,entity.getEmail());
            statement.setString(3,entity.getPassword());
            statement.setInt(4,entity.getRole().ordinal());

            int affected = statement.executeUpdate();
            if(affected == 1){
                getId(entity,statement);
                created = true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        };
        return created;
    }

    @Override
    public List<User> findAll() {
        return  null;
    }

    @Override
    public Optional<User> findById(long id) {
        User user = null;
        UserMapper mapper = new UserMapper();

        String query = "SELECT * FROM user WHERE id_user = " + id +";";
        try(Statement statement =  connection.createStatement()){
            ResultSet result = statement.executeQuery(query);
            if(result.next()){
                user = mapper.extractFromResultSet(result);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        };
        return Optional.ofNullable(user);
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        boolean affected = false;
        String query = "DELETE FROM user WHERE id_user = " + id + ";";
        try(Statement statement =  connection.createStatement()){
            statement.execute(query);
            affected = true;
        }catch (Exception ex){
            ex.printStackTrace();
        };
        return affected;
    }

    @Override
    public void close() {

    }

    private void getId(User user, Statement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            user.setId(generatedKeys.getLong(1));
            System.out.println();
        }
    }
}

package ua.training.cashregister.dao.impl;

import ua.training.cashregister.dao.UserDao;
import ua.training.cashregister.dao.mapper.UserMapper;
import ua.training.cashregister.entity.User;

import java.sql.*;
import java.util.ArrayList;
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
        final String query = "INSERT INTO user(username, email, password_hash, role) VALUES(?,?,?,?)";
        try(PreparedStatement statement =  connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,entity.getUsername());
            statement.setString(2,entity.getEmail());
            statement.setString(3,entity.getPasswordHash());
            statement.setInt(4,entity.getRole().ordinal());

            int affected = statement.executeUpdate();
            if(affected == 1){
                getId(entity,statement);
                created = true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return created;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        UserMapper mapper = new UserMapper();

        final String query = "SELECT * FROM user";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                User user = mapper.extractFromResultSet(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return users;
        }

        return users;
    }

    @Override
    public Optional<User> findById(long id) {
        User user = null;
        UserMapper mapper = new UserMapper();

        final String query = "SELECT * FROM user WHERE id_user = " + id +";";
        try(Statement statement =  connection.createStatement()){
            ResultSet result = statement.executeQuery(query);
            if(result.next()){
                user = mapper.extractFromResultSet(result);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = null;
        UserMapper mapper = new UserMapper();

        final String query = "SELECT * FROM user WHERE username = ?;";
        try(PreparedStatement statement =  connection.prepareStatement(query)){
            statement.setString(1,username);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                user = mapper.extractFromResultSet(result);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean update(User entity) {
        boolean created = false;
        final String query = "UPDATE user SET username = ?, email = ?, password_hash = ?, role = ? WHERE id_user = ?";
        try(PreparedStatement statement =  connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,entity.getUsername());
            statement.setString(2,entity.getEmail());
            statement.setString(3,entity.getPasswordHash());
            statement.setInt(4,entity.getRole().ordinal());
            statement.setLong(5,entity.getId());

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
        final String query = "DELETE FROM user WHERE id_user = " + id + ";";
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

    private void getId(User user, Statement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            user.setId(generatedKeys.getLong(1));
            System.out.println();
        }
    }


}

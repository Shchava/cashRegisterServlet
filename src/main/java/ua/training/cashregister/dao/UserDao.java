package ua.training.cashregister.dao;

import ua.training.cashregister.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByUsername(String username);
}

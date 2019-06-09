package ua.training.cashregister.service.user;

import ua.training.cashregister.dao.DaoFactory;
import ua.training.cashregister.dao.UserDao;
import ua.training.cashregister.entity.User;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class Authentification {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<User> findUser(String username){
        UserDao dao =  daoFactory.createUserDao();
        return dao.findByUsername(username);
    }

    public boolean checkAuthority(String username,String password){
        UserDao dao =  daoFactory.createUserDao();
        AtomicBoolean authority = new AtomicBoolean(false);
        findUser(username).ifPresent(user -> {
            authority.set(checkPassword(password, user.getPassword()));});
        return authority.get();
    }

    private boolean checkPassword(String password,String hash){
        return password.equals(hash);
    }
}

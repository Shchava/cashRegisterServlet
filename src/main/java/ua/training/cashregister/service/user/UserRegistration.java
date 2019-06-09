package ua.training.cashregister.service.user;

import ua.training.cashregister.dao.DaoFactory;
import ua.training.cashregister.entity.Roles;
import ua.training.cashregister.entity.User;

public class UserRegistration {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public boolean registerUser(User user){
        return daoFactory.createUserDao().create(user);
    }

    public User createUser(String username, String email ,String password, Roles role){
        UserAuthentification auth = new UserAuthentification();
        String passwordHash = auth.codePassword(password);
        return new User(username,email,passwordHash,role);
    }
}

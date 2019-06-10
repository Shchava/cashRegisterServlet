package ua.training.cashregister.service.user;

import ua.training.cashregister.dao.DaoFactory;
import ua.training.cashregister.dao.UserDao;
import ua.training.cashregister.entity.User;

import java.util.List;
import java.util.Optional;

public class UserServices {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers(){
        try(UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }
}

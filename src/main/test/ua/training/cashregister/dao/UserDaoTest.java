package ua.training.cashregister.dao;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import ua.training.cashregister.entity.Roles;
import ua.training.cashregister.entity.User;

import static org.junit.Assert.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDaoTest {
    private String name = "test";
    private String email = "test@example.com";
    private String password = "password";
    private Roles role = Roles.CASHIER;
    private static long id;

    UserDao dao = DaoFactory.getInstance().createUserDao();


    @Test
    @Order(1)
    public void testCreate(){
        User user = new User(name,email,password,role);
        dao.create(user);
        User dbUser = dao.findById(user.getId()).get();
        assertEquals(user,dbUser);
        id = user.getId();
    }

//    @Test
//    @Order(2)

    @Test
    @Order(3)
    public void testDelete(){
        assertTrue(dao.delete(id));
        assertFalse(dao.findById(id).isPresent());
    }



}

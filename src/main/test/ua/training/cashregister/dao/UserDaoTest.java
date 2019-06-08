package ua.training.cashregister.dao;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;
import ua.training.cashregister.entity.Roles;
import ua.training.cashregister.entity.User;

import static org.junit.Assert.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {
    private String name = "test";
    private String email = "test@example.com";
    private String password = "password";
    private String newName = "test2";
    private Roles role = Roles.CASHIER;
    private static long id;

    UserDao dao = DaoFactory.getInstance().createUserDao();


    @Test
    @Order(1)
    public void testCreate(){
        User user = new User(name,email,password,role);
        assertTrue(dao.create(user));
        User dbUser = dao.findById(user.getId()).get();
        assertEquals(user,dbUser);
        id = user.getId();
    }

    @Test
    @Order(2)
    public void testUpdate(){
        User user = new User(newName,email,password,role);
        user.setId(id);
        //dao.update(user);
        dao.findById(id).get();
        assertTrue(dao.update(user));
        assertEquals(id,user.getId());
        User dbUser = dao.findById(user.getId()).get();
        assertEquals(user.getUsername(),dbUser.getUsername());
    }


    @AfterAll
    @Test
    @Order(4)
    public void testDelete(){
        assertTrue(dao.delete(id));
        assertFalse(dao.findById(id).isPresent());
    }
}

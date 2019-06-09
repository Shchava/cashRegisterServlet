package ua.training.cashregister.dao;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runners.MethodSorters;
import ua.training.cashregister.entity.Roles;
import ua.training.cashregister.entity.User;

import java.util.List;

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
    private static User user;

    private UserDao dao = DaoFactory.getInstance().createUserDao();

    @Test
    @Order(1)
    public void atestCreateAndFindByID(){
        user = new User(name,email,password,role);
        assertTrue(dao.create(user));
        User dbUser = dao.findById(user.getId()).get();
        assertEquals(user,dbUser);
        id = user.getId();
    }

    @Test
    @Order(2)
    public void btestFindByName(){
        assertEquals(user,dao.findByUsername(name).get());
    }

    @Test
    @Order(3)
    public void testUpdate(){
        user.setUsername(newName);
        //dao.update(user);
        dao.findById(id).get();
        assertTrue(dao.update(user));
        assertEquals(id,user.getId());
        User dbUser = dao.findById(user.getId()).get();
        assertEquals(user.getUsername(),dbUser.getUsername());
    }

    @Test
    @Order(4)
    public void testFindAll(){


        List<User> all =  dao.findAll();
        assertTrue(all.size() > 0);
        assertTrue(all.contains(user));
    }

    @Test
    @Order(5)
    public void wtestDelete(){
        assertTrue(dao.delete(id));
        assertFalse(dao.findById(id).isPresent());
    }

    @Test
    @Order(6)
    public void xtestClose(){
        User user = new User(newName,email,password,role);
        dao.close();
        assertFalse(dao.create(user));
        assertFalse(dao.delete(user.getId()));
    }
}

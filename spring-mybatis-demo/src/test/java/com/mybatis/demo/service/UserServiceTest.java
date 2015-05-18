package com.mybatis.demo.service;

import com.mybatis.demo.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users =  userService.getAllUsers();
        assertNotNull(users);
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = userService.getUserById(1);
        assertNotNull(user);
    }

    @Test
    @Ignore
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setName("test");
        user.setAge(20);
        user.setDate(new Date());
        userService.createUser(user);
    }

    @Test
    @Ignore
    public void testUpdateUser() throws Exception {
        User user = userService.getUserById(1);
        user.setName("updateName");
        userService.updateUser(user);
    }

    @Test
    @Ignore
    public void testDeleteUser() throws Exception {
        User user = new User();
        user.setId(1);
        userService.deleteUser(user);
    }
}
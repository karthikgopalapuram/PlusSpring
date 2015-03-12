package org.karthik.example.spring;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.karthik.example.spring.config.AppConfig;
import org.karthik.example.spring.entities.User;
import org.karthik.example.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Karthik Gopalapuram
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void findAllUsers() {
	List<User> users = userService.findAll();
	assertNotNull(users);
	assertTrue(!users.isEmpty());
    }

    @Test
    public void findUserById() {
	User user = userService.findUserById(1);
	assertNotNull(user);
    }

    @Test
    public void createUser() {
	User user = new User(0, "username", "email", "password", new Date("dob"));
	User savedUser = userService.create(user);
	User newUser = userService.findUserById(savedUser.getId());
	assertEquals("username", newUser.getName());
	assertEquals("email", newUser.getEmail());
    }

}
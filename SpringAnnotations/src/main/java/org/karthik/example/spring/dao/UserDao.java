package org.karthik.example.spring.dao;

import java.util.List;
import org.karthik.example.spring.entities.User;

/**
 * @author Karthik Gopalapuram
 *
 */
public interface UserDao {
    
    public List<User> findAll();

    public User create(User user);

    public User findUserById(int id);

    public User login(String email, String password);
    
}

package org.karthik.example.spring.config;

import org.karthik.example.spring.entities.User;
import org.karthik.example.spring.services.UserService;
import org.karthik.example.spring.web.config.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;



/**
 * @author Karthik Gopalapuram
 *
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	User user = userService.findUserByEmail(userName);
	if (user == null) {
	    throw new UsernameNotFoundException("UserName " + userName + " not found");
	}
	return new SecurityUser(user);
    }
}

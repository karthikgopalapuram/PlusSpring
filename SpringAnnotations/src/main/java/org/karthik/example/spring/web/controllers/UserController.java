package org.karthik.example.spring.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.karthik.example.spring.entities.User;
import org.karthik.example.spring.services.UserService;
import org.karthik.example.spring.web.config.SecurityUser;

/**
 * @author Karthik
 *
 */
@Controller
public class UserController {
    private static UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
	UserController.userService = userService;
    }

    public static User getCurrentUser() {
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if (principal instanceof UserDetails) {
	    String email = ((UserDetails) principal).getUsername();
	    User loginUser = userService.findUserByEmail(email);
	    return new SecurityUser(loginUser);
	}
	return null;
    }
}

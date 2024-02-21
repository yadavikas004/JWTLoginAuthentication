package com.jwt.authentication.controller;

import com.jwt.authentication.entities.User;
import com.jwt.authentication.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class HomeController {
	private final Logger logger = LogManager.getLogger(HomeController.class);

	@Autowired
	private UserService userService;

	//	http://localhost:8081/admin/user
	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal) {
		logger.info("-----------Current User-----------");
		return principal.getName();
	}

	@GetMapping("/user-list")
	public List<User> userList() {
		logger.info("-----------List Of User-----------");
		return userService.getUsers();
	}
}





	


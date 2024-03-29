package com.jwt.authentication.controller;

import java.security.Principal;
import java.util.List;

import com.jwt.authentication.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.authentication.entities.User;
import com.jwt.authentication.services.UserServiceImpl;

@RestController
@RequestMapping("/admin")
public class HomeController {
	private Logger logger = LogManager.getLogger(HomeController.class);
	
	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
	}


}

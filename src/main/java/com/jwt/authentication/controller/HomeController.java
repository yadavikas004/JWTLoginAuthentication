package com.jwt.authentication.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.authentication.entities.User;
import com.jwt.authentication.services.UserServiceImpl;

@RestController
@RequestMapping("/admin")
public class HomeController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	
//	http://localhost:8081/admin/user
	@GetMapping("/user")
	public List<User> getUser() {
		System.out.println("Getting Users");
		return userServiceImpl.getUsers();
	}
	
	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
	}

	@GetMapping("/signin")
	public String login(){
		return "login";
	}

	@GetMapping("/profile")
	public String profile(){
		return "profile";
	}

	@GetMapping("/about")
	public String about(){
		return "about";
	}

	@GetMapping("/index")
	public String index(){
		return "index";
	}
}

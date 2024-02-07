package com.jwt.authentication.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.authentication.entities.User;
import com.jwt.authentication.models.JwtRequest;
import com.jwt.authentication.models.JwtResponse;
import com.jwt.authentication.security.JwtHelper;
import com.jwt.authentication.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtHelper helper;
	
	@Autowired
	private UserService userService;

	private Logger logger = LogManager.getLogger(AuthController.class);

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) throws UsernameNotFoundException {
		this.doAuthenticate(request.getEmail(), request.getPassword());
		UserDetails userDetails = userDetailService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails);
		logger.info("--------token generated--------"+token);
		JwtResponse response = JwtResponse.builder()
				.jwtToken(token)
				.username(userDetails.getUsername()).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void doAuthenticate(String email, String password) {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
		try{
			authenticationManager.authenticate(authenticationToken);
		}catch(BadCredentialsException e) {
			throw new RuntimeException("Invalid Username or Password !!");
		}	
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}
	
	@PostMapping("/create-user")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		try {
			userService.createUser(user);
			return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
		}catch (Exception e){
			return new ResponseEntity<>("Email Already Exist", HttpStatus.BAD_REQUEST);
		}
	}
	
	
}

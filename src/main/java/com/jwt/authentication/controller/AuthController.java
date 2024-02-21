package com.jwt.authentication.controller;

import com.jwt.authentication.dto.UserRegistrationDTO;
import com.jwt.authentication.models.JwtRequest;
import com.jwt.authentication.models.JwtResponse;
import com.jwt.authentication.security.JwtHelper;
import com.jwt.authentication.services.RoleService;
import com.jwt.authentication.services.UserService;
import com.jwt.authentication.entities.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@NoArgsConstructor
public class AuthController {

	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtHelper helper;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	private Logger logger = LogManager.getLogger(AuthController.class);

	@PostMapping("/user-login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) throws UsernameNotFoundException {
		System.out.println("Request Came at Backend Server");
		logger.info("----------user-login-api---------------");
		try {
			this.doAuthenticate(request.getEmail(), request.getPassword());
			UserDetails userDetails = userDetailService.loadUserByUsername(request.getEmail());
			if (request.getEmail()==null){
				System.out.println("This User Doesn't exit in Database!!!");
			}

			String token = this.helper.generateToken(userDetails);
			logger.info("--------token generated--------\n"+token);
			Set<String> roles = roleService.getAllRolesByEmail(request.getEmail()).stream()
					.map(Role::getName)
					.collect(Collectors.toSet());

			JwtResponse response = JwtResponse.builder()
					.jwtToken(token)
					.email(userDetails.getUsername())
					.roles(roles)
					.build();

			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch (AuthenticationException e){
			return new ResponseEntity<>(new JwtResponse(), HttpStatus.UNAUTHORIZED);
		}
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
	public ResponseEntity<String> createUser(@RequestBody UserRegistrationDTO registrationDto) {
		logger.info("--------create-user-api---------");
		try {
			String roleName = registrationDto.getRole();
			userService.createUser(registrationDto, roleName);
			return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>("Role not found or Email already exists", HttpStatus.BAD_REQUEST);
		} catch (Exception e){
			return new ResponseEntity<>("Unexpected error occurred", HttpStatus.BAD_REQUEST);
		}
	}


}

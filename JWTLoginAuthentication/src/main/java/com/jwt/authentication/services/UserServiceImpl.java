package com.jwt.authentication.services;

import java.util.*;

import com.jwt.authentication.controller.AuthController;
import com.jwt.authentication.dto.UserRegistrationDTO;
import com.jwt.authentication.entities.Role;
import com.jwt.authentication.repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.authentication.entities.User;
import com.jwt.authentication.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private final Logger logger = LogManager.getLogger(AuthController.class);
	public List<User> store = new ArrayList<>();
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User createUser(UserRegistrationDTO userRegistrationDTO, String name) {
		try {


			// Check if the email already exists
			if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
				logger.error("Email already exists: {}", userRegistrationDTO.getEmail());
				throw new IllegalArgumentException("Email already exists");
			}

			// Continue with user creation
			User user = new User();
			user.setName(userRegistrationDTO.getName());
			user.setEmail(userRegistrationDTO.getEmail());
			user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
			user.setAbout(userRegistrationDTO.getAbout());

			// Check if the specified role exists
			Role role = roleRepository.findByName(name);
			if (role == null) {
				logger.error("Role not found: {}", name);
				throw new IllegalArgumentException("Role not found: " + name);
			}

			// Set the role for the user
			Set<Role> roles = new HashSet<>();
			roles.add(role);
			user.setRoles(roles);

			// Save the user to the database
			return userRepository.save(user);
		} catch (IllegalArgumentException e) {
			// Handle the exception or rethrow it if needed
			throw e;
		} catch (Exception e) {
			logger.error("Unexpected error occurred", e);
			throw new IllegalArgumentException("Unexpected error occurred");
		}
	}


//	public UserServiceImpl() {
//		store.add(new User(UUID.randomUUID().toString(),"Vikas Yadav","yadavikas004@gmail.com"));
//		store.add(new User(UUID.randomUUID().toString(),"Kareena Yadav","yada9812kareena@gmail.com"));
//		store.add(new User(UUID.randomUUID().toString(),"Harsh Yadav","yada9812harsh@gmail.com"));
//		store.add(new User(UUID.randomUUID().toString(),"Ramrathi Yadav","yadavramrathi088@gmail.com"));
//	}
	

}

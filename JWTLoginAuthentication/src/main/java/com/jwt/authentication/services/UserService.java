package com.jwt.authentication.services;

import java.util.List;

import com.jwt.authentication.dto.UserRegistrationDTO;
import com.jwt.authentication.entities.User;

public interface UserService {

	public List<User> getUsers();

	public User createUser(UserRegistrationDTO userRegistrationDTO,String roleName);

}

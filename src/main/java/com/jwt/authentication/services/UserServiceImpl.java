package com.jwt.authentication.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jwt.authentication.models.User;

@Service
public class UserServiceImpl implements UserService{
	public List<User> store = new ArrayList<>();
	
	public UserServiceImpl() {
		store.add(new User(UUID.randomUUID().toString(),"Vikas Yadav","yadavikas004@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"Kareena Yadav","yada9812kareena@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"Harsh Yadav","yada9812harsh@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"Ramrathi Yadav","yadavramrathi088@gmail.com"));
	}
	
	public List<User> getUsers(){
		return this.store;
	}
}

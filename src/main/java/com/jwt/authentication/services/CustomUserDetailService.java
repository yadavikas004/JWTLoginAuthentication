package com.jwt.authentication.services;

import com.jwt.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// load user from database
//		User user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found with email: "+user.getEmail()));
       return userRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("User not found!!!"));
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());
	}

}

package com.jwt.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class AppConfig {

//	@Bean
//	UserDetailsService userDetailsService() {
//		UserDetails user = User.builder().username("softshop").password(passwordEncoder().encode("softshop"))
//				.roles("ADMIN").build();
//		UserDetails user1 = User.builder().username("openspace").password(passwordEncoder().encode("openspace"))
//				.roles("DEVELOPER").build();
//		return new InMemoryUserDetailsManager(user, user1);
//	}

}

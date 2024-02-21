package com.jwt.authentication.config;

import com.jwt.authentication.security.JwtAuthenticationEntryPoint;
import com.jwt.authentication.security.JwtAuthenticationFilter;
import com.jwt.authentication.security.JwtHelper;
import com.jwt.authentication.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@Component
public class SecurityConfig{

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter authenticationFilter;

	@Autowired
	private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private CustomUserDetailService userDetailsService;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		configuration
		http
				.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/course-list", "/create-course", "/edit-course","/admin-dashboard").hasRole("ADMIN")
                            .requestMatchers("/courses/**").hasRole("ADMIN")
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers("/resources/**", "/auth/**", "/auth/create-user","/logout").permitAll()
                            .anyRequest().authenticated();
                })
				.formLogin(form -> {
                    form
                            .loginPage("/")
                            .successHandler(authenticationSuccessHandler)
                            .loginProcessingUrl("/user-login")
                            .permitAll();
                })
				.logout(logout -> {
					logout
							.deleteCookies("JSESSIONID")
							.invalidateHttpSession(true)
							.clearAuthentication(true)
							.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
							.logoutSuccessUrl("/login?logout")
							.permitAll();
				})
				.exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		System.out.println("AuthenticationConfiguration");
		return builder.getAuthenticationManager();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> {
			web.ignoring().requestMatchers(
					"/*.css",
					"/*.js",
					"/*.json",
					"/*.ico",
					"/*.tff",
					"/*.woff",
					"/*.woff2",
					"/*.eot",
					"/*.otf");
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(false);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}

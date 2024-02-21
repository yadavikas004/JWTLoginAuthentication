package com.jwt.authentication.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub

		// Log details of the unauthorized access
		// This log information is for development/debugging purposes
		System.out.println("Unauthorized access. Request details: " +
				"Method=" + request.getMethod() +
				", URI=" + request.getRequestURI() +
				", Remote Address=" + request.getRemoteAddr());

		// Set the response status to unauthorized
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		// Provide a more detailed error message
		PrintWriter writer = response.getWriter();
		writer.println("Access Denied !! "+authException.getMessage());

	}

}

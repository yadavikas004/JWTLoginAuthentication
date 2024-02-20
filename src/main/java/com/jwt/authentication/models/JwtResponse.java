package com.jwt.authentication.models;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JwtResponse {

	private String jwtToken;
	
	private String email;

	private Collection<String> roles;
}

package com.jwt.authentication.entities;

import java.io.Serial;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "user_table")
public class User implements UserDetails{

	/**
	 * @author Vikas Yadav
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Getter
	private String name;
	
	@Getter
	private String email;
	
	@Getter
	private String password;
	
	@Getter
	private String about;

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAbout(String about) {
		this.about = about;
	}

//	------------------------------------------------

//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(
//			name = "user_roles",
//			joinColumns = @JoinColumn(name = "user_id"),
//			inverseJoinColumns = @JoinColumn(name = "role_id")
//	)
//	private Set<Role> roles;

//	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private List<Role> role;
	private String role;

	//	--------------------------------------------

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role);
		return Arrays.asList(authority);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}

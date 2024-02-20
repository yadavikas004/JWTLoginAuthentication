package com.jwt.authentication.services;

import com.jwt.authentication.entities.Role;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

public interface RoleService {

    public List<Role> getAllRoles();

    public Set<Role> getAllRolesByEmail(String email);
}

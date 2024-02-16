package com.jwt.authentication.services;

import com.jwt.authentication.entities.Role;
import com.jwt.authentication.entities.User;
import com.jwt.authentication.repository.RoleRepository;
import com.jwt.authentication.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Set<Role> getAllRolesByEmail(String email) {
        List<Role> roles = roleRepository.findByUsers_Email(email);
        return new HashSet<>(roles);
    }


}

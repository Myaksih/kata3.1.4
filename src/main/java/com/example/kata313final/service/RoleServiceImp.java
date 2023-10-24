package com.example.kata313final.service;

import com.example.kata313final.model.Role;
import com.example.kata313final.repository.RoleRepository;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;


    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
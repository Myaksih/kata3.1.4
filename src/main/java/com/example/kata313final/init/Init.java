package com.example.kata313final.init;

import com.example.kata313final.model.Role;
import com.example.kata313final.model.User;
import com.example.kata313final.repository.RoleRepository;
import com.example.kata313final.service.UserService;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class Init {

    private final UserService userService;

    private final RoleRepository roleRepository;

    public Init(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        Role role = new Role("ROLE_USER");
        roleRepository.save(role);

        Role role1 = new Role("ROLE_ADMIN");
        roleRepository.save(role1);

        User user = new User();
        user.setName("user");
        user.setLastName("test");
        user.setAge(15L);
        user.setPassword("100");
        user.setRoles(Collections.singletonList(role));
        userService.add(user);

        User user1 = new User();
        user1.setName("kolya");
        user1.setLastName("test");
        user1.setAge(20L);
        user1.setPassword("100");
        user1.setRoles(Collections.singletonList(role1));
        userService.add(user1);
    }
}

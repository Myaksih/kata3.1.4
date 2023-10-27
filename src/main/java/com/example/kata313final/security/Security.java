package com.example.kata313final.security;

import com.example.kata313final.model.Role;
import com.example.kata313final.model.User;
import com.example.kata313final.service.UserService;
import com.example.kata313final.service.UserServiceImp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class Security implements UserDetailsService {

    private final UserService userService;

    public Security(UserService userService) {
        this.userService = userService;
    }

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User `%s` not found", name));
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), mapRolesToAuthority(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthority(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

}

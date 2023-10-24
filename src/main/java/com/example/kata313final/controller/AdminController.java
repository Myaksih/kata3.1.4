package com.example.kata313final.controller;

import com.example.kata313final.model.Role;
import com.example.kata313final.model.User;
import com.example.kata313final.service.RoleService;
import com.example.kata313final.service.RoleServiceImp;
import com.example.kata313final.service.UserService;
import com.example.kata313final.service.UserServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    public AdminController(UserServiceImp userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/")
    public String printUsers(ModelMap model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<User> listOfUsers = userService.getAllUser();
        model.addAttribute("listOfUsers", listOfUsers);
        return "Users";
    }

    @GetMapping("/addNewUser")
    public String showCreateUserForm(ModelMap model) {
        User user = new User();
        Collection<Role> roles = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "new-user-info";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin/";
    }

    @GetMapping("/{id}/edit")
    public String showEditUserForm(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.show(id));
        return "/editUser";
    }

    @PatchMapping("/{id}")
    public String saveEditUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

}
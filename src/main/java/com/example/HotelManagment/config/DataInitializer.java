package com.example.HotelManagment.config;


import com.example.HotelManagment.Entity.Role;
import com.example.HotelManagment.Entity.User;
import com.example.HotelManagment.Service.RoleService;
import com.example.HotelManagment.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create roles if not exist
        Role adminRole = roleService.findByName("ADMIN").orElseGet(() -> roleService.saveRole(new Role(null, "ADMIN")));
        Role userRole = roleService.findByName("USER").orElseGet(() -> roleService.saveRole(new Role(null, "USER")));

        // Create admin user if not exist
        if (userService.findByEmail("admin@gmail.com").isEmpty()) {
            User admin = new User();
            admin.setName("Admin User");
            admin.setEmail("admin@gmail.com");
            admin.setPassword("123"); // For demo purposes, plain text
            admin.setRoles(Set.of(adminRole));
            userService.saveUser(admin);
        }

        // Create demo normal user if not exist
        if (userService.findByEmail("user@gmail.com").isEmpty()) {
            User user = new User();
            user.setName("Normal User");
            user.setEmail("user@gmail.com");
            user.setPassword("123"); // For demo purposes, plain text
            user.setRoles(Set.of(userRole));
            userService.saveUser(user);
        }

        System.out.println("✅ Demo roles and users initialized!");
    }
}

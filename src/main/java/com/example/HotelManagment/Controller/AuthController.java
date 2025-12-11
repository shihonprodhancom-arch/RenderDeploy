package com.example.HotelManagment.Controller;

import com.example.HotelManagment.Dto.LoginResponseDto;
import com.example.HotelManagment.Entity.Role;
import com.example.HotelManagment.Entity.User;
import com.example.HotelManagment.Service.RoleService;
import com.example.HotelManagment.Service.UserService;
import com.example.HotelManagment.Security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, RoleService roleService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.roleService = roleService;
        this.jwtUtil = jwtUtil;
    }

    // Registration
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "User already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        // Assign default role
        Role userRole = roleService.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        user.setRoles(Set.of(userRole));

        User savedUser = userService.saveUser(user);

        // Prepare response
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("success", true);
        successResponse.put("message", "User registered successfully");
        successResponse.put("user", Map.of(
                "id", savedUser.getId(),
                "name", savedUser.getName(),
                "email", savedUser.getEmail(),
                "roles", savedUser.getRoles().stream().map(Role::getName).toList()
        ));

        return ResponseEntity.ok(successResponse);
    }


    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        Optional<User> optionalUser = userService.findByEmail(email);

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
            User user = optionalUser.get();
            String token = jwtUtil.generateToken(email);

            List<String> roles = user.getRoles()
                    .stream()
                    .map(Role::getName)
                    .collect(Collectors.toList());

            LoginResponseDto response = new LoginResponseDto(token, roles, "Login successful");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}


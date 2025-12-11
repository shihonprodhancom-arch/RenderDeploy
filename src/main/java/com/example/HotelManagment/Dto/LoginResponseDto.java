package com.example.HotelManagment.Dto;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponseDto {
    private String token;
    private List<String> roles;
    private String message;

    public LoginResponseDto() {}

    public LoginResponseDto(String token, List<String> roles, String message) {
        this.token = token;
        this.roles = roles;
        this.message = message;
    }
}

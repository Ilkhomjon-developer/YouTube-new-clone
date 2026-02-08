package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record UserCreateDto(@NotBlank(message = "Name cannot be empty") String name,
                            @NotBlank(message = "Surname cannot be empty") String surname,
                            @NotBlank(message = "Email cannot be empty") String email,
                            @NotBlank(message = "Password cannot be empty") String password) {
}

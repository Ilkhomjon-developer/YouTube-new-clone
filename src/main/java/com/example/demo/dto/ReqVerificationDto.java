package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReqVerificationDto(@NotBlank(message = "Email cannot be empty") String email,
                                 @NotNull(message = "Please enter the verification code") Integer code) {
}

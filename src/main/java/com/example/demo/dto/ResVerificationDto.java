package com.example.demo.dto;

import java.time.LocalDateTime;

public record ResVerificationDto(String userId, String name, String surname, String email, LocalDateTime updatedAt) {
}

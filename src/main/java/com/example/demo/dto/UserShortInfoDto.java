package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserShortInfoDto( UUID userId,
                                String name,
                                String surname,
                                String email,
                                Boolean isActive,
                                LocalDateTime createdAt) {
}

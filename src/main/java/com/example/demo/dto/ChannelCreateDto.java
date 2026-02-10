package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChannelCreateDto(
        @NotBlank(message = "Channel name cannot be empty")
        @Size(max = 100)
        String name,

        @Size(max = 5000)
        String description,

        String profileImageUrl,

        String bannerImageUrl,

        @Size(max = 50)
        String customUrl
) {
}

package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChannelResponseDto {

    private Long id;
    private Long ownerId;
    private String name;
    private String description;
    private String profileImageUrl;
    private String bannerImageUrl;
    private String customUrl;
    private Long subscriberCount;
    private Boolean isVerified;
    private Boolean isActive;
    private LocalDateTime createdAt;
}

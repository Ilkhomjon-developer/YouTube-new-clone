package com.example.demo.service;

import com.example.demo.dto.ChannelCreateDto;
import com.example.demo.entity.ChannelEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.AppBadException;
import com.example.demo.repository.ChannelRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    @Transactional
    public ChannelEntity create(ChannelCreateDto dto) {
        // should be jwt current session ownerId
        Long ownerId = 5L;
        UserEntity owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new AppBadException("User not found"));

        if (channelRepository.existsByOwnerId(ownerId)) {
            throw new AppBadException("User already has a channel");
        }

        // Trim once and reuse
        String customUrl = trimOrNull(dto.customUrl());

        if (customUrl != null && channelRepository.existsByCustomUrl(customUrl)) {
            throw new AppBadException("Custom URL already taken: " + customUrl);
        }

        ChannelEntity entity = ChannelEntity.builder()
                .ownerId(ownerId)
                .name(dto.name().trim())
                .description(trimOrNull(dto.description()))
                .profileImageUrl(trimOrNull(dto.profileImageUrl()))
                .bannerImageUrl(trimOrNull(dto.bannerImageUrl()))
                .customUrl(customUrl)
                .build();

        return channelRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public ChannelEntity getById(Long id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new AppBadException("Channel not found"));
    }

    @Transactional(readOnly = true)
    public ChannelEntity getByOwnerId(Long ownerId) {
        return channelRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new AppBadException("Channel not found for this user"));
    }

    @Transactional(readOnly = true)
    public boolean existsByOwnerId(Long ownerId) {
        return channelRepository.existsByOwnerId(ownerId);
    }

    // Helper method to avoid repetition
    private String trimOrNull(String value) {
        return value != null && !value.isBlank() ? value.trim() : null;
    }
}

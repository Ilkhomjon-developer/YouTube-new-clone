package com.example.demo.service;

import com.example.demo.dto.ChannelCreateDto;
import com.example.demo.dto.ChannelResponseDto;
import com.example.demo.entity.ChannelEntity;
import com.example.demo.exception.AppBadException;
import com.example.demo.mapper.ChannelMapper;
import com.example.demo.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final ChannelMapper CHANNEL_MAPPER;

    @Transactional
    public ChannelResponseDto create(ChannelCreateDto dto) {
        // should be jwt current session ownerId
        Long ownerId = 5L;
        if (channelRepository.existsByOwnerId(ownerId)) {
            throw new AppBadException("User already has a channel");
        }
        String customUrl = trimOrNull(dto.customUrl()); // Trim once and reuse
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
        channelRepository.save(entity);
        return CHANNEL_MAPPER.toChannelResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public ChannelResponseDto getById(Long id) {
        ChannelEntity entity = channelRepository.findById(id)
                .orElseThrow(() -> new AppBadException("Channel not found"));
        return CHANNEL_MAPPER.toChannelResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public ChannelResponseDto getByOwnerId(Long ownerId) {
        ChannelEntity entity = channelRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new AppBadException("Channel not found for this user"));
        return CHANNEL_MAPPER.toChannelResponseDto(entity);
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

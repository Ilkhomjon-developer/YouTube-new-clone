package com.example.demo.controller;

import com.example.demo.dto.ChannelCreateDto;
import com.example.demo.dto.ChannelResponseDto;
import com.example.demo.service.ChannelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/channel")
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    public ResponseEntity<ChannelResponseDto> createChannel(@Valid @RequestBody ChannelCreateDto dto){
        return ResponseEntity.ok(channelService.create(dto));
    }
}

package com.example.demo.controller;

import com.example.demo.dto.ChannelCreateDto;
import com.example.demo.dto.ChannelResponseDto;
import com.example.demo.service.ChannelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channel")
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping("/create")
    public ResponseEntity<ChannelResponseDto> createChannel(@Valid @RequestBody ChannelCreateDto dto){
        return ResponseEntity.ok(channelService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChannelResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(channelService.getById(id));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<ChannelResponseDto> getByOwnerId(@PathVariable Long ownerId){
        return ResponseEntity.ok(channelService.getByOwnerId(ownerId));
    }

    @GetMapping("/exists/{ownerId}")
    public ResponseEntity<Boolean> existsByOwnerId(@PathVariable Long ownerId){
        return ResponseEntity.ok(channelService.existsByOwnerId(ownerId));
    }
}

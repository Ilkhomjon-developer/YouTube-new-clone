package com.example.demo.controller;

import com.example.demo.dto.ReqVerificationDto;
import com.example.demo.dto.ResVerificationDto;
import com.example.demo.dto.UserCreateDto;
import com.example.demo.dto.UserShortInfoDto;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/create")
    public ResponseEntity<UserShortInfoDto> createUser(@Valid @RequestBody UserCreateDto dto){
        return ResponseEntity.ok(authService.createUser(dto));
    }

    @PostMapping("/verification")
    public ResponseEntity<ResVerificationDto> verification(@Valid @RequestBody ReqVerificationDto dto){
        return ResponseEntity.ok(authService.verification(dto));
    }
}

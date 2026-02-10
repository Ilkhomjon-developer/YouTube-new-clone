package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public Optional<UserEntity> getUserStatus(String email) {
       return userRepository.getUserStatus(email);
    }

    public Optional<UserEntity> findByEmailAndIsActiveTrue(String email) {
       return userRepository.findByEmailAndIsActiveTrue(email);
    }

    public void updateUserStatus(String email) {
         userRepository.updateUserStatus(email);
    }
}

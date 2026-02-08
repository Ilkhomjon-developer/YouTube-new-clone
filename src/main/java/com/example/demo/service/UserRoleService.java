package com.example.demo.service;

import com.example.demo.entity.UserRoleEntity;
import com.example.demo.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public void saveRole(UserRoleEntity role) {
        userRoleRepository.save(role);
    }
}

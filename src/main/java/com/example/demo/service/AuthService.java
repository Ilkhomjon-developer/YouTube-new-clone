package com.example.demo.service;

import com.example.demo.dto.UserCreateDto;
import com.example.demo.dto.UserShortInfoDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserRoleEntity;
import com.example.demo.enums.UserRoleEnum;
import com.example.demo.enums.UserStatusEnum;
import com.example.demo.exception.EmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final UserRoleService userRoleService;


    public UserShortInfoDto createUser(UserCreateDto dto) {
        if(userService.existsByEmail(dto.email())){
            throw new EmailAlreadyExistsException(dto.email() + " already exists");
        }
        UserEntity user = UserEntity.builder()
                                    .name(dto.name())
                                    .surname(dto.surname())
                                    .email(dto.email())
                                    .password(dto.password())
                                    .status(UserStatusEnum.REGISTERING)
                                    .isActive(true).build();
        UserEntity savedUser = userService.save(user);

        UserRoleEntity role = UserRoleEntity.builder()
                                    .userId(savedUser.getId())
                                    .user(savedUser)
                                    .role(UserRoleEnum.ROLE_USER).build();
        userRoleService.saveRole(role);
        return mapUserEntityToShortInfoDto(savedUser);
    }

    private static UserShortInfoDto mapUserEntityToShortInfoDto(UserEntity savedUser) {
        return new UserShortInfoDto(savedUser.getUuid(),
                savedUser.getName(),
                savedUser.getSurname(),
                savedUser.getEmail(),
                savedUser.getIsActive(),
                savedUser.getCreatedAt());
    }
}

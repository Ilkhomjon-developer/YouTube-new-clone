package com.example.demo.service;

import com.example.demo.dto.ReqVerificationDto;
import com.example.demo.dto.ResVerificationDto;
import com.example.demo.dto.UserCreateDto;
import com.example.demo.dto.UserShortInfoDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserRoleEntity;
import com.example.demo.enums.UserRoleEnum;
import com.example.demo.enums.UserStatusEnum;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VerificationException;
import com.example.demo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final EmailSenderService emailSenderService;
    private final UserMapper USER_MAPPER;
    private final Executor executor = Executors.newFixedThreadPool(2);
    private final EmailHistoryService emailHistoryService;


    public UserShortInfoDto createUser(UserCreateDto dto) {
        Optional<UserEntity> optional = userService.getUserStatus(dto.email());
        if (optional.isPresent()) {
            UserEntity existingUser = optional.get();
            if (handleExistingUserStatus(dto, existingUser)) {
                return USER_MAPPER.toUserShortInfoDto(existingUser);
            }
        }
        return toInfoDto(dto);
    }

    public ResVerificationDto verification(ReqVerificationDto dto) {
        UserEntity user = userService.findByEmailAndIsActiveTrue(dto.email())
                .orElseThrow(() -> new UserNotFoundException("User not found or inactive"));
        Integer codeByEmail = emailHistoryService.getCodeByEmail(dto.email());
        if (codeByEmail == null) {
            throw new VerificationException("Verification code not found");
        }
        if (!dto.code().equals(codeByEmail)) {
            throw new VerificationException("Invalid verification code");
        }
        userService.updateUserStatus(user.getEmail());
        return USER_MAPPER.toResVerificationDto(user);
    }

    private boolean handleExistingUserStatus(UserCreateDto dto, UserEntity entity) {
        UserStatusEnum status = entity.getStatus();
        if (UserStatusEnum.REGISTERING.equals(status)) {
            CompletableFuture.runAsync(() -> emailSenderService.resendEmailCode(dto.email()), executor);
            return true;
        } else if (UserStatusEnum.ACTIVE.equals(status)) {
            throw new EmailAlreadyExistsException(dto.email() + " is already active");
        } else if (UserStatusEnum.BLOCKED.equals(status)) {
            throw new EmailAlreadyExistsException(dto.email() + " is blocked");
        }
        return false;
    }

    private UserShortInfoDto toInfoDto(UserCreateDto dto) {
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
        CompletableFuture.runAsync(() -> emailSenderService.sendEmail(savedUser.getEmail()), executor);
        return USER_MAPPER.toUserShortInfoDto(savedUser);
    }

}

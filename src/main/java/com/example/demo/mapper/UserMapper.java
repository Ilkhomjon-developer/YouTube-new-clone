package com.example.demo.mapper;

import com.example.demo.dto.ResVerificationDto;
import com.example.demo.dto.UserShortInfoDto;
import com.example.demo.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "uuid", target = "userId")
    UserShortInfoDto toUserShortInfoDto(UserEntity entity);

    @Mapping(source = "uuid", target = "userId")
    ResVerificationDto toResVerificationDto(UserEntity entity);
}

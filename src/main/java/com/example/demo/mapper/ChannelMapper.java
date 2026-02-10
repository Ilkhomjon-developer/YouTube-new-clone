package com.example.demo.mapper;

import com.example.demo.dto.ChannelResponseDto;
import com.example.demo.entity.ChannelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChannelMapper {

    ChannelResponseDto toChannelResponseDto(ChannelEntity entity);
}

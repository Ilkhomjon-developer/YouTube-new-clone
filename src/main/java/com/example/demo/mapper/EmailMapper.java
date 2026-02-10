package com.example.demo.mapper;

import com.example.demo.entity.EmailEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    EmailEntity toEmailEntity(String email,String message, Integer code);

}

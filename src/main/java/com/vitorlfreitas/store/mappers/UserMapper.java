package com.vitorlfreitas.store.mappers;

import com.vitorlfreitas.store.dtos.UserDto;
import com.vitorlfreitas.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);
}

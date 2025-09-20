package com.vitorlfreitas.store.mappers;

import com.vitorlfreitas.store.dtos.UserDto;
import com.vitorlfreitas.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
}

package com.vitorlfreitas.store.mappers;

import com.vitorlfreitas.store.dtos.RegisterUserRequest;
import com.vitorlfreitas.store.dtos.UpdateUserRequest;
import com.vitorlfreitas.store.dtos.UserDto;
import com.vitorlfreitas.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // GET
    UserDto toDto(User user);

    // POST
    User toEntity(RegisterUserRequest request);

    // PUT
    void update(UpdateUserRequest request, @MappingTarget User user);
}

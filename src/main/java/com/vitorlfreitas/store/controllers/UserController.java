package com.vitorlfreitas.store.controllers;

import com.vitorlfreitas.store.dtos.UserDto;
import com.vitorlfreitas.store.mappers.UserMapper;
import com.vitorlfreitas.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    // Declares the Mapper
    private final UserMapper userMapper;

    @GetMapping()
    public Iterable<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {

        var user = userRepository.findById(id).orElse(null);

        if (user == null)
            return ResponseEntity.notFound().build();


        return ResponseEntity.ok(userMapper.toDto(user));
    }
}

package com.vitorlfreitas.store.controllers;

import com.vitorlfreitas.store.dtos.UserDto;
import com.vitorlfreitas.store.mappers.UserMapper;
import com.vitorlfreitas.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    // Injects UserRepository dependency for database operations.
    private final UserRepository userRepository;

    // Injects UserMapper dependency for converting entities to DTOs.
    private final UserMapper userMapper;

    // Maps HTTP GET requests to "/users" (base path)
    @GetMapping()
    public Iterable<UserDto> getAllUsers(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sortBy
    ) {

        // If the "sort" parameter is not "name" or "email", default to sorting by "name"
        if (!Set.of("name", "email").contains(sortBy))
            sortBy = "name";

        // Fetches all users from DB sorted by chosen field.
        // Converts List<User> to Stream<User>.
        // Maps each User entity to UserDto using UserMapper.
        // Collects result into a List<UserDto> and returns it.
        return userRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(userMapper::toDto).toList();

    }
    // Maps HTTP GET requests to "/users/{id}" (dynamic path variable for user id).
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {

        // Searches for user in DB by id. If not found, returns null.
        var user = userRepository.findById(id).orElse(null);

        // If user does not exist, return HTTP 404 Not Found.
        if (user == null)
            return ResponseEntity.notFound().build();

        // If found, convert User entity to UserDto and return HTTP 200 OK with body.
        return ResponseEntity.ok(userMapper.toDto(user));

    }
}

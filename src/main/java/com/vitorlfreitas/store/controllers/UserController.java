package com.vitorlfreitas.store.controllers;

import com.vitorlfreitas.store.dtos.ChangePasswordRequest;
import com.vitorlfreitas.store.dtos.RegisterUserRequest;
import com.vitorlfreitas.store.dtos.UpdateUserRequest;
import com.vitorlfreitas.store.dtos.UserDto;
import com.vitorlfreitas.store.mappers.UserMapper;
import com.vitorlfreitas.store.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
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

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody RegisterUserRequest request,
                                              UriComponentsBuilder uriBuilder) {
        // Convert the incoming request DTO (data sent by client) into a User entity
        var user = userMapper.toEntity(request);

        // Save the new user entity in the database
        userRepository.save(user);

        // Convert the saved entity back into a DTO to return to the client
        var userDto = userMapper.toDto(user);

        // Build a URI pointing to the newly created resource (e.g., /users/5)
        var uri = uriBuilder.path("/users/{id}")
                .buildAndExpand(userDto.getId())
                .toUri();

        // Return HTTP 201 Created with the "Location" header set to the new resource URI
        // and the body containing the created User DTO
        return ResponseEntity.created(uri).body(userDto);
    }

    @PutMapping("/{id}")
    // Maps HTTP PUT requests to /users/{id}, used to update an existing user
    public ResponseEntity<UserDto> updateUser(
            @PathVariable(name = "id") Long id,           // Extracts the user ID from the URL
            @RequestBody UpdateUserRequest request) {     // Maps the JSON body into an UpdateUserRequest object

        // Look up the user in the database by ID
        var user = userRepository.findById(id).orElse(null);

        // If no user is found, return HTTP 404 Not Found
        if (user == null) return ResponseEntity.notFound().build();

        // Apply the updates from the request DTO onto the existing user entity
        userMapper.update(request, user);

        // Save the updated entity back into the database
        userRepository.save(user);

        // Convert the updated entity to a DTO and return it with HTTP 200 OK
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        // Look up the user in the database by ID
        var user = userRepository.findById(id).orElse(null);

        // If no user is found, return HTTP 404 Not Found
        if (user == null) return ResponseEntity.notFound().build();

        userRepository.delete(user);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(
        @PathVariable Long id,
        @RequestBody ChangePasswordRequest request
    ) {

        // Look up the user in the database by ID
        var user = userRepository.findById(id).orElse(null);

        // If no user is found, return HTTP 404 Not Found
        if (user == null) return ResponseEntity.notFound().build();

        if (!user.getPassword().equals(request.getOldPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        user.setPassword(request.getNewPassword());
        userRepository.save(user);

        return ResponseEntity.noContent().build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException exception
    ) {

        var errors = new HashMap<String, String>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

}

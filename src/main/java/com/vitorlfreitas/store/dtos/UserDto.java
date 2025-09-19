package com.vitorlfreitas.store.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserDto {

    // Besides JsonIgnore, we have more options,
    // JsonProperty("new name") for renaming a field
    @JsonIgnore // Removes id from being serialized
    private Long id;
    private String name;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields
    private String phoneNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Format data
    private LocalDateTime createdAt;

}

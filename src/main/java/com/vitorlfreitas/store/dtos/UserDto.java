package com.vitorlfreitas.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Getter
public class UserDto {

    private Long id;
    private String name;
    private String email;

}

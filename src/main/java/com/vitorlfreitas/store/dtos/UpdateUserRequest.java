package com.vitorlfreitas.store.dtos;

import lombok.Data;

@Data
public class UpdateUserRequest {

    public String name;
    public String email;
}

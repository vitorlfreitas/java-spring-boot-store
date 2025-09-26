package com.vitorlfreitas.store.mappers;

import com.vitorlfreitas.store.dtos.CartDto;
import com.vitorlfreitas.store.dtos.CartItemDto;
import com.vitorlfreitas.store.entities.Cart;
import com.vitorlfreitas.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}

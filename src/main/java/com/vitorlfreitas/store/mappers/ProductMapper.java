package com.vitorlfreitas.store.mappers;

import com.vitorlfreitas.store.dtos.ProductDto;
import com.vitorlfreitas.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);
}

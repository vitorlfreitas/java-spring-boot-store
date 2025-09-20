package com.vitorlfreitas.store.mappers;

import com.vitorlfreitas.store.dtos.CategoryDto;
import com.vitorlfreitas.store.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);
}

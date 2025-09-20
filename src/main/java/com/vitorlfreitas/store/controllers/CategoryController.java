package com.vitorlfreitas.store.controllers;


import com.vitorlfreitas.store.dtos.CategoryDto;
import com.vitorlfreitas.store.mappers.CategoryMapper;
import com.vitorlfreitas.store.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @GetMapping()
    public Iterable<CategoryDto> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory (
            @PathVariable Byte id
    ){
        var category = categoryRepository.findById(id).orElse(null);

        if (category == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(categoryMapper.toDto(category));
    }

}

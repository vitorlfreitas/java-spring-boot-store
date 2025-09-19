package com.vitorlfreitas.store.repositories;

import com.vitorlfreitas.store.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}
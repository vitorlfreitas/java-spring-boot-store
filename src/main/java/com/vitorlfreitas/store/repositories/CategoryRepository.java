package com.vitorlfreitas.store.repositories;

import com.vitorlfreitas.store.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
}
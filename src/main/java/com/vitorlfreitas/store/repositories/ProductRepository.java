package com.vitorlfreitas.store.repositories;

import com.vitorlfreitas.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
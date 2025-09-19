package com.vitorlfreitas.store.repositories;

import com.vitorlfreitas.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

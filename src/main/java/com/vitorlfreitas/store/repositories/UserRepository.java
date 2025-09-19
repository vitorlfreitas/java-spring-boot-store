package com.vitorlfreitas.store.repositories;

import com.vitorlfreitas.store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}

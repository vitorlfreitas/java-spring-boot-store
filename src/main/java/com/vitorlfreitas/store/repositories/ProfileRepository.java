package com.vitorlfreitas.store.repositories;

import com.vitorlfreitas.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
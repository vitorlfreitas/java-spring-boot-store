package com.vitorlfreitas.store.repositories;

import com.vitorlfreitas.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
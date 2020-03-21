package com.reserveme.repository;

import com.reserveme.model.Store;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StoreRepository extends CrudRepository<Store, UUID> {
    Iterable<Store> findAllByCity(String city);
    Iterable<Store> findAllByPostcode(String postcode);
}

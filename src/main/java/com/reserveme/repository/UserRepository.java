package com.reserveme.repository;

import com.reserveme.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> getUserByPhoneNumber(String phoneNumber);
}

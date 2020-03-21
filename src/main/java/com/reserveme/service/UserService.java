package com.reserveme.service;

import com.reserveme.model.User;
import com.reserveme.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        // TODO: Unify phone number format
        userRepository.save(user);
        log.debug("Saved new user with UUID {}", user.getUuid());
        return user;
    }

    public Optional<User> getUser(UUID uuid) {
        return userRepository.findById(uuid);
    }

    public Optional<User> getUserByPhoneNumber(String phoneNumber) {
        return userRepository.getUserByPhoneNumber(phoneNumber);
    }
}

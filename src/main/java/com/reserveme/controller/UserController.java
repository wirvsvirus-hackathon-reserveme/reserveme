package com.reserveme.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reserveme.model.User;
import com.reserveme.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping(UserController.ENDPOINT)
public class UserController {

    static final String ENDPOINT = "/user";

    private final UserService userService;
    private final ObjectMapper objectMapper;

    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
    }

    /*
        {
            "phoneNumber": "012345678901",
            "password": "XYZ"
        }
    */

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/user/{phoneNumber}")
    public ResponseEntity getUser(@PathVariable("phoneNumber") String phoneNumber) {
        log.info("Received request to get user");
        return ResponseEntity.of(userService.getUserByPhoneNumber(phoneNumber));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody String json) {
        try {
            log.info("Received request to create user from JSON {}", json);
            User user = this.objectMapper.readValue(json, User.class);
            return ResponseEntity.ok(userService.addUser(user));
        } catch (JsonProcessingException e) {
            log.debug("Required JSON attribute(s) missing in {}", json);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some of the required JSON attribute(s) are missing in your request body");
        }
    }

}

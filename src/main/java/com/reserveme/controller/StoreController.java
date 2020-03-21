package com.reserveme.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reserveme.model.Store;
import com.reserveme.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping(StoreController.ENDPOINT)
public class StoreController {

    static final String ENDPOINT = "/store";

    private final StoreService storeService;
    private final ObjectMapper objectMapper;

    public StoreController(StoreService storeService, ObjectMapper objectMapper) {
        this.storeService = storeService;
        this.objectMapper = objectMapper;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
    }

    /*
        Post following json to localhost:8080/store
        {
            "name": "Budnikowski",
            "street": "Hummelsbüttler Hauptstraße",
            "houseNumber": "62",
            "postcode": "22339",
            "city": "Hamburg",
            "reservedCapacity": 5,
            "appointmentCapacity": 10,
            "appointmentForerun": "P1D",
            "appointmentDuration": "PT15M"
        }
    */

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addStore(@RequestBody String json) {
        try {
            log.info("Received request to create store from JSON {}", json);
            Store store = this.objectMapper.readValue(json, Store.class);
            return new ResponseEntity<>(storeService.addStore(store), HttpStatus.ACCEPTED);
        } catch (JsonProcessingException e) {
            log.debug("Required JSON attribute(s) missing in {}", json);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some of the required JSON attribute(s) are missing in your request body");
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/city/{city}")
    public ResponseEntity<Iterable<Store>> getStoresByCity(@PathVariable("city") String city) {
        log.info("Received request to return stores in city {}", city);
        return new ResponseEntity<>(storeService.getStoresByCity(city), HttpStatus.ACCEPTED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/postcode/{postcode}")
    public ResponseEntity<Iterable<Store>> getStoresByPostcode(@PathVariable("postcode") String postcode) {
        log.info("Received request to return stores with postcode {}", postcode);
        return new ResponseEntity<>(storeService.getStoresByPostcode(postcode), HttpStatus.ACCEPTED);
    }
}

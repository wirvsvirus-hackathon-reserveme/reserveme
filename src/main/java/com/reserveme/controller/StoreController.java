package com.reserveme.controller;

import com.reserveme.model.Store;
import com.reserveme.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(StoreController.ENDPOINT)
public class StoreController {

    static final String ENDPOINT = "/store";

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
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

    @PostMapping
    public ResponseEntity<Store> addStore(@RequestBody Store store) {
        log.info("Received request to add store {}", store);
        return new ResponseEntity<>(storeService.addStore(store), HttpStatus.ACCEPTED);
    }
}

package com.reserveme.controller;

import com.reserveme.model.Store;
import com.reserveme.response.StoreAddResponse;
import com.reserveme.service.StoreService;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping
    public StoreAddResponse addStore(@RequestBody Store store) {
        log.info("Received request to add store {}", store);
        return new StoreAddResponse(storeService.addStore(store));
    }
}

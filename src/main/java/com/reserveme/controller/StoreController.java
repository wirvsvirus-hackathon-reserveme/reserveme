package com.reserveme.controller;

import com.reserveme.model.Store;
import com.reserveme.response.StoreAddResponse;
import com.reserveme.service.StoreService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(StoreController.ENDPOINT)
public class StoreController {

    static final String ENDPOINT = "/store";

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public StoreAddResponse addStore(Store store) {
        return new StoreAddResponse(storeService.addStore(store));
    }
}

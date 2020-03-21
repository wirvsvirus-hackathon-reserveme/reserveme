package com.reserveme.service;

import com.reserveme.model.Store;
import com.reserveme.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store addStore(Store store) {
        storeRepository.save(store);
        return store;
    }
}

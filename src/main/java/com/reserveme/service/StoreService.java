package com.reserveme.service;

import com.reserveme.model.Store;
import com.reserveme.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store addStore(Store store) {
        storeRepository.save(store);
        log.debug("Saved new store with UUID {}", store.getUuid());
        return store;
    }

    public Iterable<Store> getStoresByCity(String city) {
        log.debug("Looking up stores in city {}", city);
        return storeRepository.findAllByCity(city);
    }

    public Iterable<Store> getStoresByPostcode(String postcode) {
        log.debug("Looking up stores with postcode {}", postcode);
        return storeRepository.findAllByPostcode(postcode);
    }
}

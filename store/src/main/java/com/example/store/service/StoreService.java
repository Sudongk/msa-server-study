package com.example.store.service;

import com.example.store.domain.request.StoreRequest;
import com.example.store.domain.response.StoreResponse;
import com.example.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public void save(StoreRequest request, UUID ownerId) {
        storeRepository.save(request.toEntity(ownerId));
    }

    public List<StoreResponse> findAllByOwnerId(UUID ownerId) {
        return storeRepository.findAllByOwnerId(ownerId);
    }

    public List<StoreResponse> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(StoreResponse::of)
                .toList();
    }

    public Page<StoreResponse> findAllByLocation(String location, Pageable pageable) {
        return storeRepository.findAllByLocation(location, pageable);
    }

}

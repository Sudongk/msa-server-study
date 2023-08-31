package com.example.ceo.service;

import com.example.ceo.domain.request.OwnerRequest;
import com.example.ceo.domain.response.OwnerResponse;
import com.example.ceo.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public void save(OwnerRequest request) {
        ownerRepository.save(request.toEntity());
    }

    public OwnerResponse findById(UUID ownerId) {
        return OwnerResponse.of(
                ownerRepository.findById(ownerId)
                        .orElseThrow(() -> new IllegalArgumentException("NO CEO FOUND"))
        );
    }

}

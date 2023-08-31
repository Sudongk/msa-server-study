package com.example.ceo.domain.controller;

import com.example.ceo.domain.request.OwnerRequest;
import com.example.ceo.domain.response.OwnerResponse;
import com.example.ceo.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody OwnerRequest request) {
        ownerService.save(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> findById(@PathVariable("id") UUID ownerId) {
        OwnerResponse response = ownerService.findById(ownerId);
        return ResponseEntity.ok(response);
    }

}

package com.example.store.controller;

import com.example.store.config.JwtService;
import com.example.store.config.TokenInfo;
import com.example.store.domain.request.StoreRequest;
import com.example.store.domain.response.StoreResponse;
import com.example.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
public class StoreController {

    private final StoreService storeService;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody StoreRequest request,
            @RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(token.replace("Bearer ", ""));
        storeService.save(request, tokenInfo.getId());
    }

    @GetMapping()
    public ResponseEntity<List<StoreResponse>> findAll() {
        List<StoreResponse> responses = storeService.findAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/owner")
    public ResponseEntity<List<StoreResponse>> findAllByOwnerId(@RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(token.replace("Bearer ", ""));
        List<StoreResponse> response = storeService.findAllByOwnerId(tokenInfo.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/location")
    public ResponseEntity<Page<StoreResponse>> findAllByLocation(
            @RequestParam(name = "location") String location,
            @RequestParam(required = false, defaultValue = "0", name = "page") Integer page,
            @RequestParam(required = false, defaultValue = "10", name = "size") Integer size
    ) {
        Page<StoreResponse> response = storeService.findAllByLocation(location, PageRequest.of(page, size));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public String test() {
        return "service-1";
    }
}

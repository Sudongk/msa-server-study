package com.example.auth.api;

import com.example.auth.client.request.OwnerRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "CEO-SERVICE")
public interface OwnerClient {

    @PostMapping("/api/v1/owner")
    ResponseEntity<Void> saveOwner(OwnerRequest ownerRequest);

}

package com.example.customer.controller;

import com.example.customer.config.JwtService;
import com.example.customer.config.TokenInfo;
import com.example.customer.domain.entity.Customer;
import com.example.customer.domain.request.CustomerRequest;
import com.example.customer.domain.request.SignupRequest;
import com.example.customer.domain.response.SignupResponse;
import com.example.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final JwtService jwtService;

    @PostMapping("/check")
    public ResponseEntity<SignupResponse> check(
            @RequestHeader("Authorization") String token
    ) {
        TokenInfo tokenInfo = jwtService.parseToken(token.replace("Bearer ", ""));
        SignupResponse response = customerService.checkSignup(tokenInfo);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody CustomerRequest request) {
        customerService.save(request);
    }

    @GetMapping("/me")
    public ResponseEntity<Customer> getMe(@RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(token.replace("Bearer ", ""));
        Customer customer = customerService.getMe(tokenInfo);

        return ResponseEntity.ok(customer);
    }

    @GetMapping("/me/{id}")
    public ResponseEntity<Customer> getMeByToken(@PathVariable String id) {
        Customer customer = customerService.getMeById(UUID.fromString(id));

        return ResponseEntity.ok(customer);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(
            @RequestBody SignupRequest request,
            @RequestHeader("Authorization") String token
    ) {
        TokenInfo tokenInfo = jwtService.parseToken(token.replace("Bearer ", ""));
        SignupResponse response = customerService.signup(request, tokenInfo);

        return ResponseEntity.ok(response);
    }

}

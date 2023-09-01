package com.example.pay.controller;

import com.example.pay.config.JwtService;
import com.example.pay.config.TokenInfo;
import com.example.pay.domain.request.PaymentRequest;
import com.example.pay.domain.response.PaymentResponse;
import com.example.pay.domain.response.PaymentResponses;
import com.example.pay.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pay")
public class PaymentController {

    private final PaymentService paymentService;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody PaymentRequest request,
            @RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(token.replace("Bearer ", ""));
        paymentService.save(request, tokenInfo);
    }

    @GetMapping
    public ResponseEntity<PaymentResponses> getAllPayment() {
        PaymentResponses response = paymentService.getAllPayment();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<PaymentResponses> getMyPayment(@RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(token.replace("Bearer ", ""));
        PaymentResponses response = paymentService.getMyPayment(tokenInfo);

        return ResponseEntity.ok(response);
    }

}

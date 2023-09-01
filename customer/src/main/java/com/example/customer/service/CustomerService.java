package com.example.customer.service;

import com.example.customer.config.TokenInfo;
import com.example.customer.domain.entity.Customer;
import com.example.customer.domain.request.CustomerRequest;
import com.example.customer.domain.request.SignupRequest;
import com.example.customer.domain.response.SignupResponse;
import com.example.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Customer save(CustomerRequest request) {
        return customerRepository.save(request.toEntity());
    }

    public SignupResponse checkSignup(TokenInfo tokenInfo) {
        Customer customer = getByToken(tokenInfo);

        return SignupResponse.builder()
                .customer(customer)
                .redirectUrl(customer.getAddress() == null ? "/signup" : "/main")
                .build();
    }

    @Transactional
    public SignupResponse signup(SignupRequest request, TokenInfo tokenInfo) {
        Customer customer = getByToken(tokenInfo);
        customer.setAddress(request.getAddress());

        return SignupResponse.builder()
                .customer(customer)
                .redirectUrl("/main")
                .build();
    }

    public Customer getMe(TokenInfo tokenInfo) {
        return customerRepository
                .findById(tokenInfo.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객입니다."));
    }

    public Customer getMeById(UUID id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객입니다."));
    }

    private Customer getByToken(TokenInfo tokenInfo) {
        UUID id = tokenInfo.getId();

        return customerRepository
                .findById(id)
                .orElseGet(
                        () -> save(new CustomerRequest(id, tokenInfo.getName(), tokenInfo.getNumber()))
                );
    }
}

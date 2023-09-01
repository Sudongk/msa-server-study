package com.example.pay.service;

import com.example.pay.api.CustomerClient;
import com.example.pay.api.MenuClient;
import com.example.pay.config.TokenInfo;
import com.example.pay.domain.dto.Customer;
import com.example.pay.domain.entity.Payment;
import com.example.pay.domain.request.PaymentRequest;
import com.example.pay.domain.response.MenuResponse;
import com.example.pay.domain.response.PaymentResponse;
import com.example.pay.domain.response.PaymentResponses;
import com.example.pay.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CustomerClient customerClient;
    private final MenuClient menuClient;

    // 결제 등록
    @Transactional
    public void save(PaymentRequest request, TokenInfo tokenInfo) {
        paymentRepository.save(request.toEntity(tokenInfo));
    }

    // 내 결제내역 조회
    public PaymentResponses getMyPayment(TokenInfo tokenInfo) {
        List<PaymentResponse> paymentResponses = new ArrayList<>();

        List<Payment> payments = paymentRepository.findByCustomerId(tokenInfo.getId());

        for (Payment payment : payments){
            Long storeId = payment.getStoreId();
            String menuIds = payment.getMenuIds();
            ResponseEntity<Customer> customer = customerClient.getMeByToken(tokenInfo.getId().toString());
            ResponseEntity<List<MenuResponse>> MenuResponses = menuClient.findAllByStoreIdAndInMenuIds(storeId, menuIds);

            paymentResponses.add(new PaymentResponse(payment, MenuResponses.getBody(), customer.getBody()));
        }

        return PaymentResponses.of(paymentResponses);
    }

    // 전체 결제내역 조회
    public PaymentResponses getAllPayment() {
        List<PaymentResponse> paymentResponses = new ArrayList<>();

        List<Payment> payments = paymentRepository.findAll();

        for (Payment payment : payments){
            Long storeId = payment.getStoreId();
            String menuIds = payment.getMenuIds();
            ResponseEntity<Customer> customer = customerClient.getMeByToken(payment.getCustomerId().toString());
            ResponseEntity<List<MenuResponse>> MenuResponses = menuClient.findAllByStoreIdAndInMenuIds(storeId, menuIds);

            paymentResponses.add(new PaymentResponse(payment, MenuResponses.getBody(), customer.getBody()));
        }

        return PaymentResponses.of(paymentResponses);
    }
}

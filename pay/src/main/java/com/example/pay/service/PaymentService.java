package com.example.pay.service;

import com.example.pay.api.CustomerClient;
import com.example.pay.api.MenuClient;
import com.example.pay.api.OrderCommandClient;
import com.example.pay.config.TokenInfo;
import com.example.pay.domain.dto.Customer;
import com.example.pay.domain.entity.Payment;
import com.example.pay.domain.kafka.OrderKafkaData;
import com.example.pay.domain.request.OrderRequest;
import com.example.pay.domain.request.PaymentRequest;
import com.example.pay.domain.response.MenuResponse;
import com.example.pay.domain.response.PaymentResponse;
import com.example.pay.domain.response.PaymentResponses;
import com.example.pay.kafka.OrderCommandProducer;
import com.example.pay.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CustomerClient customerClient;
    private final MenuClient menuClient;
    private final OrderCommandClient orderCommandClient;
    private final OrderCommandProducer orderCommandProducer;

    // 결제 등록
    @Transactional
    public void save(PaymentRequest request, TokenInfo tokenInfo) {
        paymentRepository.save(request.toEntity(tokenInfo));

        List<MenuResponse> menuResponses = getMenus(request.getStoreId(), request.getMenuIds());

        int totalPrice = menuResponses
                .stream()
                .map(MenuResponse::getPrice) // 각 MenuResponse 객체의 price 필드 추출
                .mapToInt(Integer::intValue) // Integer를 int로 변환
                .sum();

        // FeignClient를 사용하여 외부 서버와의 통신
//        orderCommandClient(request, tokenInfo, totalPrice);

        // FeignClient 대신 Kafka를 사용하여 외부 서버와의 통신
        orderCommandProducer.send(new OrderKafkaData(request.getStoreId(), tokenInfo.getId().toString(), totalPrice));
    }

    // 내 결제내역 조회
    public PaymentResponses getMyPayment(TokenInfo tokenInfo) {
        List<PaymentResponse> paymentResponses = new ArrayList<>();

        List<Payment> payments = paymentRepository.findByCustomerId(tokenInfo.getId());

        for (Payment payment : payments){
            Long storeId = payment.getStoreId();
            String menuIds = payment.getMenuIds();
            Customer customer = getCustomer(tokenInfo);
            List<MenuResponse> menuResponses = getMenus(storeId, menuIds);

            paymentResponses.add(new PaymentResponse(payment, menuResponses, customer));
        }

        return PaymentResponses.of(paymentResponses);
    }

    private List<MenuResponse> getMenus(Long storeId, String menuIds) {
        return menuClient.findAllByStoreIdAndInMenuIds(storeId, menuIds).getBody();
    }

    private Customer getCustomer(TokenInfo tokenInfo) {
        return customerClient.getMeByToken(tokenInfo.getId().toString()).getBody();
    }

    private void orderCommandClient(PaymentRequest request, TokenInfo tokenInfo, int totalPrice) {
        orderCommandClient.save(
                new OrderRequest(
                        request.getStoreId(),
                        tokenInfo.getId().toString(),
                        totalPrice
                )
        );
    }

}

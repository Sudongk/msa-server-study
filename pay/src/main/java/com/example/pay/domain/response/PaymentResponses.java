package com.example.pay.domain.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PaymentResponses {

    List<PaymentResponse> paymentResponses;

    public static PaymentResponses of(List<PaymentResponse> paymentResponses) {
        return new PaymentResponses(paymentResponses);
    }

}

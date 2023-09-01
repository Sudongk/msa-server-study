package com.example.pay.repository;

import com.example.pay.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByCustomerId(UUID customerId);

}

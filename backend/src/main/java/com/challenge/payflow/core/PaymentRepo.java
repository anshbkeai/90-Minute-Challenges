package com.challenge.payflow.core;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository 
public interface PaymentRepo extends  JpaRepository<Payment,UUID>{

    Optional<Payment> findByPaymentKey(String paymentKey);

    
}

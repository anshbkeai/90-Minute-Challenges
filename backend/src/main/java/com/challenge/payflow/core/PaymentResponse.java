package com.challenge.payflow.core;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class PaymentResponse {

    private UUID paymentId; 

    private  BigDecimal amount;

    private String merchantId;

    private String currency ;
    private Instant createdAt;
    private PaymentStatus paymentStatus;

    
    public UUID getPaymentId() {
        return paymentId;
    }


    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }


    public BigDecimal getAmount() {
        return amount;
    }


    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public String getMerchantId() {
        return merchantId;
    }


    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public Instant getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }


    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }


    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    public PaymentResponse(UUID paymentId, BigDecimal amount, String merchantId, String currency, Instant createdAt,
            PaymentStatus paymentStatus) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.merchantId = merchantId;
        this.currency = currency;
        this.createdAt = createdAt;
        this.paymentStatus = paymentStatus;
    }


    public static PaymentResponse convert(Payment payment) {
        return new  PaymentResponse(payment.getPaymentId(), payment.getAmount(), payment.getMerchantId(), payment.getCurrency(), 
                                        payment.getCreatedAt(), payment.getPaymentStatus());
    }
}

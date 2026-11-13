package com.challenge.payflow.core;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "payments",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"merchantId", "paymentKey"})
    }
)
public class Payment {

    @Id 
    private UUID paymentId; 

    @Column(nullable = false, precision = 19, scale = 2)
    private  BigDecimal amount;

    private String merchantId;

    private String currency ;
    private String paymentKey;

    private String requestFingerPrint; // u can have the column contast for so . using sha as we can see about the change in the system like the JSON values 

    private Instant createdAt;
    private  Instant updatedAt;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    public BigDecimal getAmount() {
        return amount;
    }
    public Payment() {
    }
    public Payment(UUID paymentId, BigDecimal amount, String merchantId, String currency, String paymentKey,
            Instant createdAt, Instant updatedAt , String requestFingerPrint) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.merchantId = merchantId;
        this.currency = currency;
        this.paymentKey = paymentKey;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.requestFingerPrint = requestFingerPrint;
    }
    public String getRequestFingerPrint() {
        return requestFingerPrint;
    }
    public UUID getPaymentId() {
        return paymentId;
    }
    public String getMerchantId() {
        return merchantId;
    }
    public String getCurrency() {
        return currency;
    }
    public String getPaymentKey() {
        return paymentKey;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public Instant getUpdatedAt() {
        return updatedAt;
    }
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    // Pending -> Auth 
    public void authorize() {
        if(paymentStatus == PaymentStatus.PENDING) {
            this.paymentStatus = PaymentStatus.AUTHORIZED;
        }
        else {
            throw new RuntimeException("Illegal Operation " + PaymentStatus.AUTHORIZED.toString());
        }
    }

    public void capture() {
        if(paymentStatus == PaymentStatus.AUTHORIZED) {
            this.paymentStatus = PaymentStatus.CAPTURED;
        }
        else {
            throw new RuntimeException("Illegal Operation of " + PaymentStatus.CAPTURED.toString());
        }
    }

    public  void decline() {
        if(paymentStatus != PaymentStatus.DECLINED) {
            this.paymentStatus = PaymentStatus.DECLINED;
        }
        else {
            throw new RuntimeException("Illegal Operation of " + PaymentStatus.CAPTURED.toString());
        }
    }

    
}

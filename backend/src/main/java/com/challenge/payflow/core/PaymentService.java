package com.challenge.payflow.core;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service 

public class PaymentService {
    private final PaymentRepo paymentRepo;
    private final AcquirerService acquirerService;

    public PaymentService(PaymentRepo paymentRepo , @Lazy AcquirerService acquirerService) {
        this.paymentRepo = paymentRepo;
        this.acquirerService = acquirerService;
    }

    @Transactional 
    public PaymentResponse create(CreatePayment createPayment , String paymentKey) {
        String fingerPrint = UtilsService.genrateFingerPrint(createPayment);
        Optional<Payment> optional = paymentRepo.findByPaymentKey(paymentKey);  
        if(optional.isPresent() ){
            String existingFingerPrint = optional.get().getRequestFingerPrint();
            if(fingerPrint.equals(existingFingerPrint)) {
                // conflict rfighjt 
                throw new IllegalStateException("HTTP 409");
            }
            else {
                return PaymentResponse.convert(optional.get());
            }
        }
        Payment payment = new Payment(UUID.randomUUID()
                            , createPayment.amount(), createPayment.merchantId(), 
                            createPayment.currency(),paymentKey  , Instant.now()  , Instant.now() , fingerPrint);
        payment.setPaymentStatus(PaymentStatus.PENDING);        
        // hey save this payment and then do the steps . accoring

        // service will not do that like it will be done by payment object 

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.print("Testing Thread" );
            acquirerService.authorizeAndCapture(payment);
        });

        t1.start();

        return PaymentResponse.convert(paymentRepo.save(payment));

    }

    public PaymentResponse getPayment(UUID id) {
        return paymentRepo.findById(id).map(PaymentResponse::convert).orElseThrow(() -> new RuntimeException());
    }

    void doWork() {
        paymentRepo.findAll().stream().filter(x -> x.getPaymentStatus().equals(PaymentStatus.PENDING)).toList();
    }


    @Transactional
    public void updateAndSavePaymentState(Payment payment ) {
        paymentRepo.save(payment);
    }

    // so

}

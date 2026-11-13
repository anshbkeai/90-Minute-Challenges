package com.challenge.payflow.core;

import org.springframework.stereotype.Service;

@Service 
public class AcquirerService {

    // form the Thread will trigger his action say 
    private final PaymentService paymentService;
    

    public AcquirerService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    public void authorizeAndCapture(Payment payment) {
        // Either u authoroze the payment . so like we will update the payment . Right .

        // as u come up will 
        int val = (int)(Math.random() * 100);

        if(val <= 15) {
            // fail the request . 
            payment.decline();
            paymentService.updateAndSavePaymentState(payment);
            throw new RuntimeException(payment.toString());
        }
        else {
            try {
                payment.authorize();
                paymentService.updateAndSavePaymentState(payment);

            }catch(RuntimeException e) {
                payment.decline();
                paymentService.updateAndSavePaymentState(payment);
                return ; 
            }

            // now u ask the merchant to Cpautor 

            try {
                int sleep = (int)(Math.random() * 1000);
                System.out.println("Sleep for " + sleep);
                Thread.currentThread();
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // merchant will caputie . 

            payment.capture();
            paymentService.updateAndSavePaymentState(payment);
        }
    }
}

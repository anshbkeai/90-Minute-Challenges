package com.challenge.payflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.challenge.payflow.core")
public class PayflowApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayflowApplication.class, args);
    }
}

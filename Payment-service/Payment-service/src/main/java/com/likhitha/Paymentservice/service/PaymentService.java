package com.likhitha.Paymentservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.likhitha.Paymentservice.entity.Payment;
import com.likhitha.Paymentservice.repository.PaymentRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;



    public Payment doPayment(Payment payment)  {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return repository.save(payment);
    }

    public String paymentProcessing(){
        //api should be 3rd party payment gateway (paypal,paytm...)
        return new Random().nextBoolean()?"success":"false";
    }

    public Payment findPaymentHistoryByOrderId(int orderId) {
        Payment payment=repository.findByOrderId(orderId);
        return payment ;
    }
}
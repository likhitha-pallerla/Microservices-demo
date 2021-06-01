package com.likhitha.Paymentservice.controller;

import com.likhitha.Paymentservice.entity.Payment;
import com.likhitha.Paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment) {
        return service.doPayment(payment);
    }

    public String paymentProcessing(){
        //api should be 3rd party payment gateway (paypal, paytm..)
        return new Random().nextBoolean()?"success":"false";
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable int orderId) {
        return service.findPaymentHistoryByOrderId(orderId);
    }

}

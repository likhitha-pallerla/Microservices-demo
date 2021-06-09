package com.likhitha.OrderService.service;

import com.likhitha.OrderService.common.Payment;
import com.likhitha.OrderService.common.TransactionRequest;
import com.likhitha.OrderService.common.TransactionResponse;
import com.likhitha.OrderService.entity.Order;
import com.likhitha.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order createOrder(Order order){
        return repository.save(order);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order saveOrder(Order order){
        return repository.save(order);
    }



    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest request)  {
        String response = "";

        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderid(order.getId());
        payment.setAmount(order.getPrice());

        //rest call
        Payment paymentResponse = restTemplate.postForObject("http://localhost:9092/payment/dopayment", payment, Payment.class);
        System.out.println("Here:"+ paymentResponse);

        response = paymentResponse.getPaymentStatus().equals("success") ? "Payment processing successful and order placed" : "There is a failure in payment api, Order added to cart";
        System.out.println("Order:"+order);
        repository.save(order);
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
    }

}

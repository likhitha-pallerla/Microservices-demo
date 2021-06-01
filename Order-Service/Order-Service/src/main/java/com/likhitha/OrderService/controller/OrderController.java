package com.likhitha.OrderService.controller;

import com.likhitha.OrderService.common.TransactionRequest;
import com.likhitha.OrderService.common.TransactionResponse;
import com.likhitha.OrderService.entity.Order;
import com.likhitha.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/message")
    public String test(){
        return "testing, if this is working i will be happy else sad";
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders(){
        return service.getAllOrders();
    }

    @PostMapping("/create")
    public Order create(@RequestBody Order order){
        return service.createOrder(order);
    }

//    @PostMapping("/bookOrder")
//    public Order bookOrder(@RequestBody Order order) {
//        return service.saveOrder(order);
//    }

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) {
       return service.saveOrder(request);
       // return null;
    }
}

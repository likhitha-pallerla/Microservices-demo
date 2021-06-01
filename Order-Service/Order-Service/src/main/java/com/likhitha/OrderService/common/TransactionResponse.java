package com.likhitha.OrderService.common;

import com.likhitha.OrderService.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data


public class TransactionResponse {
    private Order order;
    private double amount;
    private String transactionId;
    private String message;

    public TransactionResponse (Order order, double amount, String transactionId, String message)
    {
        this.order = order;
        this.amount=amount;
        this.transactionId= transactionId;
        this.message=message;
    }
}

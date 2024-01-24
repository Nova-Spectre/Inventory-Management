package com.shubham.dev.orderservice.controller;

import com.shubham.dev.orderservice.dto.OrderRequest;
import com.shubham.dev.orderservice.model.OrderLineItem;
import com.shubham.dev.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.PlaceOrder(orderRequest);
        return "Order Placed Successfully";

    }

}

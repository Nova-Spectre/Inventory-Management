package com.shubham.dev.orderservice.controller;

import com.shubham.dev.orderservice.dto.OrderRequest;
import com.shubham.dev.orderservice.model.OrderLineItem;
import com.shubham.dev.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="inventory",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name="inventory") //same name as circuitbreaker
    @Retry(name="inventory")//same name as circuitbreaker which is defined in application.properties (inventory)
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
        return CompletableFuture.supplyAsync(()->orderService.PlaceOrder(orderRequest));

    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest , RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Oops! Something went wrong , Please order after some time!");
    }

}

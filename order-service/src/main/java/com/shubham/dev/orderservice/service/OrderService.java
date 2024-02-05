package com.shubham.dev.orderservice.service;

import com.shubham.dev.orderservice.dto.InventoryResponse;
import com.shubham.dev.orderservice.dto.OrderLineItemDto;
import com.shubham.dev.orderservice.dto.OrderRequest;
import com.shubham.dev.orderservice.model.Order;
import com.shubham.dev.orderservice.model.OrderLineItem;
import com.shubham.dev.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.border.Border;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    
    private final WebClient.Builder webClientBuilder;





    public String PlaceOrder(OrderRequest orderRequest){

        System.out.println("OrderRequest: " + orderRequest);
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> OrderLineItem=orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::maptodto).toList();
        order.setOrderLineItemList(OrderLineItem);

        List<String> skucodes = order.getOrderLineItemList()
                .stream().map(orderLineItem -> orderLineItem.getSkuCode()).toList();


        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri(UriComponentsBuilder.fromUriString("http://inventory-service/api/inventory")
                .queryParam("skuCode", skucodes)
                .build()
                .toUriString())
                .retrieve().bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductisInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);




        if(allProductisInStock){
            orderRepository.save(order);
            return "Order Placed Successfully";

        }else{
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }




    }


    private OrderLineItem maptodto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderlineitem=new OrderLineItem();

        orderlineitem.setPrice(orderLineItemDto.getPrice());
        orderlineitem.setSkuCode(orderLineItemDto.getSkuCode());
        orderlineitem.setQuantity(orderLineItemDto.getQuantity());

        return orderlineitem;
    }
}

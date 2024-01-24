package com.shubham.dev.orderservice.service;

import com.shubham.dev.orderservice.dto.OrderLineItemDto;
import com.shubham.dev.orderservice.dto.OrderRequest;
import com.shubham.dev.orderservice.model.Order;
import com.shubham.dev.orderservice.model.OrderLineItem;
import com.shubham.dev.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.border.Border;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;





    public void PlaceOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> OrderLineItem=orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::maptodto).toList();
        order.setOrderLineItemList(OrderLineItem);

        orderRepository.save(order);

    }


    private OrderLineItem maptodto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderlineitem=new OrderLineItem();

        orderlineitem.setPrice(orderLineItemDto.getPrice());
        orderlineitem.setSkuCode(orderLineItemDto.getSkuCode());
        orderlineitem.setQuatity(orderLineItemDto.getQuatity());

        return orderlineitem;
    }
}

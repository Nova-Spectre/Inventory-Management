package com.shubham.dev.orderservice.repository;

import com.shubham.dev.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}

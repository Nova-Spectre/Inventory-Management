package com.shubham.dev.orderservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderLineItemDto {
    private long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

}

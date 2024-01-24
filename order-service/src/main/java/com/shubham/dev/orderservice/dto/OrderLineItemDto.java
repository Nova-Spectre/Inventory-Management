package com.shubham.dev.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemDto {
    private long id;
    private String SkuCode;
    private BigDecimal price;
    private Integer quatity;

}

package com.shubham.dev.Productservice.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class productResponse {

    private String id;

    private String name;
    private String description;
    private BigDecimal price;
}

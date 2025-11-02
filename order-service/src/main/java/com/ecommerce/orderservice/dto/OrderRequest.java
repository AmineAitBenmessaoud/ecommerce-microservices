package com.ecommerce.orderservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private String productId;
    private Integer quantity;
    private Double price;
}

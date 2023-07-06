package com.springboot.myproject.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductDto {
    private String name;
    private int price;
    private int stock;
}

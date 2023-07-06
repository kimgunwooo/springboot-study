package com.springboot.myproject.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ChangeProductNameDto {

    private Long number;
    private String name;
}

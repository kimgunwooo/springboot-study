package com.springboot.myproject.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeProductNameDto {

    private Long number;
    private String name;
}

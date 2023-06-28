package com.springboot.myproject.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class MemberDto {
    private final String name;
    private final String email;
    private final String organization;

}

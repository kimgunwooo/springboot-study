package com.springboot.myproject.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class MemberDto {
    private String name;
    private String email;
    private String organization;
}

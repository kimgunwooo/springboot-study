package com.springboot.myproject.data.dto;

import com.springboot.myproject.data.group.ValidationGroup1;
import com.springboot.myproject.data.group.ValidationGroup2;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidatedRequestDto {
    @NotBlank //null, "", " " 허용하지 않음
    String name;

    @Email
    String email;

    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    String phoneNumber;

    @Min(value = 20, groups = ValidationGroup1.class)
    @Max(value = 40, groups = ValidationGroup2.class)
    int age;

    @Size(min = 0, max = 40)
    String description;

    @Positive
    int count;

    @AssertTrue
    boolean booleanCheck;
}

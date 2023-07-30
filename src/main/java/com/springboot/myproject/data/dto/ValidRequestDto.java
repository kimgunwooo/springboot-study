package com.springboot.myproject.data.dto;

import com.springboot.myproject.config.annotation.Telephone;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidRequestDto {
    @NotBlank //null, "", " " 허용하지 않음
    String name;

    @Email
    String email;

    //@Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    @Telephone
    String phoneNumber;

    @Min(value = 20) @Max(value = 40)
    int age;

    @Size(min = 0, max = 40)
    String description;

    @Positive
    int count;

    @AssertTrue
    boolean booleanCheck;
}

package com.springboot.myproject.config.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //어디서 선언할 수 있는지 정의
@Retention(RetentionPolicy.RUNTIME) //이 어노테이션이 실제로 적용,유지되는 범위
@Constraint(validatedBy = TelephoneValidator.class) //매핑작업
public @interface Telephone {
    String message() default "전화번호 형식이 일치하지 않습니다."; //유효성 검사가 실패할 경우 반한되는 메시지
    Class[] groups() default {}; //유효성 검사를 사용하는 그룹으로 설정
    Class[] payload() default {}; //사용자가 추가 정보를 위해 전달하는 값
}

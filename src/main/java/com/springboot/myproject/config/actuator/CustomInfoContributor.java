package com.springboot.myproject.config.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        Map<String,Object> content = new HashMap<>();
        content.put("code-info","InfoContributor 구현체에서 정의한 정보입니다.");
        //Info 엔드포인트에서 보여줄 내용을 담음
        builder.withDetail("custom-info-contributor",content);
    }
}

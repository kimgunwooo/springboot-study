package com.springboot.myproject.controller;

import com.springboot.myproject.dto.MemberDto;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    //@RequestMapping 사용
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello world";
    }

    //매개 변수 없는 Get 메서드
    @GetMapping("/name")
    public String getName(){
        return "kimgunwooo";
    }

    //@PathVariable 사용
    @GetMapping("/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){//@PathVariable("variable") String var
        return variable;
    }

    //@RequestParam 사용
    @GetMapping("/request1")
    public String getRequestParam1(@RequestParam String name,
                                   @RequestParam String email,
                                   @RequestParam String organization){
        return name + " " + email + " " + organization;
    }
    //어떤 값이 들어올지 모를 때 - Map 사용
    @GetMapping("/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param){
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    //dto 객체 사용
    @GetMapping("/request3")
    public String getRequestParam3(MemberDto memberDto){
        return memberDto.toString();
    }


}

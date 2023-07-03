package com.springboot.myproject.controller.test;

import com.springboot.myproject.dto.MemberDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    @RequestMapping(value="/domain", method = RequestMethod.PUT)
    public String postExample(){
        return "PUT API";
    }

    @PutMapping("/member")
    public String postMember(@RequestBody Map<String,Object> putData){
        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach(map->{
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    @PutMapping("member2")
    public MemberDto postMemberDto(@RequestBody MemberDto memberDto){
        return memberDto;
    }

    //ResponseEntity를 적용한 예
    @PutMapping("/member3")
    public ResponseEntity<MemberDto> putMemberDto3(@RequestBody MemberDto memberDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberDto);
    }

}

package com.springboot.myproject.controller.test;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {
    @DeleteMapping("/{var}")
    public String DeleteVariable(@PathVariable String var){
        return var;
    }

    @DeleteMapping("/request1")
    public String getRequestParam1(@RequestParam String email){
        return "e-mail : " + email;
    }
}

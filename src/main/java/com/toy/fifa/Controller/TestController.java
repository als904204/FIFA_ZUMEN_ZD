package com.toy.fifa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test/bootstrap")
    public String Test() {
        return "/test/test";
    }
}

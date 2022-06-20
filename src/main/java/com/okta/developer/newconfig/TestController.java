package com.okta.developer.newconfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author V·Sel
 * @date 2022/6/20
 */
@Controller
public class TestController {

    @GetMapping("/hello/sayHello")
    @ResponseBody
    public String sayHello() {
        return "hello world !";
    }

    @GetMapping("/hello/sayHi")
    @ResponseBody
    public String sayHi() {
        return "Hi world !";
    }


}

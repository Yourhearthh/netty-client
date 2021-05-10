package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:
 * @Description:
 * @author: baoguangyu
 * @date: 2021-05-10 16:19
 * @version: 1.0
 */
@RestController
public class TestController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello netty";
    }
}

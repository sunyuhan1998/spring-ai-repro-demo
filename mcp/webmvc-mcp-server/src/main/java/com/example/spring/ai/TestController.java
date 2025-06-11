package com.example.spring.ai;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sun Yuhan
 */
@RestController
public class TestController {

    @RequestMapping("/hello")
    public void hello() {
        System.out.println("Hello!");
    }

}

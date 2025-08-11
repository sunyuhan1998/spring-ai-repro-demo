package com.example.spring.ai.deepseek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Sun Yuhan
 */
@SpringBootApplication(scanBasePackages = {"com.example"})
public class DeepSeekApp {
    public static void main(String[] args) {
        SpringApplication.run(DeepSeekApp.class, args);
    }
}

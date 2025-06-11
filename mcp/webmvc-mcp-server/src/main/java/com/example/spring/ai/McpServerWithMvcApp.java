package com.example.spring.ai;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;

/**
 * @author Sun Yuhan
 */
@SpringBootApplication
public class McpServerWithMvcApp {
    public static void main(String[] args) {
        SpringApplication.run(McpServerWithMvcApp.class, args);
    }

    @Bean
    public ToolCallbackProvider dateTimeTools() {
        return MethodToolCallbackProvider.builder().toolObjects(new DateTimeTool()).build();
    }

    public static class DateTimeTool {

        @Tool(description = "Get the current date and time in the user's timezone")
        String getCurrentDateTime() {
            return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
        }

    }
}

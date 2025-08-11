package com.example.spring.ai;

import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.mcp.customizer.McpSyncClientCustomizer;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Sun Yuhan
 */
@SpringBootApplication
public class McpClientApp {
    public static void main(String[] args) {
        SpringApplication.run(McpClientApp.class, args);
    }

    @Bean
    public CommandLineRunner predefinedQuestions(OllamaChatModel chatModel,
                                                 List<McpSyncClient> mcpClients) {

        return args -> {

            var mcpToolProvider = new SyncMcpToolCallbackProvider(mcpClients);

            ChatClient chatClient = ChatClient.builder(chatModel).defaultToolCallbacks(mcpToolProvider).build();

            String userQuestion = """
                    What time is it now?
                    """;

            System.out.println("> USER: " + userQuestion);
            System.out.println("> ASSISTANT: ");
            chatClient.prompt(userQuestion).stream().content().doOnNext(System.out::print).blockLast();
        };
    }
}

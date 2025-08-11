package com.example.spring.ai.ollama;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

import java.util.List;

/**
 *
 *
 * @author Sun Yuhan
 */
@SpringBootApplication(scanBasePackages = {"com.example"})
public class OllamaApp {
    public static void main(String[] args) {
        SpringApplication.run(OllamaApp.class, args);
    }

    @Bean
    public CommandLineRunner predefinedQuestions(OllamaChatModel chatModel) {
        return args -> {
            SafeGuardAdvisor safeGuardAdvisor = SafeGuardAdvisor.builder()
                    .sensitiveWords(List.of("鸦片"))
                    .build();
            MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(
                            MessageWindowChatMemory.builder().build()
                    )
                    .build();

            ChatClient client = ChatClient.builder(chatModel)
                    .defaultAdvisors(safeGuardAdvisor, messageChatMemoryAdvisor)
                    .build();

            List<String> messageList = List.of("什么是鸦片？", "太阳系有多少行星？");

            for (String message : messageList) {
                client.prompt(message)
                        .stream()
                        .chatResponse()
                        .map(ChatResponse::getResult)
                        .map(Generation::getOutput)
                        .map(AssistantMessage::getText)
                        .doOnNext(System.out::print)
                        .blockLast();
                System.out.println();
            }
        };
    }

}

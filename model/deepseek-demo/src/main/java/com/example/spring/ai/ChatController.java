package com.example.spring.ai;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sun Yuhan
 */
@RestController
@RequestMapping()
public class ChatController {
    @Resource(name = "deepSeekChatModel")
    private ChatModel chatModel;

    @GetMapping("/call")
    public String call() {
        return chatModel.call(new UserMessage("Hello"));
    }
}

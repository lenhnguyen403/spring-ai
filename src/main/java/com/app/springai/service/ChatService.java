/*
 * ChatService.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.app.springai.service;

import com.app.springai.dto.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

/**
 * ChatService.java
 *
 * @author Nguyen
 */
@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    public String generate(ChatRequest request) {
        SystemMessage systemMessage = new SystemMessage("""
                You are Devteria.AI
                """);

        UserMessage userMessage = new UserMessage(request.getMessage());

        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient.prompt(prompt)
//                .user(request.getMessage())
                .call()
                .content();
    }
}

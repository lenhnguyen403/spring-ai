/*
 * ChatService.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.app.springai.service;

import com.app.springai.dto.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
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
        return chatClient.prompt()
                .user(request.getMessage())
                .call()
                .content();
    }
}

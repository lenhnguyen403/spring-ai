/*
 * ChatService.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.app.springai.service;

import com.app.springai.dto.BillItem;
import com.app.springai.dto.ChatRequest;
import com.app.springai.dto.ExpenseInfo;
import com.app.springai.dto.FilmInfo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ChatService.java
 *
 * @author Nguyen
 */
@Service
public class ChatService {
    private final ChatClient chatClient;

    private final JdbcChatMemoryRepository chatMemoryRepository;

    public ChatService(ChatClient.Builder chatClient, JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        this.chatMemoryRepository = jdbcChatMemoryRepository;

        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .maxMessages(30)
                .build();

        this.chatClient = chatClient
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    public String generate(ChatRequest request) {
        return chatClient.prompt()
                .user(request.getMessage())
                .call()
                .content();
    }

    // Xay dung Prompt trong Spring AI
    public String chatWithPrompts(ChatRequest request) {
        SystemMessage systemMessage = new SystemMessage("""
                You are Devteria.AI
                You should response with a formal voice
                """);

        UserMessage userMessage = new UserMessage(request.getMessage());

        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient.prompt(prompt)
                .call()
                .content();
    }

    // Xu ly hinh anh voi Spring AI
    public String chatWithImage(MultipartFile file, String message) {
        Media media = Media.builder()
                .mimeType(MimeTypeUtils.parseMimeType(file.getContentType()))
                .data(file.getResource())
                .build();

        ChatOptions chatOptions = ChatOptions.builder()
                .temperature(0D)
                .build();

        return chatClient.prompt()
                .options(chatOptions)
                .system("You are Devteria.AI")
                .user(promptUserSpec
                        -> promptUserSpec
                        .media(media)
                        .text(message))
                .call()
                .content();
    }

    public List<FilmInfo> chatGetInfo(ChatRequest request) {
        SystemMessage systemMessage = new SystemMessage("""
                You are Devteria.AI
                """);

        UserMessage userMessage = new UserMessage(request.getMessage());

        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient.prompt(prompt)
                .call()
                .entity(new ParameterizedTypeReference<List<FilmInfo>>() {
                });
    }

    public ExpenseInfo chatGetExpense(ChatRequest request) {
        SystemMessage systemMessage = new SystemMessage("""
                You are Devteria.AI
                """);

        UserMessage userMessage = new UserMessage(request.getMessage());

        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient.prompt(prompt)
                .call()
                .entity(ExpenseInfo.class);
    }

    public String chatMemory(ChatRequest request) {
        String conversationId = "conversation1";
        SystemMessage systemMessage = new SystemMessage("""
                You are Devteria.AI
                """);

        UserMessage userMessage = new UserMessage(request.getMessage());

        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient.prompt(prompt)
                .advisors(advisorSpec -> advisorSpec.param(
                        ChatMemory.CONVERSATION_ID, conversationId
                ))
                .call()
                .content();
    }

    public List<BillItem> chatWithImageBill(MultipartFile file, String message) {
        Media media = Media.builder()
                .mimeType(MimeTypeUtils.parseMimeType(file.getContentType()))
                .data(file.getResource())
                .build();

        ChatOptions chatOptions = ChatOptions.builder()
                .temperature(0D)
                .build();

        return chatClient.prompt()
                .options(chatOptions)
                .system("You are Devteria.AI")
                .user(promptUserSpec
                        -> promptUserSpec
                        .media(media)
                        .text(message))
                .call()
                .entity(new ParameterizedTypeReference<List<BillItem>>() {
                });
    }
}

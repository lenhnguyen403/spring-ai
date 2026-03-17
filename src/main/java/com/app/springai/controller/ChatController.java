/*
 * ChatController.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.app.springai.controller;

import com.app.springai.dto.ChatRequest;
import com.app.springai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * ChatController.java
 *
 * @author Nguyen
 */
@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/chat-message")
    public String chatMessage(@RequestBody ChatRequest request) {
        return chatService.generate(request);
    }

    @PostMapping("/chat-prompt")
    public String chatWithPrompts(@RequestBody ChatRequest request) {
        return chatService.chatWithPrompts(request);
    }

    @PostMapping("/chat-with-image")
    public String chatWithImage(@RequestParam("file") MultipartFile file,
                                @RequestParam("message") String message) {
        return chatService.chatWithImage(file, message);
    }
}

/*
 * ChatController.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.app.springai.controller;

import com.app.springai.dto.BillItem;
import com.app.springai.dto.ChatRequest;
import com.app.springai.dto.ExpenseInfo;
import com.app.springai.dto.FilmInfo;
import com.app.springai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ChatController.java
 *
 * @author Nguyen
 */
@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/chat/get-info")
    List<FilmInfo> getInfo(@RequestBody ChatRequest request) {
        return chatService.chatGetInfo(request);
    }

    @PostMapping("/chat/get-expense")
    ExpenseInfo getExpenseInfo(@RequestBody ChatRequest request) {
        return chatService.chatGetExpense(request);
    }

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

    @PostMapping("/chat-with-image/bill")
    public List<BillItem> chatWithImageBill(@RequestParam("file") MultipartFile file,
                                            @RequestParam("message") String message) {
        return chatService.chatWithImageBill(file, message);
    }
}

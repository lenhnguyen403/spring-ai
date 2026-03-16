/*
 * MessageType.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.app.springai.enums;

/**
 * MessageType.java
 *
 * @author Nguyen
 */
public enum MessageType {
    USER("user"),
    ASSISTANT("assistant"),
    SYSTEM("system"),
    TOOL("tool");

    MessageType(String type) {

    }
}

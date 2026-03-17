/*
 * BillItem.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.app.springai.dto;

/**
 * BillItem.java
 *
 * @author Nguyen
 */
public record BillItem(String name, String unit, Integer quantity, Double price, Double subTotal) {
}

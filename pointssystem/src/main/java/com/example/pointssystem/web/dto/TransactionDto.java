package com.example.pointssystem.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDto {
    private String payer;
    private int points;
    private LocalDateTime timestamp;
}

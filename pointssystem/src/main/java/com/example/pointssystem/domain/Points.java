package com.example.pointssystem.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Points {
    private String payer;
    private int points;
    private LocalDateTime timestamp;
}

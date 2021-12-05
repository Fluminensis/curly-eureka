package com.example.pointssystem.web.dto;

import lombok.*;

@Data
public class SpendBreakdownDto {
    private String payer;
    private int points;

    public SpendBreakdownDto(String payer){
        this.payer = payer;
    }
}

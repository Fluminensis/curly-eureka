package com.example.pointssystem.web.mapper;

import com.example.pointssystem.domain.Points;
import com.example.pointssystem.web.dto.TransactionDto;

public class PointsMapper {

    /*
        function that converts TransactionDto to Points
     */
    public static Points transactionDtoToPoints(TransactionDto transactionDto){
        return Points.builder()
                .payer(transactionDto.getPayer())
                .points(transactionDto.getPoints())
                .timestamp(transactionDto.getTimestamp())
                .build();
    }
}

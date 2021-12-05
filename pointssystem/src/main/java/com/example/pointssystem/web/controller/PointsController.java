package com.example.pointssystem.web.controller;

import com.example.pointssystem.services.PointsService;
import com.example.pointssystem.web.dto.SpendBreakdownDto;
import com.example.pointssystem.web.dto.SpendDto;
import com.example.pointssystem.web.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/points/")
@RequiredArgsConstructor
public class PointsController {

    private final PointsService pointsService;

    /*
        Transact web service that accepts transaction dto and returns nothing
     */
    @PostMapping(path = "/transact", produces = {"application/json"})
    public ResponseEntity<?> transact(@RequestBody TransactionDto transactionDto)  {
        pointsService.transact(transactionDto);
        return ResponseEntity.noContent().build();
    }

    /*
    Spend web service that accepts spend dto and returns the breakdown of the amount spent.
    Throws an error if there is no points available or points is not enough
    */
    @PostMapping(path = "/spend", produces = {"application/json"})
    public ResponseEntity<List<SpendBreakdownDto>> spend(@RequestBody SpendDto spendDto)   {
        return new ResponseEntity<>(pointsService.spend(spendDto), HttpStatus.OK);
    }

    /*
    Balance web service returns the balance points of each Payer
    */
    @GetMapping(path = "/balance", produces = {"application/json"})
    public ResponseEntity<Map<String, Integer>> balance()   {
        return new ResponseEntity<>(pointsService.balance(), HttpStatus.OK);
    }


}

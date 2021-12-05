package com.example.pointssystem.services;

import com.example.pointssystem.domain.Points;
import com.example.pointssystem.web.dto.SpendBreakdownDto;
import com.example.pointssystem.web.dto.SpendDto;
import com.example.pointssystem.web.dto.TransactionDto;
import com.example.pointssystem.web.mapper.PointsMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointsService {
    /*
    non durable storage of all the points accumulated
    */
    private Set<Points> pointsList = new HashSet<>();

    /*
    function that add points in the pointsList
    */
    public void transact(TransactionDto transactionDto) {
        pointsList.add(PointsMapper.transactionDtoToPoints(transactionDto));
    }

    /*
    function that deduct points from pointsList  if there is a non positive points
    */
    private void deduct(Points point) {
        int pointsToBeDeducted = point.getPoints();
        List<Points> sortedPointsList = pointsList.stream()
                .filter(points -> points.getPayer().equalsIgnoreCase(point.getPayer()))
                .sorted(Comparator.comparing(Points::getTimestamp))
                .collect(Collectors.toList());
        for (Points points : sortedPointsList) {
            if (pointsToBeDeducted > points.getPoints()) {
                pointsToBeDeducted -= points.getPoints();
                pointsList.remove(points);
            } else {
                points.setPoints(points.getPoints() + pointsToBeDeducted);
                break;
            }

        }
    }

    /*
    function that spend points from pointsList  and returns List of SpendBreakDownDto
    */
    public List<SpendBreakdownDto> spend(SpendDto spendDto) {
        if (pointsList.isEmpty()) {
            throw new IllegalArgumentException("You dont have any existing points");
        }

        if (spendDto.getPoints() > getTotalPoints()) {
            throw new IllegalArgumentException("You dont have enough points");
        }

        deductNegativePoints();

        List<Points> sortedPointsList = pointsList.stream()
                .sorted(Comparator.comparing(Points::getTimestamp))
                .filter(points -> points.getPoints() > 0)
                .collect(Collectors.toList());

        int pointsToBeSpent = spendDto.getPoints();
        List<SpendBreakdownDto> spendBreakdownDtoList = new ArrayList<>();

        for (Points points : sortedPointsList) {
            SpendBreakdownDto spendBreakdownDto = new SpendBreakdownDto(points.getPayer());
            if (pointsToBeSpent > points.getPoints()) {

                pointsToBeSpent -= points.getPoints();
                spendBreakdownDto.setPoints(-points.getPoints());
                spendBreakdownDtoList.add(spendBreakdownDto);
                points.setPoints(0);
            } else {
                points.setPoints(points.getPoints() - pointsToBeSpent);
                spendBreakdownDto.setPoints(-pointsToBeSpent);
                spendBreakdownDtoList.add(spendBreakdownDto);
                break;
            }

        }

        pointsList = sortedPointsList.stream().collect(Collectors.toSet());
        return spendBreakdownDtoList;
    }


    /*
    function that removes the non positive transactions from the list of transactions made from transact api
    */
    private void deductNegativePoints() {
        List<Points> negativePointsList = pointsList.stream().filter(points -> points.getPoints() < 0).collect(Collectors.toList());
        if (negativePointsList.isEmpty())
            return;

        pointsList.removeAll(negativePointsList);
        negativePointsList.stream().forEach(points -> {
            deduct(points);
        });
    }

    /*
    function that adds all the points from the pointsList
    */
    private int getTotalPoints() {
        return pointsList.stream().mapToInt(points -> points.getPoints()).sum();
    }


    /*
    function that gets the total balance for each payor from the pointsList
    */
    public Map<String, Integer> balance() {
        return pointsList.stream().sorted(Comparator.comparing(Points::getTimestamp))
                .collect(Collectors.groupingBy(Points::getPayer, LinkedHashMap::new, Collectors.mapping(Points::getPoints, Collectors.summingInt(Integer::intValue))));
    }
}

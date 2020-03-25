package com.github.smaegaard.bowlingPointsCalculator.model;

import java.util.List;

/*
    check README.md file
 */
public class APIPointsAndToken {
    String token;
    List<List<Integer>> points;

    public String getToken() {
        return token;
    }

    public List<List<Integer>> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "APIPointsAndToken{" +
                "token='" + token + '\'' +
                ", points=" + points +
                '}';
    }
}
package com.github.smaegaard.bowlingPointsCalculator.model;

import java.util.List;

public class APIPointsAndToken {
    String token;
    List<List<Integer>> points;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<List<Integer>> getPoints() {
        return points;
    }

    public void setPoints(List<List<Integer>> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "APIPointsAndToken{" +
                "token='" + token + '\'' +
                ", points=" + points +
                '}';
    }
}
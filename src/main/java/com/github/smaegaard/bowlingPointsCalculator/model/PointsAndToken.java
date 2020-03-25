package com.github.smaegaard.bowlingPointsCalculator.model;

import java.util.ArrayList;
import java.util.List;

/*
    Contains the points and token from the PointService.

    Points are in a 1 dimensional list, but 2 numbers are pair together.
    ex. the list 3,4,3,7,10,0  is 3 frames [3,4],[3,7],[10,0]
 */
public class PointsAndToken {
    String token;
    List<Integer> points;

    public PointsAndToken() {
        this.points = new ArrayList<>();
    }

    public PointsAndToken(String token, List<Integer> points) {
        this.token = token;
        this.points = points;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void addPoint(Integer integer) {
        points.add(integer);
    }

    public List<Integer> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "PointsAndToken{" +
                "token='" + token + '\'' +
                ", points=" + points +
                '}';
    }
}

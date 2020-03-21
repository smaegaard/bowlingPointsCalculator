package com.github.smaegaard.bowlingPointsCalculator.model;

import java.util.List;

/*
    Contains the points and token from the point service.

    Points are in a 1 dimensional list, but 2 numbers are pair together.
    ex. the list 3,4,3,7,10,0  is 3 frames [3,4],[3,7],[10,0]
 */
public class PointsAndToken {
    String token;
    List<Integer> points;

    public PointsAndToken() {
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

    public List<Integer> getPoints() {
        return points;
    }

    public void setPoints(List<Integer> points) {
        this.points = points;
    }
}

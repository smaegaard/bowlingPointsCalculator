package com.github.smaegaard.bowlingPointsCalculator.model;

import java.util.List;

public class RESTResult {
    String token;
    boolean success;
    List<Integer> points;

    public RESTResult(String token, List<Integer> points) {
        this.success = false;
        this.token = token;
        this.points = points;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public List<Integer> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "APIPostResult{" +
                "token='" + token + '\'' +
                ", success=" + success +
                ", points=" + points +
                '}';
    }
}

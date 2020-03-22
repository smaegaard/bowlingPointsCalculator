package com.github.smaegaard.bowlingPointsCalculator.model;

import java.util.List;

public class APIPostResult {
    String token;
    boolean success;
    List<Integer> points;

    public APIPostResult(String token, List<Integer> points) {
        this.success = false;
        this.token = token;
        this.points = points;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    @Override
    public String toString() {
        return "APIPostResult{" +
                "token='" + token + '\'' +
                ", success=" + success +
                ", points=" + points +
                '}';
    }
}

package com.github.smaegaard.bowlingPointsCalculator.model;

public class RESTResponse {
    int status;
    String body;

    public RESTResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }
}
